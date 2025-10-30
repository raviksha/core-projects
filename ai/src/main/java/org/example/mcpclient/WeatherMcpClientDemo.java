package org.example.mcpclient;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.spec.McpSchema;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MCP Client implementation to connect to the Weather MCP Server.
 * This client demonstrates:
 * - Connecting to an MCP server via STDIO transport
 * - Listing available tools
 * - Calling tools with arguments
 * - Listing and using prompts
 */
public class WeatherMcpClientDemo {

    public static void main(String[] args) {
        try {
            // Create the Weather MCP Client and run demo
            WeatherMcpClientDemo client = new WeatherMcpClientDemo();
            client.run();
        } catch (Exception e) {
            System.err.println("Error running MCP client: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void run() throws Exception {
        System.out.println("=== Weather MCP Client Demo ===\n");

        // Step 1: Configure server parameters
        ServerParameters serverParams = createServerParameters();
        System.out.println("1. Configured server parameters for Weather MCP Server");

        // Step 2: Create STDIO transport to connect to the server
        StdioClientTransport transport = new StdioClientTransport(serverParams);
        System.out.println("2. Created STDIO transport");

        // Step 3: Create the MCP client
        McpSyncClient mcpClient = McpClient.sync(transport)
                .requestTimeout(Duration.ofSeconds(30))
                .build();
        System.out.println("3. Created MCP client\n");

        // Step 4: Initialize the connection
        System.out.println("4. Initializing connection to server...");
        McpSchema.InitializeResult initResult = mcpClient.initialize();
        System.out.println("   Connected to: " + initResult.serverInfo().name() +
                         " v" + initResult.serverInfo().version());
        System.out.println("   Server capabilities: " + initResult.capabilities() + "\n");

        // Step 5: List available tools
        System.out.println("5. Listing available tools...");
        McpSchema.ListToolsResult toolsResult = mcpClient.listTools(null);
        List<McpSchema.Tool> tools = toolsResult.tools();
        System.out.println("   Found " + tools.size() + " tool(s):");
        for (McpSchema.Tool tool : tools) {
            System.out.println("   - " + tool.name() + ": " + tool.description());
        }
        System.out.println();

        // Step 6: Call the weather tool for different cities
        System.out.println("6. Calling weather tool...\n");

        String[] cities = {"New York", "London", "Tokyo"};
        for (String city : cities) {
            callWeatherTool(mcpClient, city);
            System.out.println();
        }

        // Step 7: List available prompts
        System.out.println("7. Listing available prompts...");
        McpSchema.ListPromptsResult promptsResult = mcpClient.listPrompts(null);
        List<McpSchema.Prompt> prompts = promptsResult.prompts();
        System.out.println("   Found " + prompts.size() + " prompt(s):");
        for (McpSchema.Prompt prompt : prompts) {
            System.out.println("   - " + prompt.name() + ": " + prompt.description());
        }
        System.out.println();

        // Step 8: Use a prompt template
        System.out.println("8. Using prompt template...");
        usePromptTemplate(mcpClient, "weather_inquiry", "Paris");

        // Step 9: Close the client
        System.out.println("\n9. Closing connection...");
        mcpClient.close();
        System.out.println("   Connection closed successfully");

        System.out.println("\n=== Demo Complete ===");
    }

    /**
     * Creates server parameters for the Weather MCP Server.
     * Configures the command to launch the Java server process.
     */
    private ServerParameters createServerParameters() {
        // Build the command to start the Weather MCP Server
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + "/bin/java";
        String classpath = System.getProperty("java.class.path");

        return ServerParameters.builder(javaBin)
                .args("-cp", classpath, "org.example.weather.WeatherMcpServer")
                .build();
    }

    /**
     * Calls the weather tool for a specific city.
     */
    private void callWeatherTool(McpSyncClient client, String city) throws Exception {
        System.out.println("   Requesting weather for: " + city);

        // Prepare arguments for the tool call
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("city", city);

        // Call the tool
        McpSchema.CallToolResult result = client.callTool(
                new McpSchema.CallToolRequest("get_weather", arguments)
        );

        // Display the result
        System.out.println("   Result:");
        for (McpSchema.Content content : result.content()) {
            if (content instanceof McpSchema.TextContent textContent) {
                // Indent each line of the response
                String[] lines = textContent.text().split("\n");
                for (String line : lines) {
                    System.out.println("      " + line);
                }
            }
        }
    }

    /**
     * Uses a prompt template with arguments.
     */
    private void usePromptTemplate(McpSyncClient client, String promptName, String location)
            throws Exception {
        System.out.println("   Using prompt: " + promptName + " for location: " + location);

        // Prepare prompt arguments
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("location", location);

        // Get the prompt
        McpSchema.GetPromptResult result = client.getPrompt(
                new McpSchema.GetPromptRequest(promptName, arguments)
        );

        // Display the prompt result
        System.out.println("   Prompt description: " + result.description());
        System.out.println("   Messages:");
        for (McpSchema.PromptMessage message : result.messages()) {
            System.out.println("      Role: " + message.role());
            McpSchema.Content content = message.content();
            if (content instanceof McpSchema.TextContent textContent) {
                System.out.println("      Content: " + textContent.text());
            }
        }
    }
}
