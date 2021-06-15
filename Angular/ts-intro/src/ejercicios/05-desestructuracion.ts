
interface Detalle {
    autor: string;
    anio: number;
}

interface Reproductor {
    volumen: number;
    segundo: number;
    cancion: string;
    detalles: Detalle;
}

const reproductor: Reproductor = {
    volumen: 50,
    segundo: 36,
    cancion: 'Mess',
    detalles: {
        autor: 'Ed Sheeran',
        anio: 2015
    }
}

const {volumen: vol, segundo, cancion, detalles:{ autor: autorDetalle } } = reproductor;
//Se pueden extraer las propiedades (desestructuracion) y ademas se pueden renombrar

//console.log('El nivel de volumen es ', reproductor.volumen);
console.log('El nivel de volumen es ', vol);

//console.log('El segundo actual es ', reproductor.segundo);
console.log('El segundo actual es', segundo);

//console.log('La cancion actual es ', reproductor.cancion);
console.log('La cancion actual es ', cancion);

//console.log('El autor es ', reproductor.detalles.autor);
console.log('El autor es ', autorDetalle);


//////////////////// DESESTRUCURAION DE VECTORES ///////////////////////

const dbz: string[] = ['Goku', 'Vegeta', 'Trunks']
const [ dbzPersonaje1 ] = dbz //Los extrae por orden
const [ , , dbzPersonaje3 ] = dbz //Si queremos extraer el 3 personaje sin importarnos los primeros

//console.log('Personaje 1 ', dbz[0])
console.log('Personaje 1 ', dbzPersonaje1)