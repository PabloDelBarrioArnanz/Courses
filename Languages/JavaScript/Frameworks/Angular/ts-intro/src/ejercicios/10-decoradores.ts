

//Decoradores cambian las clases en el momento de la definicion

function classDecorator<T extends {new (...args: any[]): {}}> (constructor: T) {
    return class extends constructor {
        newProperty = 'new Propertie';
        hello = 'override';
    };
}

@classDecorator
class MiSuperClase {
    property: string = 'property';
    hello: string;

    constructor(m: string) {
        this.hello = m;
    }
}

console.log(MiSuperClase);
console.log(new MiSuperClase('Test'));