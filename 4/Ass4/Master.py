import socket
import threading
import time

def handle_slave(slave_socket, slave_address, timestamps):
    try:
        # Receive timestamp from slave
        timestamp = float(slave_socket.recv(1024).decode())
        timestamps.append(timestamp)
        print(f"Received timestamp {timestamp} from {slave_address}")
    except Exception as e:
        print(f"Error handling {slave_address}: {e}")

def synchronize_clocks(slave_sockets):
    try:
        # Collect timestamps from all slaves
        timestamps = []
        threads = []
        for slave_socket, slave_address in slave_sockets:
            thread = threading.Thread(target=handle_slave, args=(slave_socket, slave_address, timestamps))
            threads.append(thread)
            thread.start()
        for thread in threads:
            thread.join()

        # Calculate average timestamp
        average_timestamp = sum(timestamps) / len(timestamps)

        # Broadcast average timestamp to all slaves
        for slave_socket, slave_address in slave_sockets:
            slave_socket.send(str(average_timestamp).encode())
            print(f"Sent average timestamp {average_timestamp} to {slave_address}")
    except Exception as e:
        print(f"Error during synchronization: {e}")

def start_master_server(port):
    master_server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    master_server.bind(('127.0.0.1', port))
    master_server.listen(5)
    slave_sockets = []
    print("Master node is running and waiting for slaves to connect.")
    while True:
        slave_socket, slave_address = master_server.accept()
        slave_sockets.append((slave_socket, slave_address))
        print(f"Slave connected: {slave_address}")
        # Synchronize clocks whenever a new slave connects
        synchronize_clocks(slave_sockets)

# Driver code
if __name__ == '__main__':
    start_master_server(8080)

