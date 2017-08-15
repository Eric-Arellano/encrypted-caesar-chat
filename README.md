# Vigenere Encryption
Allows Caesar and Vigenere encryption of a String, with multiple input interfaces including over local network.

For Innovation Week Encryption Workshop.

## Install program
1. Clone this repo.
1. Build with command line `gradlew build` (pc) or `./gradlew build` (mac/linux)

## Run program

#### Interactive console app
1. `./run.sh`

#### CLI
1. To encrypt: `./run.sh -e <message> <key>`
1. To decrypt: `./run.sh -d <message> <key>`
    
#### CLI with networking
You must start both a server and a client, which can be done in either order.

##### Start server
1. To start server and wait for client's message: `./run.sh <port-number>`
1. To start server and send message to client when it connects: `./run.sh <port-number> <-e or -d> <message> <key>`

##### Start client
1. To start client and read from specified server: `./run.sh <server-host-name> <port-number>`
1. To start client and send message to specified server: `./run.sh <server-host-name> <port-number> <-e or -d> <message> <key>`
  

# What I learned
* Traditional encryption/decryption
* Interfaces
* Extended enums (e.g constructors)
* Command line interface
* Networking (TCP/sockets)
* Gradle build tool
