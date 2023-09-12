
interface Direccion {
    pais: string,
    ciudad: string,
    calle: string
}

interface SuperHeroe {
    nombre: string,
    edad: number,
    //direccion: Direccion, Opcion 1 con otra interfaz
    direccion: { //Opcion 2
        pais: string,
        ciudad: string,
        calle: string
    },
    mostrarDireccion: () => string
}


const superHeroe: SuperHeroe = {
    nombre: 'Spiderman',
    edad: 25,
    direccion: {
        pais: 'USA',
        ciudad: 'NY',
        calle: 'Main St'
    },
    mostrarDireccion(): string {
        return this.nombre + ', ' + this.direccion.ciudad + ', ' + this.direccion.pais;
    }
}

const direccion = superHeroe.mostrarDireccion();
console.log(direccion)