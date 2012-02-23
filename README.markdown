# Description

Moneta is a market tool to interface with the Interactive Brokers (IB) java API.

Moneta will clean up the messy and inconsistent data we get from IB and fire
it's own market events.

Currently, only one class listens for events - the DepthTableWriter. This
class listens for MktDepthEvents and writes them to a mysql database.

You can check out the concepts in DepthTableWriter and easily write your own
data processing classes.

# Requirements

- Java JDK 1.5+
- Maven 2
- IB API Client (non-beta 9.66 as of time of writing, download from [here.](http://www.interactivebrokers.com/en/p.php?f=programInterface))

# Building

It's easy, just do:
`mvn package`

# Running

- Configure your database in DepthTableWriter.java.
- Run the IB API Client like `java -jar jtsclient.jar`
- Connect to IB using their client and enter your credentials.
- Request some market depth info in their client.
- Run moneta with `./run.sh`
