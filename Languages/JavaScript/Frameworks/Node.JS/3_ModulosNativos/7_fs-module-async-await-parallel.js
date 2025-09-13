import { readFile } from 'node:fs/promises'


Promise.all([
  readFile('./stuff/file.txt', 'utf-8'),
  readFile('./stuff/bigFile.txt', 'utf-8')
]).then(([content, bigContent]) => {
  console.log(content, '\n')
  console.log(bigContent, '\n')
})
