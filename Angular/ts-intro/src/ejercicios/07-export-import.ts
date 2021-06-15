
import { Producto, calculaImpuestoSobreVenta } from "./06-desestructuracion-argumentos"; //Donde se define Producto se hace con export y aqui se importa


const carritoCompras: Producto[] = [{desc: 'Telefono 1', precio: 100}, {desc: 'Telefono 2', precio: 130}];

const [total, impuesto] = calculaImpuestoSobreVenta(carritoCompras);

//Los resultado aparecen dos veces porque para crear calculaImpuestoSobreVenta ejecuta todo lo que hay en fichero que lo contiene


