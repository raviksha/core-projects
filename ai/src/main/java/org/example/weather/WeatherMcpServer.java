package org.example.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpServerFeatures.SyncToolSpecification;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpSchema.Content;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Weather MCP Server - A Model Context Protocol server that provides weather information.
 * This server implements:
 * - A weather tool that returns hardcoded temperature data (45F)
 * - MCP prompt templates for weather-related interactions
 * - STDIO transport for communication with Claude Desktop
 * <p>
 * Built using the official MCP Java SDK.
 */

public class WeatherMcpServer {

    private static OpenWeatherMapClient weatherClient;

    /**
     * Main entry point for the Weather MCP Server.
     * Sets up STDIO transport and starts the server to listen for MCP requests.
     *
     * @param args Command line arguments (not used)
     */

    public static void main(String[] args) {

        try {
            // Initialize the weather API client with API key from environment variable
            String apiKey = System.getenv("OPENWEATHERMAP_API_KEY");
            weatherClient = new OpenWeatherMapClient(apiKey);

            // Create STDIO transport provider for communication with Claude Desktop or any local MCP inspector http://localhost:6274/
            StdioServerTransportProvider transportProvider = new StdioServerTransportProvider(new ObjectMapper());
            SyncToolSpecification weatherToolSpec = createWeatherToolSpecification();

            // Create the MCP server with tools and prompts
            McpSyncServer server = McpServer.sync(transportProvider)
                    .serverInfo("weather-server", "1.0.0")
                    .capabilities(McpSchema.ServerCapabilities.builder()
                            .tools(true)    // Enable tools support
                            .prompts(true)  // Enable prompts support
                            .build())
                    .tools(weatherToolSpec)
                    .prompts(createWeatherPromptSpecifications())
                    .build();

            // Start the server - this will block and listen for incoming requests
            System.err.println("Starting Weather MCP Server...");
            System.err.println("Server ready to accept connections via STDIO");

            // The server runs automatically once built
            // Keep the main thread alive

            Thread.currentThread().join();
        } catch (InterruptedException e) {
            // Log error to stderr (stdout is used for MCP communication)
            System.err.println("Failed to start Weather MCP Server: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

    }

    private static SyncToolSpecification createWeatherToolSpecification() {
        // Define the schema as a JSON string
        String schemaString = """
                {
                    "type": "object",
                    "properties": {
                        "city": {
                            "type": "string",
                            "description": "Name of the city to get weather for (e.g., 'New York', 'London', 'Tokyo')"
                        }
                    },
                    "required": ["city"]
                }
                """;

        // Create the tool with String schema
        McpSchema.Tool weatherTool = new McpSchema.Tool(
                "get_weather",
                "Get current weather information for a specified city. Returns temperature data for the requested location.",
                schemaString
        );

        McpServerFeatures.SyncToolSpecification weatherToolSpecification = new McpServerFeatures.SyncToolSpecification(
                weatherTool,
                (exchange, request) -> {
                    // Tool implementation - extract city and get weather data
                    String city = extractCityFromArguments(request);
                    String weatherData = getWeatherData(city);

                    // Return the weather data as text content
                    List<Content> contents = new ArrayList<>();
                    contents.add(new McpSchema.TextContent(weatherData));

                    return new McpSchema.CallToolResult(contents, false);
                }
        );
        return weatherToolSpecification;
    }

    /**
     * Creates MCP prompt specifications that Claude can use for weather-related conversations.
     * These templates provide structured ways to interact with weather data.
     *
     * @return List of prompt specifications
     */

    private static List<McpServerFeatures.SyncPromptSpecification> createWeatherPromptSpecifications() {
        return List.of(
                // Weather inquiry prompt - helps Claude ask about weather
                new McpServerFeatures.SyncPromptSpecification(
                        new McpSchema.Prompt(
                                "weather_inquiry",
                                "Template for asking about weather conditions in a specific location",
                                List.of(new McpSchema.PromptArgument("location", "The city or location to inquire about", true))
                        ),
                        (exchange, request) -> {
                            Map<String, Object> args = request.arguments();
                            String location = args.get("location").toString();

                            // Create a structured prompt for weather inquiries
                            String promptText = String.format(
                                    "I need current weather information for %s. " +
                                            "Please provide the temperature and any relevant weather conditions. " +
                                            "If you need to use a tool to get this information, please do so.",
                                    location
                            );

                            return new McpSchema.GetPromptResult(
                                    "Weather Inquiry for " + location,
                                    List.of(new McpSchema.PromptMessage(
                                            McpSchema.Role.USER,
                                            new McpSchema.TextContent(promptText)
                                    ))
                            );
                        }
                ),
                // Weather travel advice prompt - helps with travel planning based on weather
                new McpServerFeatures.SyncPromptSpecification(
                        new McpSchema.Prompt(
                                "weather_travel_advice",
                                "Template for getting weather-based travel advice for a destination",
                                List.of(
                                        new McpSchema.PromptArgument("destination", "Travel destination city", true),
                                        new McpSchema.PromptArgument("travel_date", "Planned travel date (optional)", false)
                                )
                        ),
                        (exchange, request) -> {
                            Map<String, Object> args = request.arguments();
                            String destination = args.get("destination").toString();
                            Object travelDate = args.get("travel_date");

                            String dateInfo = (travelDate != null) ?
                                    " for travel on " + travelDate.toString() :
                                    " for current conditions";

                            String promptText = String.format(
                                    "I'm planning to travel to %s%s. " +
                                            "Please check the current weather conditions and provide advice on " +
                                            "what to pack and any weather-related considerations for my trip. " +
                                            "Use the weather tool to get current temperature data.",
                                    destination, dateInfo
                            );

                            return new McpSchema.GetPromptResult(
                                    "Travel Weather Advice for " + destination,
                                    List.of(new McpSchema.PromptMessage(
                                            McpSchema.Role.USER,
                                            new McpSchema.TextContent(promptText)
                                    ))
                            );
                        }
                )
        );
    }

    /**
     * Gets weather data for the specified city.
     * Uses OpenWeatherMap API if configured, otherwise returns mock data.
     *
     * @param city The city to get weather data for
     * @return Formatted weather information string
     */
    private static String getWeatherData(String city) {
        // Log the request for debugging
        System.err.println("Fetching weather data for " + city);

        // Use the weather client to get data (real or mock)
        return weatherClient.getWeatherData(city);
    }

    /**
     * Extracts the city parameter from the tool call arguments.
     *
     * @param arguments The tool call arguments map
     * @return The city name as a string
     * @throws IllegalArgumentException if city parameter is missing or invalid
     */
    private static String extractCityFromArguments(Map<String, Object> arguments) {
        // Validate that city parameter exists
        if (!arguments.containsKey("city")) {
            throw new IllegalArgumentException("Missing required parameter: city");
        }

        Object cityObj = arguments.get("city");
        if (cityObj == null) {
            throw new IllegalArgumentException("City parameter cannot be null");
        }

        String city = cityObj.toString().trim();
        if (city.isEmpty()) {
            throw new IllegalArgumentException("City parameter cannot be empty");
        }

        // Log the request for debugging (to stderr to avoid interfering with STDIO)
        System.err.println("Processing weather request for city: " + city);

        return city;
    }
}
