# Vigenere Encryption
Allows Caesar and Vigenere encryption of a String, with multiple input interfaces including over local network.

For Innovation Week Encryption Workshop.

# Run program
1. Clone this repo.
1. Build with command line ```gradlew build``` (pc) or ```./gradlew build``` (mac/linux)
1. Execute program with ```java -cp build/classes/main Encryptor [args]```
  1. interactive console app - no args
  1. command line
    1. ```[-e message key]``` - return encrypted message
    1. ```[-d message key]``` - return decrypted message
  1. command line with networking
    1. ```[PORT_NUMBER]``` - start server (waits for client's message)
    1. ```[PORT_NUMBER -e/-d message key]``` - start server (will send 
      message when client connects)
    1. ```[SERVER_HOST_NAME PORT_NUMBER]``` - start client and read message 
        from specified server (use name ```local``` if localhost)
    1. ```[SERVER_HOST_NAME PORT_NUMBER -e/-d message key]``` - start 
        client and send message to specified server (use name ```local``` if localhost)
  1. chat app
    1. ```[chat PORT_NUMBER]``` - start chat app on server (waits for client)
    2. ```[chat SERVER_HOST_NAME PORT_NUMBER]``` - start chat app on client
  
  

# What I learned
* Traditional encryption/decryption
* Interfaces
* Extended enums (e.g constructors)
* Command line interface
* Networking (TCP/sockets)
* Gradle build tool
