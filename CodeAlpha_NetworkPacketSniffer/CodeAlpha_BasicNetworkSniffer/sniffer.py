from scapy.all import sniff
from scapy.layers.inet import IP, TCP, UDP, ICMP
from datetime import datetime

def process_packet(packet):

    # Process only IP packets
    if packet.haslayer(IP):

        ip = packet[IP]

        print("\n=================================================")
        print("              PACKET CAPTURED")
        print("=================================================")

        # Time
        current_time = datetime.now().strftime("%H:%M:%S")
        print(f"Time              : {current_time}")

        # IP Addresses
        print(f"Source IP         : {ip.src}")
        print(f"Destination IP    : {ip.dst}")

        # Packet Size
        print(f"Packet Size       : {len(packet)} bytes")

        # TCP Protocol
        if packet.haslayer(TCP):

            tcp = packet[TCP]

            print("Protocol          : TCP")
            print(f"Source Port       : {tcp.sport}")
            print(f"Destination Port  : {tcp.dport}")

        # UDP Protocol
        elif packet.haslayer(UDP):

            udp = packet[UDP]

            print("Protocol          : UDP")
            print(f"Source Port       : {udp.sport}")
            print(f"Destination Port  : {udp.dport}")

        # ICMP Protocol
        elif packet.haslayer(ICMP):

            print("Protocol          : ICMP")

        else:
            print("Protocol          : Other")

        # Packet Summary
        print(f"Packet Summary    : {packet.summary()}")

        # Payload Preview
        payload = bytes(packet.payload)

        if payload:

            print("Payload Preview   :")

            try:
                decoded_payload = payload[:100].decode('utf-8', errors='ignore')

                if decoded_payload.strip():
                    print(decoded_payload)

                else:
                    print("Binary Data")

            except:
                print("Unable to decode payload")


# Start sniffing continuously
print("Starting Packet Sniffer...")
print("Press CTRL + C to stop.\n")

sniff(prn=process_packet, store=False)