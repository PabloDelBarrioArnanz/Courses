
const express = require('express')

const app = express()

app.get('/', (req, res) => {
  // .json para json 
  // .send para texto
  res.status(200).json(
  {
    name: "Pablo",
    age: 28
  })
})

const PORT = process.env.port ?? 3000

app.listen(PORT, () => {
  console.log(`App running at port http://localhost:${PORT}`)
})
