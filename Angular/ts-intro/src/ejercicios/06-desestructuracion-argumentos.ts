

export interface Producto {
    desc: string,
    precio: number
}

const telefono: Producto = {
    desc: 'Nokia A1',
    precio: 190
}

const tablet: Producto = {
    desc: 'IPad Pro',
    precio: 1200
}

export function calculaImpuestoSobreVenta(productos: Producto[]): [number, number] {
    let total = 0;

    //productos.map(producto => producto.precio) Java way
        //.forEach(precio => total += precio);

    productos.forEach( ({precio}) => total += precio); //Desestructuracion de argumentos para recorrer solo lo que queremos
    
    return [total, total * .15];
}

const articulos: Producto[] = [telefono, tablet];

const [total, impuesto] = calculaImpuestoSobreVenta(articulos);

console.log('Total ', total);
console.log('Impuesto ', impuesto);
