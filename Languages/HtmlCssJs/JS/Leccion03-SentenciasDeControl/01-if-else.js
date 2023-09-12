
let a = Math.random()
let b = Math.random()
let c = Math.random()

console.log("El valor más pequeño es: A:" + a + " " + " B:" + b + " C:" + c)

if (a < b) {
    if (a < c) {
        console.log("A")
    } else if (b < c) {
        console.log("B")
    } else {
        console.log("C")
    }
} else if(b < c) {
    console.log("B")
} else {
    console.log("C")
}


if (a < b && a < c)
    console.log("A")
else if (b < a && b < c)
    console.log("B")
else
    console.log("C")


console.log(Math.min(a, b, c))