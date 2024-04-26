import socket
import time

def synchronize_clock(master_address):
    try:
        slave_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        slave_socket.connect(master_address)

        while True:
            # Send local timestamp to master
            local_timestamp = time.time()
            slave_socket.send(str(local_timestamp).encode())

            # Receive synchronized time from master
            synchronized_time = float(slave_socket.recv(1024).decode())
            print(f"Synchronized time received: {synchronized_time}")

            # Sleep for a while before sending the next timestamp
            time.sleep(5)
    except Exception as e:
        print(f"Error during synchronization: {e}")

# Driver code
if __name__ == '__main__':
    master_address = ('127.0.0.1', 8080)
    synchronize_clock(master_address)
