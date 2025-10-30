import "dotenv/config";
import { Client } from "@modelcontextprotocol/sdk/client/index.js";
import { StreamableHTTPClientTransport } from "@modelcontextprotocol/sdk/client/streamableHttp.js";

const CONFIG = {
  mcpServerUrl: process.env.MCP_SERVER_URL || "http://localhost:3000",
  auth: {
    host: process.env.AUTH_HOST || "localhost",
    port: Number(process.env.AUTH_PORT) || 8080,
    realm: process.env.AUTH_REALM || "master",
    clientId: process.env.OAUTH_CLIENT_ID || "mcp-client",
    clientSecret: process.env.OAUTH_CLIENT_SECRET || "",
    username: process.env.USERNAME || "admin",
    password: process.env.PASSWORD || "admin",
  },
};

async function getAccessToken() {
  const tokenUrl = `http://${CONFIG.auth.host}:${CONFIG.auth.port}/realms/${CONFIG.auth.realm}/protocol/openid-connect/token`;

  const params = new URLSearchParams({
    grant_type: "password",
    client_id: CONFIG.auth.clientId,
    client_secret: CONFIG.auth.clientSecret,
    username: CONFIG.auth.username,
    password: CONFIG.auth.password,
    scope: "mcp:tools",
    resource: CONFIG.mcpServerUrl,
  });

  console.log(`🔐 Requesting access token from ${tokenUrl}...`);

  try {
    const response = await fetch(tokenUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: params.toString(),
    });

    if (!response.ok) {
      const errorText = await response.text();
      console.error(`❌ Token request failed: ${response.status}`);
      console.error(errorText);
      throw new Error(`Failed to get access token: ${response.status} ${errorText}`);
    }

    const data = await response.json();
    console.log("✅ Access token obtained successfully");
    return data.access_token;
  } catch (error) {
    console.error("❌ Error getting access token:", error.message);
    throw error;
  }
}

async function main() {
  try {
    console.log("🚀 Starting MCP OAuth Client...\n");

    // Get access token
    const accessToken = await getAccessToken();

    // Create client with OAuth transport
    const client = new Client(
      {
        name: "example-client",
        version: "1.0.0",
      },
      {
        capabilities: {},
      }
    );

    // Create transport with bearer token
    const transport = new StreamableHTTPClientTransport(
      new URL(CONFIG.mcpServerUrl),
      {
        requestInit: {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        },
      }
    );

    console.log(`\n📡 Connecting to MCP server at ${CONFIG.mcpServerUrl}...`);
    await client.connect(transport);
    console.log("✅ Connected to MCP server successfully!\n");

    // List available tools
    console.log("📋 Listing available tools...");
    const tools = await client.listTools();
    console.log(`Found ${tools.tools.length} tools:\n`);

    tools.tools.forEach((tool) => {
      console.log(`  • ${tool.name}: ${tool.description}`);
    });

    // Test the add tool
    console.log("\n🧮 Testing 'add' tool (5 + 3)...");
    const addResult = await client.callTool("add", { a: 5, b: 3 });
    console.log(`Result: ${addResult.content[0].text}`);

    // Test the multiply tool
    console.log("\n🧮 Testing 'multiply' tool (4 × 7)...");
    const multiplyResult = await client.callTool("multiply", { x: 4, y: 7 });
    console.log(`Result: ${multiplyResult.content[0].text}`);

    // Close connection
    console.log("\n👋 Closing connection...");
    await client.close();
    console.log("✅ Client finished successfully!");

  } catch (error) {
    console.error("\n❌ Error:", error.message);
    if (error.stack) {
      console.error(error.stack);
    }
    process.exit(1);
  }
}

main();
