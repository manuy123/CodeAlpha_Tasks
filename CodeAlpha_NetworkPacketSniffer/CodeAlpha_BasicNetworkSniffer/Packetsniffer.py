from flask import Flask, render_template
from scapy.all import sniff
from scapy.layers.inet import IP, TCP, UDP, ICMP
import threading

app = Flask(__name__)

captured_packets = []

# Packet processing function
def process_packet(packet):

    if packet.haslayer(IP):

        ip = packet[IP]

        protocol = "Other"

        source_port = ""
        destination_port = ""

        if packet.haslayer(TCP):

            protocol = "TCP"

            source_port = packet[TCP].sport
            destination_port = packet[TCP].dport

        elif packet.haslayer(UDP):

            protocol = "UDP"

            source_port = packet[UDP].sport
            destination_port = packet[UDP].dport

        elif packet.haslayer(ICMP):

            protocol = "ICMP"

        packet_data = {
            "source_ip": ip.src,
            "destination_ip": ip.dst,
            "protocol": protocol,
            "source_port": source_port,
            "destination_port": destination_port,
            "packet_size": len(packet)
        }

        captured_packets.append(packet_data)

        # Keep only latest 20 packets
        if len(captured_packets) > 20:
            captured_packets.pop(0)

# Background sniffing
def start_sniffing():

    sniff(prn=process_packet, store=False)

# Home route
@app.route("/")
def home():

    return render_template("index.html", packets=captured_packets)

if __name__ == "__main__":

    # Run sniffing in background thread
    sniff_thread = threading.Thread(target=start_sniffing)
    sniff_thread.daemon = True
    sniff_thread.start()

    app.run(debug=True)