package akademiasovy.PSC;

import akademiasovy.PSC.resources.Path;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class ProjectPSCobciApplication extends Application<ProjectPSCobciConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ProjectPSCobciApplication().run(args);
    }

    @Override
    public String getName() {
        return "ProjectPSCobci";
    }

    @Override
    public void initialize(final Bootstrap<ProjectPSCobciConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ProjectPSCobciConfiguration configuration,
                    final Environment environment) {

        environment.jersey().register(
                new Path()
        );

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

// Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

// Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}