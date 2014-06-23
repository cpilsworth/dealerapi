package uk.co.diffa.dealerapi.resource;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DealerMapper implements ResultMapper {

    @Override
    public Dealer map(final DBObject result) {
        System.out.println(result);
        final String number = (String) result.get("number");
        final String name = (String) result.get("name");
        final BasicDBObject loc = (BasicDBObject) result.get("loc");
        return new Dealer(number, name, getCoordinates(loc));
    }

    private Double[] getCoordinates(final BasicDBObject loc) {
        final BasicDBList coordinates = (BasicDBList) loc.get("coordinates");
        return coordinates.toArray(new Double[coordinates.size()]);
    }
}
