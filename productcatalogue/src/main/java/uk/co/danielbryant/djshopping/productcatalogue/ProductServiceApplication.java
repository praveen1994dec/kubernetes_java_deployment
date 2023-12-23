package uk.co.danielbryant.djshopping.productcatalogue;
import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthcheck {
@GetMapping("/actuator/health")
public String healthcheck() {
return "Hello User !! " + new Date();
}
}
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import uk.co.danielbryant.djshopping.productcatalogue.healthchecks.BasicHealthCheck;
import uk.co.danielbryant.djshopping.productcatalogue.configuration.ProductServiceConfiguration;
import uk.co.danielbryant.djshopping.productcatalogue.resources.ProductResource;

public class ProductServiceApplication extends Application<ProductServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new ProductServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "product-list-service";
    }

    @Override
    public void initialize(Bootstrap<ProductServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(ProductServiceConfiguration config,
                    Environment environment) {
        final BasicHealthCheck healthCheck = new BasicHealthCheck(config.getVersion());
        environment.healthChecks().register("template", healthCheck);

        Injector injector = Guice.createInjector();
        environment.jersey().register(injector.getInstance(ProductResource.class));
    }
}
