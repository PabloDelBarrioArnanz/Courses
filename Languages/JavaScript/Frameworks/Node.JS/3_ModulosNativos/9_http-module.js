
const http = require('node:http')

const server = http.createServer((req, res) => {
  console.log('Request received', req.url)
  res.writeHead(200, { 'Content-Type': 'text/plain' })
  res.end('Hello World\n')
})

server.listen(0, () => { // Puerto 0 asigna el primer puerto disponible
  console.log(`Server running at http://localhost:${server.address().port}`)
})
