package com.app.smart.customerproducts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Customer Products API", version = "2.0", description = "Customer Products Information"))
@SecurityScheme(name = "Authorization", scheme = "basic", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
public class CustomerProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerProductsApplication.class, args);
    }

}
