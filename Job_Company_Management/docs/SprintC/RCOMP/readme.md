* **COMMUNICATION INTERFACES:**

* Stream Sockets (TCP) :
 - Guarantees delivery
 - Data arrives in the correct order
 - Connection-oriented
 - Used in applications where data integrity is crucial

       Before sending and receiving data, it is necessary to establish a connection between two applications.
       One application will have the role of receiving the TCP connection, and the other must request the establishment of the TCP connection from the first.
       In other words, the client initiates contact with the server.

 **Client Application:**
     public Socket(InetAddress address, int port) throws IO Exception
 **Server Application:**
     public ServerSocket (int port) throws IO Exception

      To accept requests, the server application must call the accept() method defined in the ServerSocket class.


* **Simple Mail Transfer Protocol (SMTP)**

 * SMTP is the standard protocol used for sending email messages.
 * It utilizes TCP/IP protocols and is fundamental for communication between email servers. 
 * Here, we will explain how SMTP works.

 **Main Components:**:
   1. SMTP Client: The client that sends the email.
   2. SMTP Server: The server that receives the email.
   3. Email Recipient: The server or client that receives the final email.

 - Users are identified by email addresses in the format “user@dns-name”, where user is the mailbox name and dns-name is the DNS name of the server hosting that mailbox.

* When a Mail Transfer Agent (MTA) receives a message, the following steps are performed:
   1. The MTA checks if the dns-name corresponds to its own domain name. If so, the message is delivered to the local user's mailbox.
   2. If the dns-name corresponds to its own domain name for an IP address, it then forwards the message to the corresponding server using the SMTP protocol.

 _Connection Establishment and Message Sending_
 * To send a message, the SMTP client must follow these steps:
     1. Initiate a TCP connection with the SMTP server, which is defined on port number 25 and the ISEP host (Frodo.dei.isep.ipp.pt).
     2. Once the connection is established, a dialog session is initiated where the SMTP client sends SMTP commands to the SMTP server, and the server responds to each command.


* We follow this SMTP protocol as a base:

      220 frodo.dei.isep.ipp.pt ESMTP Mailer DEINET-1.1; Wed, 21 May 2008 18:15:30 +0100
      HELO frodo.dei.isep.ipp.pt
      250 frodo.dei.isep.ipp.pt Hello pci14ppp.dei.isep.ipp.pt [193.136.62.213], pleased to meet you
      MAIL FROM:<andre@dei.isep.ipp.pt>
      250 2.1.0 <andre@dei.isep.ipp.pt>... Sender ok
      RCPT TO:<asc@isep.ipp.pt>
      250 2.1.5 <asc@isep.ipp.pt>... Recipient ok
      DATA
      354 Enter mail, end with "." on a line by itself
      From: "Andre Moreira" <andre@dei.isep.ipp.pt> To: <asc@isep.ipp.pt>
      Subject: Teste
      Mensagem de teste
 
* **Positive Aspects**
    - Offline Mode: SMTP supports sending messages to users who may not be online at the moment and stores the messages until the recipient reconnects.
    - Guaranteed Delivery: SMTP is designed to ensure message delivery, handling network errors and repeatedly attempting to send messages until they are delivered or returned as undeliverable.
    - Expandability: With the evolution of the internet, SMTP has been expanded to support communication between different email systems through networks, using MTAs and DNS records to route messages efficiently.