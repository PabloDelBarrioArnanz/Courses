export default function logger (request, response, next) {
  const now = new Date()
  console.log(`Request received: ${request.url}`)
  next()
  console.log(`Request processed: ${request.url} with status ${response.statusCode} in ${new Date() - now}ms`)
}