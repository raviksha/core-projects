package org.example.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Wrapper class for OpenWeatherMap API integration.
 * Provides a simple interface to fetch real weather data using direct HTTP calls.
 */
public class OpenWeatherMapClient {

    private static final String API_BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    private final String apiKey;
    private final boolean useRealAPI;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    /**
     * Creates a new OpenWeatherMapClient.
     * If API key is not provided or is empty, falls back to mock data.
     *
     * @param apiKey OpenWeatherMap API key (can be null for mock data)
     */
    public OpenWeatherMapClient(String apiKey) {
        this.apiKey = apiKey;
        if (apiKey != null && !apiKey.trim().isEmpty()) {
            this.useRealAPI = true;
            this.httpClient = HttpClient.newHttpClient();
            this.objectMapper = new ObjectMapper();
            System.err.println("OpenWeatherMap client initialized with API key");
        } else {
            this.useRealAPI = false;
            this.httpClient = null;
            this.objectMapper = null;
            System.err.println("No API key provided - using mock weather data");
        }
    }

    /**
     * Gets weather data for the specified city.
     * Returns real data if API key is configured, otherwise returns mock data.
     *
     * @param city The city to get weather data for
     * @return Formatted weather information string
     */
    public String getWeatherData(String city) {
        if (useRealAPI) {
            return getRealWeatherData(city);
        } else {
            return getMockWeatherData(city);
        }
    }

    /**
     * Fetches real weather data from OpenWeatherMap API using direct HTTP calls.
     */
    private String getRealWeatherData(String city) {
        try {
            // URL encode the city name
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

            // Build the API URL
            String url = String.format("%s?q=%s&appid=%s&units=imperial",
                    API_BASE_URL, encodedCity, apiKey);

            // Create HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Execute request
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            // Check response status
            if (response.statusCode() != 200) {
                System.err.println("API returned status code: " + response.statusCode());
                return getMockWeatherData(city) + "\n\nNote: API request failed with status " +
                        response.statusCode();
            }

            // Parse JSON response
            JsonNode root = objectMapper.readTree(response.body());

            // Extract weather data
            String cityName = root.path("name").asText();
            double temp = root.path("main").path("temp").asDouble();
            double feelsLike = root.path("main").path("feels_like").asDouble();
            int humidity = root.path("main").path("humidity").asInt();
            int pressure = root.path("main").path("pressure").asInt();
            double windSpeed = root.path("wind").path("speed").asDouble();
            String weatherMain = root.path("weather").get(0).path("main").asText();
            String weatherDesc = root.path("weather").get(0).path("description").asText();

            // Format the response
            String timestamp = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            );

            return String.format(
                    "Weather Report for %s\n" +
                            "========================\n" +
                            "Current Temperature: %.0f°F\n" +
                            "Feels Like: %.0f°F\n" +
                            "Conditions: %s\n" +
                            "Description: %s\n" +
                            "Humidity: %d%%\n" +
                            "Wind Speed: %.1f mph\n" +
                            "Pressure: %d hPa\n" +
                            "Last Updated: %s\n" +
                            "\n" +
                            "Data provided by OpenWeatherMap API",
                    cityName,
                    temp,
                    feelsLike,
                    weatherMain,
                    weatherDesc,
                    humidity,
                    windSpeed,
                    pressure,
                    timestamp
            );

        } catch (Exception e) {
            System.err.println("Error fetching weather data from API: " + e.getMessage());
            e.printStackTrace(System.err);
            // Fall back to mock data on error
            return getMockWeatherData(city) + "\n\nNote: API request failed - " + e.getMessage();
        }
    }

    /**
     * Returns mock weather data when API is not configured.
     */
    private String getMockWeatherData(String city) {
        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );

        return String.format(
                "Weather Report for %s\n" +
                        "========================\n" +
                        "Current Temperature: 84°F\n" +
                        "Conditions: Clear\n" +
                        "Humidity: 65%%\n" +
                        "Wind: Light breeze\n" +
                        "Last Updated: %s\n" +
                        "\n" +
                        "Note: This is sample data. To get real weather data:\n" +
                        "1. Get a free API key from https://openweathermap.org/api\n" +
                        "2. Set environment variable: OPENWEATHERMAP_API_KEY=your_key\n" +
                        "3. Restart the MCP server",
                city,
                timestamp
        );
    }

    /**
     * Checks if the client is using real API or mock data.
     */
    public boolean isUsingRealAPI() {
        return useRealAPI;
    }
}
