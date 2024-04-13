package dev.zhivotov.dotamatchupper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {
    @Bean
    public HttpGraphQlClient graphQLClient(){
        WebClient client = WebClient.builder()
                .baseUrl("https://api.stratz.com/graphql")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJTdWJqZWN0IjoiZTUxYjc1OTYtZWU4Ni00N2Q1LTk2MGItZWVkNzQ0ODhmNmY1IiwiU3RlYW1JZCI6IjE2NTU1ODYzMCIsIm5iZiI6MTcxMDc2ODQ3MSwiZXhwIjoxNzQyMzA0NDcxLCJpYXQiOjE3MTA3Njg0NzEsImlzcyI6Imh0dHBzOi8vYXBpLnN0cmF0ei5jb20ifQ.9OQaZZNbtcRGtf90Hy_ZCxsPMPLkgRTkAP72vSMhq8Y")
                .build();
        return HttpGraphQlClient.builder(client).build();
    }
}
