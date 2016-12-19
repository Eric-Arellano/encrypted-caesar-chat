# Vigenere Encryption
Allows Caesar and Vigenere encryption of a String, with multiple input interfaces including over local network.

For Innovation Week Encryption Workshop.

# Run program
1. Clone this repo.
1. Build with command line ```gradlew build``` (pc) or ```./gradlew build``` (mac/linux)
1. Execute program with ```java -cp build/classes/main Encryptor [args]```
  1. ```[]``` - interactive console app
  1. ```[encrypt_mode message key]``` returns message either encrypted (```-e```) or decrypted (```-d```)
  1. ```[PORT_NUMBER]``` - start server (waits for client's message)
  1. ```[PORT_NUMBER encrypt_mode message key]``` - start client and send 
  message to local server
  1. ```[SERVER_HOST_NAME PORT_NUMBER encrypt_mode message key]``` - start 
  client and send message to specified server

# What I learned
* Traditional encryption/decryption
* Interfaces
* Extended enums (e.g constructors)
* Command line interface
* Networking (TCP/sockets)
* Gradle build tool
