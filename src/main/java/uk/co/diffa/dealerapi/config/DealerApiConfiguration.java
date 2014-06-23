package uk.co.diffa.dealerapi.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class DealerApiConfiguration extends Configuration {

    @NotEmpty
    private String databaseName;

    @NotEmpty
    private String mongoServer;

    private int mongoPort;

    @JsonProperty
    public String getMongoServer() {
        return mongoServer;
    }

    @JsonProperty
    public void setMongoServer(String mongoServer) {
        this.mongoServer = mongoServer;
    }

    @JsonProperty
    public int getMongoPort() {
        return mongoPort;
    }

    @JsonProperty
    public void setMongoPort(final int mongoPort) {
        this.mongoPort = mongoPort;
    }

    @JsonProperty
    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(final String databaseName) {
        this.databaseName = databaseName;
    }
}
