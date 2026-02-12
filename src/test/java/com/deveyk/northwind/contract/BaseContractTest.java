package com.deveyk.northwind.contract;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpMethod;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

public abstract class BaseContractTest {

    protected static WireMockServer wireMockServer;
    protected static HttpClient httpClient;
    protected ObjectMapper objectMapper;

    @BeforeAll
    public static void setUpClass() {
        wireMockServer = new WireMockServer(WireMockConfiguration
                .options()
                .port(8089)
                .notifier(new ConsoleNotifier(true)));
        wireMockServer.start();
        WireMock.configureFor("localhost", 8089);


      //   httpClient = HttpClient.newHttpClient();

        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    @AfterAll
    public static void tearDownClass() {
        if (wireMockServer != null && wireMockServer.isRunning())
            wireMockServer.stop();
    }

    @BeforeEach
    public void setUp() {
        wireMockServer.resetAll();
        objectMapper = new ObjectMapper();
    }

    // utility methods

    /*
    protected void stubForRequest(String path, HttpMethod method, HttpStatus responseStatus, Object responseBody) {
        wireMockServer.stubFor(WireMock
                .request(method.name(), WireMock.urlEqualTo(path))
                .willReturn(WireMock.aResponse()
                        .withStatus(responseStatus.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(toJson(responseBody))));
    }
     */

    protected String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JsonProcessingException", e);
        }
    }

    protected Map<String, Object> toMap(HttpResponse<String> object) {
        try {
            return objectMapper.readValue(object.body(), Map.class);
        } catch (Exception ex) {
            throw new RuntimeException("JsonProcessingException", ex);
        }
    }

    protected HttpResponse<String> sendRequest(String path, HttpMethod method) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:" + wireMockServer.port() + path))
                .header("Content-Type", "application/hal+json")
                .header("Accept", "application/hal+json")
                .method(method.name(), HttpRequest.BodyPublishers.noBody())
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    protected HttpResponse<String> sendRequest(String path, HttpMethod method, Object body) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:" + wireMockServer.port() + path))
                .header("Content-Type", "application/hal+json")
                .header("Accept", "application/hal+json")
                .method(method.name(), HttpRequest.BodyPublishers.ofString(toJson(body)))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }


}
