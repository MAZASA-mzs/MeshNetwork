import pygame
import sys
import math
from pathlib import Path
from time import time
# Initialize Pygame
pygame.init()

# Window dimensions
WIDTH, HEIGHT = 800, 600
screen = pygame.display.set_mode((WIDTH, HEIGHT), pygame.RESIZABLE)
pygame.display.set_caption("Mesh Network Visualizer")

# Colors
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)

# Fonts
font = pygame.font.Font(None, 24)

# Data structures
nodes = {}
connections = {}
packets = {}
messages_received = {}
current_iteration = 0

# Utility for consistent connection coloring
def get_color_from_id(identifier):
    """ Generate a unique color from an identifier """
    hash_code = hash(identifier)
    r = (hash_code & 0xFF0000) >> 16
    g = (hash_code & 0x00FF00) >> 8
    b = hash_code & 0x0000FF
    return (r % 256, g % 256, b % 256)

def parse_iteration(file_path, iteration):
    global nodes, connections, packets, messages_received
    nodes.clear()
    connections.clear()
    packets.clear()
    messages_received.clear()
    iteration_key = f"endOfIteration {iteration}"
    with open(file_path, 'r') as file:
        in_iteration = False
        for line in file:
            line = line.strip()
            if line.startswith(iteration_key):
                in_iteration = True
                continue
            if in_iteration and line.startswith("endOfIteration"):
                break
            if in_iteration:
                if line.startswith("nodePosition"):
                    _, node_id, x, y = line.split()
                    nodes[node_id] = (float(x), float(y))
                elif line.startswith("connectionBetweenNodes"):
                    parts = line.split()
                    conn_id = parts[1]
                    connected_nodes = parts[2:]
                    connections[conn_id] = connected_nodes
                elif line.startswith("packetesInConnection"):
                    _, conn_id, message_id, packet_id, progress = line.split()
                    if conn_id not in packets:
                        packets[conn_id] = []
                    packets[conn_id].append((conn_id, message_id, packet_id, int(progress)))
                elif line.startswith("messageRecieved"):
                    _, node_id, message_id = line.split()
                    if node_id not in messages_received:
                        messages_received[node_id] = []
                    messages_received[node_id].append(message_id)

def draw_network():
    screen.fill(BLACK)

    # Draw connections
    for conn_id, node_ids in connections.items():
        if len(node_ids) == 2:
            start, end = node_ids
            if start in nodes and end in nodes:
                start_pos = nodes[start]
                end_pos = nodes[end]
                connection_color = get_color_from_id(conn_id)
                pygame.draw.line(screen, connection_color, transform(start_pos), transform(end_pos), 2)

    # Draw packets
    for conn_id, packet_list in packets.items():
        if conn_id in connections and len(connections[conn_id]) == 2:
            start, end = connections[conn_id]
            if start in nodes and end in nodes:
                start_pos = nodes[start]
                end_pos = nodes[end]
                for conn_id, message_id, packet_id, progress in packet_list:
                    packet_color = get_color_from_id(message_id)
                    draw_packet(start_pos, end_pos, progress, packet_color)

    # Draw nodes
    for node_id, position in nodes.items():
        pygame.draw.circle(screen, BLUE, transform(position), 8)
        node_text = font.render(node_id[:8], True, WHITE)  # Display first 8 characters of Node ID
        screen.blit(node_text, transform(position))

        # Display received messages
        if node_id in messages_received:
            y_offset = 0
            for message_id in messages_received[node_id]:
                message_text = font.render("Msg", True, get_color_from_id(message_id))
                screen.blit(message_text, (transform(position)[0] + 10, transform(position)[1] + y_offset))
                y_offset += 15

    # Draw iteration count
    iteration_text = font.render(f"Iteration: {current_iteration}", True, WHITE)
    screen.blit(iteration_text, (10, 10))

def transform(position):
    """ Transform world coordinates to screen coordinates """
    x, y = position
    WIDTH, HEIGHT = screen.get_size()
    screen_x = int((x + 100) / 200 * WIDTH)
    screen_y = int((100 - y) / 200 * HEIGHT)
    return (screen_x, screen_y)

def draw_packet(start, end, progress, color):
    """ Draw a packet moving along a connection based on progress """
    start_x, start_y = transform(start)
    end_x, end_y = transform(end)

    # Determine direction and ensure packets stay within bounds
    progress = max(0, min(progress, 1000))

    dx = end_x - start_x
    dy = end_y - start_y
    packet_x = start_x + dx * (progress / 1000)
    packet_y = start_y + dy * (progress / 1000)
    pygame.draw.circle(screen, color, (int(packet_x), int(packet_y)), 5)

def main():
    global current_iteration

    clock = pygame.time.Clock()
    file_path = Path("output.txt")

    DT = 0.05
    last_iteration = float("-inf")

    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_SPACE:
                    if (time() - last_iteration) < DT: continue
                    current_iteration += 1
                    parse_iteration(file_path, current_iteration)
                    last_iteration = time()
                if event.key == pygame.K_z:
                    current_iteration = 0
        if pygame.key.get_pressed()[pygame.K_SPACE]:
            if (time() - last_iteration) < DT: continue
            current_iteration += 1
            parse_iteration(file_path, current_iteration)
            last_iteration = time()

        draw_network()
        pygame.display.flip()
        clock.tick(30)

if __name__ == "__main__":
    main()
