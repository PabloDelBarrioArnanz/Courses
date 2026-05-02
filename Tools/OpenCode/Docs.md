
# OpenCode Docs

## Comandos

No hace falta perse saberse los comando, porque con CRT + P podemos acceder al menú de comandos.

- opencode arancar la TUI para usar opencode
- opencode -s para continuar una sesión despues de haber cerrado opencode
- /init crea un fichero AGENTS.md que es como un readme para la IA
- Con la barra / podemos escribir un comando de la propia TUI
- /undo deshace lo último cambiado por el modelo
- Con ! escribimos nosotros el comando directamente, es más barato nivel de token hacerlo y luego decirle que revise la conversación de esta manera usa menos token y responde antes
- /timeline es la linea de tiempo, sirve para hacer rollback desde ahí, o lo que es mejor, volver a ese punto, crea un sesión desde ahí
- /thinking muestro u oculta la línea de pensamiento del modelo (por lo general nunca debemos quitarla)
- /clear o /new crean nueva sesión

## Fichero SPEC.md

Sería una especificación, en este fichero el modelo va guardando lo que le hemos preguntado y lo que ha inferido, nos sirve para recrear o dar contexto a otro modelo o entre sesiones.

## Fichero AGENTS.md

En este fichero, se pone de forma general la descripción del proyecto y todas las decisiones tanto de diseño como de arquitectura que hemos tomado de esta forma nos ahorramos especificarlo en cada prompt.
En realidad esto de AGENTS no es exclusivo de OpenCode si no en realidad es estándar en la industria.
Muchas empresas tiene el suyo propio público por lo que podemos ver ejemplos.
Aun así puedes hacer que se lo salte si insistimos en el prompt.

## Skills

--
