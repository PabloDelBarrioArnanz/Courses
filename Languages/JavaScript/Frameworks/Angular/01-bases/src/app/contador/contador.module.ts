import { NgModule } from "@angular/core";
import { ContadorComponent } from './contador/contador.component';

@NgModule({
    declarations: [ //Modulos que contiene
        ContadorComponent
    ],
    exports: [ //Partes accesibles desde fuera del modulo
        ContadorComponent
    ],
    imports: [ //Los modulos que requiere
        
    ]
})
export class ContadorModule {

}