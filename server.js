const WebSocket = require('ws');

const server = new WebSocket.Server({ port: 8889 });

server.on('connection', (socket) => {
  console.log('Client connected');

  socket.on('message', (message) => {
    console.log(`Received message: ${message}`);

    // Echo the message back to the client
    socket.send(`You sent: ${message}`);
  });

  socket.on('close', () => {
    console.log('Client disconnected');
  });
});

console.log('WebSocket server started on ws://localhost:8889/');
