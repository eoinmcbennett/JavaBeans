package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.resources.DeliveryEmployeeController;

public class JavaBeansApplication extends Application<JavaBeansConfiguration> {

    public static void main(final String[] args) throws Exception {
        new JavaBeansApplication().run(args);
    }

    @Override
    public String getName() {
        return "JavaBeans";
    }

    @Override
    public void initialize(final Bootstrap<JavaBeansConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final JavaBeansConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new DeliveryEmployeeController());
    }

}
