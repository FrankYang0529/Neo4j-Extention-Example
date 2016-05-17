package org.neo4j.examples.server.unmanaged;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;
import org.neo4j.graphdb.*;

@Path( "/helloworld" )
public class HelloWorldResource
{
    private final GraphDatabaseService graphDb;
    private final ObjectMapper objectMapper;

    enum Labels implements Label {
        Person
    }

    enum RelTypes implements RelationshipType {
        ACTED_IN
    }

    public HelloWorldResource( @Context GraphDatabaseService graphDb )
    {
        this.graphDb = graphDb;
        this.objectMapper = new ObjectMapper();
    }

    @GET
    @Path( "/{personName}" )
    public Response hello( @PathParam( "personName" ) String personName ) throws IOException 
    {
        List<String> movieTitles = new ArrayList<>();

        // Do stuff with the graphDb
        try (Transaction tx = graphDb.beginTx()) {
            Node person = graphDb.findNodes(Labels.Person, "name", personName).next();

            for (Relationship actedInRel: person.getRelationships(RelTypes.ACTED_IN, Direction.BOTH)) {
                Node movie = actedInRel.getOtherNode(person);
                movieTitles.add((String) movie.getProperty("title"));
            }

            tx.success();
        }
        
        return Response.status( Status.OK ).entity(objectMapper.writeValueAsString(movieTitles)).build();
    }
}