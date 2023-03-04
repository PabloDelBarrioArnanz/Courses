
//IF ELSE and Condition similar to JS

const id = 100

if (id == 100) {
    console.log('Id is 100')
} else {
    console.log("Id is'nt 100")
}

if (id === 100) { //Equal to value & type
    console.log('Id is 100')
} else {
    console.log("Id is'nt 100")
}

if (id != 100) {
    console.log('Id is not equals to 100')
} else {
    console.log("Id is 100")
}



/*
    ==
    ===
    !=
    !===
    && and
    || or
    <
    >
    <=
    >=
    condition ? op1 : op2
*/

//SWITCHES
const color = 'red'

switch (color) {
    case 'red':
        console.log('Color is red')
        break
    case 'blue':
        console.log('Color is blue')
        break
    case 'blue':
        console.log('Color is blue')
        break
    default:
        console.log('Unknown color')
        break;
}

let day
switch (new Date().getDay()) {
    case 0:
        day = 'Sunday'
        break;
    case 1:
        day = 'Monday'
        break;
    case 2:
        day = 'Tuesday'
        break;
        //...
}
