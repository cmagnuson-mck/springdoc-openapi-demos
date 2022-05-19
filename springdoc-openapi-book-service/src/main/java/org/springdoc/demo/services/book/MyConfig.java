package org.springdoc.demo.services.book;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

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
