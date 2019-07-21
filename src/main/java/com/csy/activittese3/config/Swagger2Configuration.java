package com.csy.activittese3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.csy.activittese3.web"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title("流程系统")
                .description("springboot swagger2")
                .termsOfServiceUrl("http://localhost:8080")
                .contact(new Contact("diaoxingguo", "http://localhost:8080", "http://localhost:8080"))
                .build();

    }
}
