
function sum(a, b) {
  return a + b
}

// Se puede exportar directamente al definir la función
export function subtract(a, b) {
  return a - b
}

export function multiply(a, b) {
  return a * b
}

// export default -> Solo puede haber una por archivo es sin llaves y se importa sin llaves
export { sum }
export default multiply
