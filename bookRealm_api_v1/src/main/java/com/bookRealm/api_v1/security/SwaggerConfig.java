package com.bookRealm.api_v1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;



@Configuration
public class SwaggerConfig {
	
	String schemeName="bearerScheme";

	  @Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	    		  .addSecurityItem(new SecurityRequirement().addList(schemeName))
	    		  .components(new Components()
	    				  .addSecuritySchemes(schemeName, new SecurityScheme()
	    						  .name(schemeName).type(SecurityScheme.Type.HTTP)
	    						  .bearerFormat("JWT").scheme("bearer")))
	              .info(new Info().title("BookRealm API")
	              .description("BookRealm REST API")
	              .version("v0.0.1")
	              .contact(new Contact().name("Shailja Agarwal").email("shailjaagarwal1970@gmail.com"))
	              .license(new License().name("Apache")));
	  }

   
}
