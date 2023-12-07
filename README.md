# NPR-2023

# TCP
Dùng Socketserver để tạo server

Dùng new Socket để kết nối đến server theo port 

BufferedReader , BufferedWriter để gửi và nhận dữ liệu.

# UDP 
Tạo một UDPServer DatagramSocket để nhận các gói tin từ gửi đến port

Tạo một đối tượng DatagramSocket ở client để nhận và gửi dữ liệu - new DatagramSocket();

Server có thể trích xuất đc ipaddress và port từ dữ liệu gửi đến

Dùng DatagramPacket để đóng gói dữ liệu cbi gửi và cbi nhận đến

Cần chuyển dữ liệu sang dang byte để có thể đóng gói

# MulticastSocket

Phiá Server dùng DatagramSocket để gửi và nhận dữ liệu

Dùng DatagramPacket để đóng gói dữ liệu cbi gửi và cbi nhận đến

Dùng Runnable và Thread để mỗi client tạo ra sẽ chạy trên 1 luồng riêng biệt 

Client dùng MulticastSocket để liên kết nó với port và nhận dữ liệu từ server 

Dùng InetAddress để tạo nhóm với địa chỉ ip

socket.joinGroup(group) sẽ cho client tham gia vào nhóm với địa chỉ ip để cùng nhận dữ liệu 