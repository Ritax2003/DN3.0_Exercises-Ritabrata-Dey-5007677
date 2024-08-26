package com.example.BookstoreAPI.service;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class CustomMetricsService {

    private final MeterRegistry meterRegistry;

    public CustomMetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        registerCustomMetrics();
    }

    private void registerCustomMetrics() {
        meterRegistry.counter("bookstore.custom.metric", "type", "custom");
    }

    public void incrementCustomMetric() {
        meterRegistry.counter("bookstore.custom.metric", "type", "custom").increment();
    }
}