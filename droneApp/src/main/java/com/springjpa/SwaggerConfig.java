package com.springjpa;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				// .apis(RequestHandlerSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.springjpa.rest.controller")).paths(PathSelectors.any())
				.build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("REST API Documentation using Swagger2 in Spring Boot",
				"REST API Documentation using Swagger2", "1.0", "https://springcrudrestapibootstrap.herokuapp.com",
				new Contact("arifin", "", ""),  "Client Appication",
				"https://springcrudrestapibootstrap.herokuapp.com",new ArrayList<VendorExtension>());

		return apiInfo;
	}
}
