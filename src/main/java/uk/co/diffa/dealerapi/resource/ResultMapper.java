package uk.co.diffa.dealerapi.resource;

import com.mongodb.DBObject;

public interface ResultMapper<T> {

    T map(DBObject result);
}
