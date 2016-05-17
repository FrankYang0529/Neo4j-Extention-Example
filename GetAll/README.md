# Neo4j-Extention-Example (GetAll)

Version: neo4j 3.0.0

## How to use

1. Compile it:


		javac -cp lib/\* org/neo4j/examples/server/plugins/GetAll.java

		jar -cvf GetAll.jar *


2. Put jar to neo4j plugins

3. Restart neo4j

4. Query it over HTTP:

        curl http://localhost:7474/db/data/ext/GetAll/graphdb/get_all_nodes
