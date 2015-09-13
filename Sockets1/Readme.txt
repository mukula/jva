The programs in this folder is a example for multi-Threaded client-server communication.
MyServer and MyClient share common classes i.e SendMessage and RecieveMessage.

To run them,first run MyServer in one command prompt and then MyClient in other.

>A socket is one end-point of a two-way communication link between two programs running on the network.

>A socket is bound to a port number so that the TCP layer can identify the application that data is destined to be sent to.

>An endpoint is a combination of an IP address and a port number. Every TCP connection can be uniquely identified by its two endpoints. That way you can have multiple connections between your host and the server.

>The Socket class sits on top of a platform-dependent implementation,which allows Java programs to communicate over the network in a platform-independent fashion.
