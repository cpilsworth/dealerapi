package uk.co.diffa.dealerapi;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import uk.co.diffa.dealerapi.config.DealerApiConfiguration;
import uk.co.diffa.dealerapi.resource.DealerResource;

public class DealerServiceApplication extends Application<DealerApiConfiguration> {
    public static void main(String[] args) throws Exception {
        new DealerServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "dealer-api";
    }

    @Override
    public void initialize(Bootstrap<DealerApiConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DealerApiConfiguration configuration, Environment environment) {
        final DealerResource resource = new DealerResource(configuration.getMongoServer(), configuration.getMongoPort(), configuration.getDatabaseName());
        environment.jersey().register(resource);
    }

}
