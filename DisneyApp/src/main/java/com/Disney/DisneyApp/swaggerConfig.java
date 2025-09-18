package com.Disney.DisneyApp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition (
        info=@Info(
                title = "API DISNEY",
                description = "poner descripcion",
                version = "1.0.0",
                contact = @Contact(
                        name = "Roberto Torre",
                        url = "www.example.com/contact",
                        email = "torreroberto1996@gmail.com"
                ),
                license = @License(
                        name = "Standard Software Use Liscense for UnProgramadorNace",
                        url = "www.example.com/liscense",
                        identifier = "132.465.789.0"
                )
        ),
        servers ={
                @Server(
                        description =   "DEV SERVER",
                        url = "http://localhost:8080"
                ),
            @Server(
            description =   "PROD SERVER",
            url = "http://exampleprod:8080"
            )
        },
        security = @SecurityRequirement(
                name = "Security Token"
        )
)
@SecurityScheme(
        name = "Security Token",
        description = "Token de acceso para la API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)

public class swaggerConfig {
}
