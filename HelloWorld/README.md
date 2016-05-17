# Neo4j-Extention-Example (HelloWorldResource)

Version: neo4j 3.0.0

## How to use

1. Compile it:

		javac -cp lib/\* org/neo4j/examples/server/unmanaged/HelloWorldResource.java

		jar -cvf HelloWorldResource.jar *

2. Put jar to neo4j plugins

3. Configure Neo4j by adding a line to conf/neo4j.conf:

		org.neo4j.server.thirdparty_jaxrs_classes=org.neo4j.example.unmanagedextension=/example

4. Restart neo4j
