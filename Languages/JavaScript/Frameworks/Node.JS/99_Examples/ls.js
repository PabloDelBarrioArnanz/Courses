const fs = require('node:fs/promises')
const path = require('node:path')

async function lsAsyncAwait(directory) {
  console.log('Usando promesas y async/await')
  async function mapFiles(filePath) {
    const stats = await fs.stat(filePath)
    return {
      name: path.basename(filePath),
      size: stats.size,
      isDirectory: stats.isDirectory()
    }
  }

  const files = await fs.readdir(directory)
  files.forEach(async file => console.log(await mapFiles(path.join(directory, file))))
}

function lsThen(directory) {
  console.log('Usando promesas y .then()')
  function mapFileThen(filePath) {
    return fs.stat(filePath)
      .then(stats => {
        return {
          name: path.basename(filePath),
          size: stats.size,
          isDirectory: stats.isDirectory()
        }
      })
  }

  fs.readdir(directory)
    .then(files => {
      files.forEach(file => {
        mapFileThen(path.join(directory, file))
          .then(info => console.log(info))
      })
    })
}

; (async () => {
  (process.argv[2] ?? 'async') === 'async'
    ? await lsAsyncAwait(process.argv[3] ?? '.')
    : lsThen(process.argv[3] ?? '.')
})()
