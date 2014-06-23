package uk.co.diffa.dealerapi.resource;

import com.codahale.metrics.annotation.Timed;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Path("/dealer")
@Produces(MediaType.APPLICATION_JSON)
public class DealerResource {

    private static final ResultMapper<Dealer> mapper = new DealerMapper();
    private final String server;
    private final int port;
    private final String databaseName;

    public DealerResource(String server, int port, String databaseName) {
        this.server = server;
        this.port = port;
        this.databaseName = databaseName;
    }

    @GET @Path("/location/{lat},{lon}")
    @Timed
    public Paged<Dealer> findByLocation(@PathParam("lat") double lat, @PathParam("lon") double lon) throws UnknownHostException {
        return findByLocation(lat, lon, 1);
    }

    @GET @Path("/location/{lat},{lon}/{page}")
    @Timed
    public Paged<Dealer> findByLocation(@PathParam("lat") double lat, @PathParam("lon") double lon, @PathParam("page") Integer page) throws UnknownHostException {
        System.out.println(page);
        final DBCollection dealer = getDealerCollection();

        final BasicDBObject query = getQuery(lat, lon);
        final DBCursor cursor = dealer.find(query);
        final List<Dealer> dealers = new ArrayList<Dealer>();

        try {
            while (cursor.hasNext()) {
                final Dealer dealer1 = mapper.map(cursor.next());
                dealers.add(dealer1);
            }
            return new Paged(cursor.size(), 0, 100, dealers);
        } finally {
            cursor.close();
        }
    }

    private BasicDBObject getQuery(final double lat, final double lon) {
        BasicDBList coordinates = getCoordinateCriteria(lat, lon);
        return new BasicDBObject("loc",
                    new BasicDBObject("$near",
                            new BasicDBObject("type", "Point")
                                .append("coordinates",coordinates)));
    }

    private BasicDBList getCoordinateCriteria(final double lat, final double lon) {
        final BasicDBList coordinates = new BasicDBList();
        coordinates.add(lat);
        coordinates.add(lon);
        return coordinates;
    }

    private DBCollection getDealerCollection() throws UnknownHostException {
        final MongoClient mongoClient = new MongoClient( server , port);
        final DB db = mongoClient.getDB(databaseName);
        return db.getCollection("dealer");
    }
}
