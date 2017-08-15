# Vigenere Encryption
Allows Caesar and Vigenere encryption of a String, with multiple input interfaces including over local network.

For Innovation Week Encryption Workshop.

## Install program
1. Clone this repo.
1. Build with command line `gradlew build` (pc) or `./gradlew build` (mac/linux)

## Run program

#### Interactive console app
1. `java -cp build/classes/main Encryptor`

#### CLI
1. To encrypt: `java -cp build/classes/main Encryptor -e <message> <key>`
1. To decrypt: `java -cp build/classes/main Encryptor -d <message> <key>`
    
#### CLI with networking
You must start both a server and a client, which can be done in either order.

##### Start server
1. To start server and wait for client's message: `java -cp build/classes/main Encryptor <port-number>`
1. To start server and send message to client when it connects: `java -cp build/classes/main Encryptor <port-number> <-e or -d> <message> <key>`

##### Start client
1. To start client and read from specified server: `java -cp build/classes/main Encryptor <server-host-name> <port-number>`
1. To start client and send message to specified server: `java -cp build/classes/main Encryptor <server-host-name> <port-number> <-e or -d> <message> <key>`
  

# What I learned
* Traditional encryption/decryption
* Interfaces
* Extended enums (e.g constructors)
* Command line interface
* Networking (TCP/sockets)
* Gradle build tool
