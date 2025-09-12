
function sum(a, b) {
  return a + b
}

// Dentro del globalThis está el module donde podemos poner los exports
// module.exports = sum

// Otra forma de exportarlo dentro de un objeto
module.exports = {
  sum
}
