import pygame
import sys
from pathlib import Path
from time import time

# Constants
BROADCAST_ADDRESS = "ffffffff-ffff-ffff-ffff-ffffffffffff"

# Initialize Pygame
pygame.init()

# User controls
VIEW_MODES = [
    "standart",
    "message"
]
view_mode = "standart"
view_message_index = 0
view_message_id = None
view_update_massege_id = True

# Window dimensions
WIDTH, HEIGHT = 800, 600
screen = pygame.display.set_mode((WIDTH, HEIGHT), pygame.RESIZABLE)
pygame.display.set_caption("Mesh Network Visualizer")

# Colors
BLACK = (0, 0, 0)
GREY = (127, 127, 127)
WHITE = (255, 255, 255)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)

# Fonts
font = pygame.font.Font(None, 24)

# Data structures
nodes = {}
connections = {}
packets = []
recieved_packets = []
current_iteration = 0

# Utility for consistent connection coloring
def get_color_from_id(identifier):
    hash_code = hash(identifier)
    r = (hash_code & 0xFF0000) >> 16
    g = (hash_code & 0x00FF00) >> 8
    b = hash_code & 0x0000FF
    return (r % 256, g % 256, b % 256)

def parse_iteration(file_path, iteration):
    global nodes, connections, packets, recieved_packets
    nodes.clear()
    connections.clear()
    packets.clear()
    recieved_packets.clear()
    iteration_key = f"StartOfIteration\t{iteration}"
    with open(file_path, 'r') as file:
        in_iteration = False
        for line in file:
            line = line.strip()
            if line.startswith(iteration_key):
                in_iteration = True
                continue
            if not in_iteration: continue
            if line.startswith("EndOfIteration"):
                in_iteration = False
                break

            if line == "NodesListing":
                continue
            elif line == "NodeState":
                node_id = file.readline().strip()
                x, y = map(float, file.readline().strip().split())
                nodes[node_id] = (x, y)
                for _ in range(3): file.readline()
            elif line == "ConnectionListing":
                continue
            elif line == "ConnectionState":
                conn_id = file.readline().strip()
                file.readline()  # Skip isP2P
                connected_nodes = file.readline().strip().split()
                connections[conn_id] = connected_nodes
                # Skip PacketsState and check it
                if not file.readline().startswith("PacketsState"): continue
                packet_line = file.readline().strip()
                packet_details = packet_line.split()
                for i in range(0, len(packet_details), 7):
                    sender_id   = packet_details[i]
                    receiver_id = packet_details[i + 1]
                    if receiver_id == BROADCAST_ADDRESS:
                        receiver_id = [cID for cID in connected_nodes if cID != sender_id][0]
                    message_id  = packet_details[i + 4]

                    progress = int(packet_details[i + 6])
                    packets.append((sender_id, receiver_id, message_id, progress))
            elif line.startswith("MessageRecieved"):
                data = line.split()[1:]
                recieved_packets.append((data[0], data[1]))

def draw_network():
    global view_mode, view_message_id, view_update_massege_id
    messages = list({packet[2] for packet in packets})
    screen.fill(BLACK)

    # Draw nodes
    for node_id, position in nodes.items():
        pygame.draw.circle(screen, BLUE, transform(position), 8)
        node_text = font.render(node_id[:8], True, WHITE)
        screen.blit(node_text, transform(position))

    # Draw connections
    for conn_id, node_ids in connections.items():
        for i in range(len(node_ids)):
            for j in range(i + 1, len(node_ids)):
                start = node_ids[i]
                end = node_ids[j]
                if start in nodes and end in nodes:
                    start_pos = nodes[start]
                    end_pos = nodes[end]
                    connection_color = get_color_from_id(conn_id)
                    pygame.draw.line(screen, connection_color, transform(start_pos), transform(end_pos), 2)

    # Draw packets
    for sender_id, receiver_id, message_id, progress in packets:
        if sender_id in nodes and receiver_id in nodes:
            start_pos = nodes[sender_id]
            end_pos = nodes[receiver_id]
            size = 5
            if view_mode != "message":
                packet_color = get_color_from_id(message_id)
            else:
                if (view_update_massege_id) or (view_message_id is None) or (view_message_id not in messages):
                    view_message_id = messages[view_message_index%len(messages)]
                    view_update_massege_id = False
                if (message_id == view_message_id):
                    packet_color = get_color_from_id(message_id)
                    size += 5
                else:
                    packet_color = GREY
            draw_packet(start_pos, end_pos, progress, packet_color, size)

    # Draw revieved message
    for node_id, message_id in recieved_packets:
        pygame.draw.circle(screen, GREY, transform(nodes[node_id]), 18)
        pygame.draw.circle(screen, get_color_from_id(message_id), transform(nodes[node_id]), 16)


    # Draw iteration count
    iteration_text = font.render(f"Iteration: {current_iteration}", True, WHITE)
    screen.blit(iteration_text, (10, 10))

    # Draw view mode
    view_mode_text = font.render(view_mode, True, WHITE)
    screen.blit(view_mode_text, (10, HEIGHT - view_mode_text.get_rect().h - 10))

    # Draw selected message ID
    message_id_text = font.render(view_message_id, True, GREEN)
    screen.blit(message_id_text, (10, HEIGHT - view_mode_text.get_rect().h - 10 \
                                             - message_id_text.get_rect().h - 10))

    

def transform(position):
    x, y = position
    screen_x = int((x + 100) / 200 * WIDTH)
    screen_y = int((100 - y) / 200 * HEIGHT)
    return (screen_x, screen_y)

def draw_packet(start, end, progress, color, size=5):
    start_x, start_y = transform(start)
    end_x, end_y = transform(end)

    progress = max(0, min(progress, 1000))
    dx = end_x - start_x
    dy = end_y - start_y
    packet_x = start_x + dx * (progress / 1000)
    packet_y = start_y + dy * (progress / 1000)
    pygame.draw.circle(screen, color, (int(packet_x), int(packet_y)), size)

def main():
    global current_iteration
    global WIDTH, HEIGHT
    global view_mode, view_message_index, view_update_massege_id

    clock = pygame.time.Clock()
    file_path = Path(sys.argv[1]) if len(sys.argv) > 1 else Path("meshnetwork.log")
    dt = 0.05
    last_iteration_time = time()

    while True:
        WIDTH, HEIGHT = screen.get_size()
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_SPACE:
                    current_iteration += 1
                    parse_iteration(file_path, current_iteration)
                    last_iteration_time = time()
                if event.key == pygame.K_x:
                    view_mode = VIEW_MODES[(VIEW_MODES.index(view_mode)+1) % len(VIEW_MODES)]
                if event.key == pygame.K_c:
                    view_message_index += 1
                    view_update_massege_id = True
                if event.key == pygame.K_a:
                    try:
                        current_iteration = int(input())
                    except Exception:
                        print("Invalid input!")
                

        keys = pygame.key.get_pressed()
        if keys[pygame.K_SPACE]:
            if time() - last_iteration_time > dt:
                current_iteration += 1
                parse_iteration(file_path, current_iteration)
                last_iteration_time = time()
        if keys[pygame.K_z]:
            current_iteration = 0
            draw_network()

        draw_network()
        pygame.display.flip()
        clock.tick(30)

if __name__ == "__main__":
    main()
