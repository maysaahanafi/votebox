## Usage ##

```
java tap.Tap [serial] [report address] [port]
```
or
```
java -jar tap.jar [serial] [report address] [port]
```
Where `[serial]` is the serial number to use while on Auditorium, `[report address]` is the name or IP address of the machine running the ChallengeWebServer, and `[port]` is the port to connect to on that machine.

## Introduction ##

The Tap sits on the Auditorium network, watching for Cast, Commit, and Challenge events and forwarding them to the ChallengeWebServer.  It does no preprocessing.