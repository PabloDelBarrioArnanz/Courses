
//Colección de propiedades y métodos
const persona = {
    name: 'Pablo',
    age: 26,
    skills: {
        techSkill: ['Java', 'JavaScript', '...']
    },
    walk: function() {
        console.log('Estoy caminando')
    }
}

//Acceso a propiedades
person.name // -> 'Pablo'

person.fullName // -> undefined

//Otra forma de acceso (menos común pero la única para atributos con caracteres especiales como espacios en el nombre)
person['name']
person['full name']

persona.walk()
persona['walk']()


//Añadir atributos a un objeto (si ya existe se modifica)
persona.language = 'Spanish'

//Borra propiedades
delete persona.language


//Atajos para objetos
const superHeroName = 'Spiderman'

const spiderman = {
    //superHeroName: superHeroName, Como hay un propiedad que se llama igual podemos evitar repetir este nombre
    superHeroName,
    powers: ['Web'],
    universe: 42,
    partner: {
        name: 'Mary Jane',
        universe: 42,
        powers: ['red hair', 'blue eyes']
    }
}

const { powers, universe, score=100 } = spiderman //Desestructurando un objeto para extraer su propiedades, si alguna no existe es undefined a no ser que pongamos un valor default

//Si tuviéramos un ya definida un propiedad llamada universe en el la raíz esta desestructurarían fallaría, pero podemos cambiar su nombre al mismo tiempo
const { universe: universeName } = spiderman
console.log(universeName)

console.log(spiderman.partner.name)

const { partner: {name: partnerName} } = spiderman //Desestructurando objeto multiple
console.log(partnerName)


//Iterando Objetos

for (const property in spiderman) {
    console.log(property) //superHeroName, powers, universe.. El orden no está garantizado!
    console.log(spiderman[property]) //Spiderman, ['Web'], 42.. 
}

Object.keys(spiderman) // -> ['superHeroName', 'powers', 'universe'..]
Object.values(spiderman) // -> ['Spiderman', ['Web'], 42..]
Object.entries(spiderman) // -> [['superHeroName', 'Spiderman'], ['powers', ['Web']], ['universe', 42]..]

//Comprobar si una propiedad existe
'universe' in spiderman // -> true


//Encadenamiento Opcional

const gameSystem = {
    name: 'PS5',
    price: 550,
    specs: {
        cpu: 'AMD Ryzen Zen 2',
        gpu: 'AMD Radeon RDNA 2',
    }
}

//gameSystem.specifications.cpu Error cannot read properties from undefined
//No es practico hacer esto todo el tiempo
if (gameSystem.specifications == 'object') {
    //Así nos aseguramos que el padre existe pero no la propiedad en sí
}

gameSystem.specifications?.cpu // -> Undefined

//Se puede encadenar
gameSystem?.specs?.cpu // -> AMD...