//For-Loop

for (let i = 0; i < 10; i++) {

}

for (const x of Array(5).keys()) { //0 to 4

}

//While-Loop
i = 0
while (i < 10) {
    //do something
    i++
}

//Do-While loop at least will run 1 execution
i = 1000
do {
    //do something
} while (i < 10)

//Every loop can be break with break

const cars = ['Ford', 'Chevy', 'Honda', 'Toyota']

for (let i = 0; i < cars.length; i++) {
    //Do something with the car cars[i]
}

//Foreach function
cars.forEach(car => console.log(car))
cars.forEach((car, index) => console.log(`${index}: ${car}`))
cars.forEach((car, index, array) => console.log(`${index}: ${car} | ${array}`))

//For-Object
const user = {
    firstName: 'Pablo',
    lastName: 'Barrio',
    age: 26
}

for (let x in user) { //x is the key
    console.log(`${x} => ${user[x]}`)
}