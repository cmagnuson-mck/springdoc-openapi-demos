package org.springdoc.demo.services.book;

import org.springdoc.core.AbstractRequestService;
import org.springdoc.core.GenericResponseService;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.OpenAPIService;
import org.springdoc.core.OperationService;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.SpringDocProviders;
import org.springdoc.webmvc.api.MultipleOpenApiWebMvcResource;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Configuration
public class MyConfig {


    @Bean
    @Lazy(false)
    MultipleOpenApiWebMvcResource multipleOpenApiResource(List<GroupedOpenApi> groupedOpenApis,
                                                          ObjectFactory<OpenAPIService> defaultOpenAPIBuilder, AbstractRequestService requestBuilder,
                                                          GenericResponseService responseBuilder, OperationService operationParser,
                                                          SpringDocConfigProperties springDocConfigProperties,
                                                          SpringDocProviders springDocProviders) {
        return new MultipleOpenApiWebMvcResource(groupedOpenApis,
                defaultOpenAPIBuilder, requestBuilder,
                responseBuilder, operationParser,
                springDocConfigProperties,
                springDocProviders);
    }

    public ConfigurableListableBeanFactory beanFactory;

    public MyConfig(ConfigurableListableBeanFactory beanFactory) {
        //doing a registerSingleton is not working
        this.beanFactory = beanFactory;
        GroupedOpenApi bean = GroupedOpenApi.builder()
                .group("books")
                .pathsToMatch("/api/book/**")
                .build();
        beanFactory.registerSingleton("groupedopenApi", bean);

        GroupedOpenApi bean2 = GroupedOpenApi.builder()
                .group("stores")
                .pathsToMatch("/api/stores/**")
                .build();
        beanFactory.registerSingleton("groupedopenApi2", bean2);
    }

//   Defining as beans works
//    @Bean
//    public GroupedOpenApi groupedopenApi(){
//        return GroupedOpenApi.builder()
//                .group("books")
//                .pathsToMatch("/api/book/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi groupedopenApi2() {
//        return GroupedOpenApi.builder()
//                .group("stores")
//                .pathsToMatch("/api/stores/**")
//                .build();
//    }
}
