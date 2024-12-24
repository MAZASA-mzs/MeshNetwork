import re
import sys
from pathlib import Path
from collections import defaultdict

def parse_logs(log_file):
    """Parse the log file and extract relevant data."""
    with open(log_file, 'r') as file:
        logs = file.readlines()

    data = {
        "message_received": set(),
        "packet_lost": defaultdict(list),
        "packet_died": set(),
        "packets_in_connection": defaultdict(list),
        "end_of_iteration": [],
    }

    for line in logs:
        if match := re.match(r'messageRecieved\s+(\S+)\s+(\S+)', line):
            node_id, message_id = match.groups()
            data["message_received"].add((node_id, message_id))

        elif match := re.match(r'packetLost\s+(\S+)\s+(\S+)', line):
            connection_id, packet_id = match.groups()
            data["packet_lost"][connection_id].append(packet_id)

        elif match := re.match(r'packetDied\s+(\S+)', line):
            packet_id = match.group(1)
            data["packet_died"].add(packet_id)

        elif match := re.match(r'packetesInConnection\s+(\S+)\s+(\S+)\s+(\S+)\s+(\d+)', line):
            connection_id, message_id, packet_id, progress = match.groups()
            data["packets_in_connection"][(connection_id, message_id)].append((packet_id, int(progress)))

        elif match := re.match(r'endOfIteration\s+(\d+)', line):
            global_timer = int(match.group(1))
            data["end_of_iteration"].append(global_timer)

    return data

def calculate_metrics(data):
    """Calculate various network metrics."""
    metrics = {}

    # Total messages sent and received for MDR
    total_messages_sent = len({key[1] for key in data["packets_in_connection"]})
    total_messages_received = len(data["message_received"])
    metrics['MDR'] = (total_messages_received / total_messages_sent) * 100 if total_messages_sent else 0

    # Total packets sent, lost for PLR and PDR
    total_packets_sent = sum(len(packets) for packets in data["packets_in_connection"].values())
    total_packets_lost = sum(len(packets) for packets in data["packet_lost"].values())
    total_packets_delivered = total_packets_sent - total_packets_lost

    metrics['PLR'] = (total_packets_lost / total_packets_sent) * 100 if total_packets_sent else 0
    metrics['PDR'] = (total_packets_delivered / total_packets_sent) * 100 if total_packets_sent else 0

    # End-to-End Latency (approximation based on progress)
    latency_list = []
    for (connection_id, message_id), packets in data["packets_in_connection"].items():
        for packet_id, progress in packets:
            if progress == 100:  # Assuming 100 indicates full delivery
                latency_list.append(progress)
    metrics['Average Latency'] = sum(latency_list) / len(latency_list) if latency_list else 0

    # Network Load
    total_time = max(data["end_of_iteration"]) - min(data["end_of_iteration"])
    metrics['Network Load'] = total_packets_sent / total_time if total_time else 0

    # Network Throughput
    metrics['Throughput'] = total_packets_delivered / total_time if total_time else 0

    return metrics

if __name__ == "__main__":
    if len(sys.argv) == 0:
        file_path = Path("output.txt")
    else:
        file_path = Path(sys.argv[1])
    print(sys.argv)

    data = parse_logs(file_path)
    metrics = calculate_metrics(data)

    print("Mesh Network Metrics:")
    for metric, value in metrics.items():
        print(f"{metric}: {value:.2f}")
