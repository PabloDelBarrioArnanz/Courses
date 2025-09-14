
const http = require('node:net')

function findAvailablePort(desiredPort) {
  return new Promise((resolve, reject) => {
    const server = http.createServer()
    server.listen(desiredPort, () => {
      const { port } = server.address()
      server.close(() => {
        resolve(port)
      })
    })

    server.on('error', (err) => {
      if (err.code === 'EADDRINUSE') {
        findAvailablePort(0).then(port => resolve(port))
      } else {
        reject(err)
      }
    })
  })
}

findAvailablePort(3000)
  .then((port) => console.log(`Available Port: ${port}`))