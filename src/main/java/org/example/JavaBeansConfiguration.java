package org.example;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class JavaBeansConfiguration extends Configuration {
    // TODO: implement service configuration
    private final SwaggerBundleConfiguration swagger = new SwaggerBundleConfiguration();

    public SwaggerBundleConfiguration getSwagger(){
        swagger.setResourcePackage("org.example.resources");
        String[] schemes = {"http","https"};
        swagger.setSchemes(schemes);
        return swagger;
    }
}
