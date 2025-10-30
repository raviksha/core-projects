const http = require('http');

const PORT = 8000;

const server = http.createServer((req, res) => {
  const timestamp = new Date().toISOString();

  res.writeHead(200, { 'Content-Type': 'text/html' });
  res.end(`
    <!DOCTYPE html>
    <html>
    <head>
      <title>Localtunnel Test Server</title>
      <style>
        body {
          font-family: Arial, sans-serif;
          max-width: 800px;
          margin: 50px auto;
          padding: 20px;
          background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
          color: white;
        }
        .container {
          background: rgba(255, 255, 255, 0.1);
          padding: 30px;
          border-radius: 10px;
          backdrop-filter: blur(10px);
        }
        h1 { margin-top: 0; }
        .info {
          background: rgba(0, 0, 0, 0.2);
          padding: 15px;
          border-radius: 5px;
          margin: 15px 0;
        }
        .success {
          color: #4ade80;
          font-size: 24px;
          font-weight: bold;
        }
      </style>
    </head>
    <body>
      <div class="container">
        <h1>🚀 Localtunnel Test Server</h1>
        <p class="success">✓ Connection Successful!</p>

        <div class="info">
          <h3>Request Information:</h3>
          <p><strong>Path:</strong> ${req.url}</p>
          <p><strong>Method:</strong> ${req.method}</p>
          <p><strong>Timestamp:</strong> ${timestamp}</p>
          <p><strong>User-Agent:</strong> ${req.headers['user-agent']}</p>
        </div>

        <div class="info">
          <h3>About This Test:</h3>
          <p>This is a simple Node.js HTTP server running on localhost:${PORT}</p>
          <p>If you're seeing this through a localtunnel URL, it means the tunnel is working correctly!</p>
          <p>Your local server is now accessible from the internet.</p>
        </div>

        <p style="text-align: center; margin-top: 30px; opacity: 0.8;">
          Powered by Node.js + Localtunnel
        </p>
      </div>
    </body>
    </html>
  `);
});

server.listen(PORT, () => {
  console.log(`\n✓ Test server is running on http://localhost:${PORT}`);
  console.log('Ready to be tunneled with localtunnel!\n');
});

// Handle graceful shutdown
process.on('SIGINT', () => {
  console.log('\n\nShutting down server...');
  server.close(() => {
    console.log('Server closed');
    process.exit(0);
  });
});
