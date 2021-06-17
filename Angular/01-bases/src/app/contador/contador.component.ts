import { Component } from '@angular/core';


@Component({
    selector: 'app-contador',
    template: `
        <h1>Hola Mundo</h1>
        <h2>{{ titulo }}</h2> <!-- Con las llaves dobles se pueden insertar codigo js o acceder a variables del fichero .ts-->
        
        <h3>La base es: <strong> {{base}} </strong></h3>
        
        <button (click)="contador = contador + 1">+ 1</button>  <!-- los parentesis en click significan que es un evento -->
        <button (click)="acumular(base)">+ {{base}}</button>
        
        <span> {{ contador }} </span>
        
        <button (click)="contador = contador - 1">- 1</button>
        <button (click)="acumular(-base)">- {{base}}</button>
    `
})
export class ContadorComponent {

  titulo: string = 'Titulo Componente 1';
  contador: number = 0;

  base: number = 5;

  acumular(valor: number) {
    this.contador += valor;
  }
}