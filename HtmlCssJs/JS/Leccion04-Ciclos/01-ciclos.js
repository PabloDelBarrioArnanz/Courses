let contador = 0
while (contador < 3) {
    console.log(contador)
    contador++
}

contador = 0
do {
    console.log(contador)
    contador++
} while(contador < 3) //Aunque la condición sea falsa se ejecuta 1 vez

for (let contadorFor = 0; contadorFor < 3; contadorFor++) {
    console.log(contadorFor)
}

//break rompe el ciclo


for (contadorFor = 0; contadorFor < 3; contadorFor++) {
    console.log(contadorFor)
    break
}

for (contadorFor = 0; contadorFor < 3; contadorFor++) {
    console.log(contadorFor)
    continue
    console.log("Nunca se ejecuta esto") // continue fuerza que se vaya a la siguiente iteración
}

inicio:
for (contadorFor = 0; contadorFor < 3; contadorFor++) {
    console.log(contadorFor)
    continue inicio //codigo spaguetti
    console.log("Nunca se ejecuta esto")
}