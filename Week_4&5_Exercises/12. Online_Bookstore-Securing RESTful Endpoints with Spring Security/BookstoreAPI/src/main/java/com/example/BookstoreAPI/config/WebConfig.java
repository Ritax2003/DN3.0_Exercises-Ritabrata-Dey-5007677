package com.example.BookstoreAPI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)  // Enable path extension (e.g., /customers.xml)
                .favorParameter(false)     // Disable URL parameter-based content negotiation (e.g., ?format=xml)
                .ignoreAcceptHeader(false) // Enable Accept header for content negotiation
                .defaultContentType(MediaType.APPLICATION_JSON) // Default to JSON if no format is specified
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }
}
