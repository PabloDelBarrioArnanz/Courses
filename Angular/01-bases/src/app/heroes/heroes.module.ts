import { NgModule } from "@angular/core";
import { HeroeComponent } from './heroe/heroe.component';
import { ListadoComponent } from './listado/listado.component';
import { CommonModule } from "@angular/common";

@NgModule({
    declarations: [ //Modulos que contiene
        HeroeComponent,
        ListadoComponent
    ],
    exports: [ //Partes accesibles desde fuera del modulo
        ListadoComponent
    ],
    imports: [ //Los modulos que requiere
        CommonModule
    ]
})
export class HeroesModule {

}