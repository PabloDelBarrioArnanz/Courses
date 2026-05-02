
# OpenCode Docs

## Comandos

No hace falta perse saberse los comando, porque con CRT + P podemos acceder al menú de comandos.

- opencode arancar la TUI para usar opencode
- opencode -s para continuar una sesión despues de haber cerrado opencode
- /init crea un fichero AGENTS.md que es como un readme para la IA
- Con la barra / podemos escribir un comando de la propia TUI
- tab cambio de agente
- Con @ podemos especificar un fichero en concreto (ahorra token y responde antes si acotamos el tiro) o subagente
- CRT + X + UP/DOWN navegar entre subagentes
- /undo deshace lo último cambiado por el modelo
- Con ! escribimos nosotros el comando directamente, es más barato nivel de token hacerlo y luego decirle que revise la conversación de esta manera usa menos token y responde antes
- /timeline es la linea de tiempo, sirve para hacer rollback desde ahí, o lo que es mejor, volver a ese punto, crea un sesión desde ahí
- /thinking muestro u oculta la línea de pensamiento del modelo (por lo general nunca debemos quitarla) hay modelos que no "razonan" por lo que nunca veremos este hilo
- /compact compacta la sesión para ahorra contexto y tokens
- /share te comparte una URL con todo lo que has hecho en esa sesión y puedas compartirlo
- /clear o /new crean nueva sesión

## Generalidades

OpenCode por defecto tiene 2 modos o mejor llamados agentes que centran el comportamiento (razonamiento/acciones/respuesta) de una manera determinada, estos agentes son:

- Build: Agente general para construcción/desarrollo
- Plan: Agente para planificación de tareas (no modifica)

Según conversamos con el modelo en una misma sesiones se va llenando el contexto (% de ocupación), cuanto más lleno está menos especifico (más ruido) y más token consume, lo ideal es que si queremos seguir usándolo porque tenemos temas abiertos lo ideal es usar /compact para compactar la sesión ahorrando contexto y tokens

## Fichero SPEC.md

Sería una especificación, en este fichero el modelo va guardando lo que le hemos preguntado y lo que ha inferido, nos sirve para recrear o dar contexto a otro modelo o entre sesiones.

## Fichero AGENTS.md

En este fichero, se pone de forma general la descripción del proyecto y todas las decisiones tanto de diseño como de arquitectura que hemos tomado de esta forma nos ahorramos especificarlo en cada prompt.
En realidad esto de AGENTS no es exclusivo de OpenCode si no en realidad es estándar en la industria.
Muchas empresas tiene el suyo propio público por lo que podemos ver ejemplos.
Aun así puedes hacer que se lo salte si insistimos en el prompt.

## Skills/Agent Skills/Carpeta skill con ficheros SKILL.md

Inventado por ClaudeCode es una forma de extender el conocimiento de nuestro modelo, por ejemplo si queremos usar una librería pero el modelo no lo conoce y solo puede tirar de la documentación en internet, pues quizá podemos instalar una skill (rollo plugin) especifica para ese librería y de esa forma todo lo que el modelo haga relacionado con este tema será mucho más acertado.
Se pueden buscar en skills.sh y no da un comando para instalarlas.
En realidad no dejan de ser markdowns que se añaden al contexto y le dan las instrucciones para proceder al modelo.
Autoskills creado por Midu hace encargar de buscar e instalar todas las skill necesarias para un proyecto en vez de tener que ir a mano tu una por una.
En base a las dependencias de tu proyecto te sugiere las skills más útiles y mejor valoradas (no las descarga de un repo extraño sino de un registro donde ya han sido auditadas).

```shell
npx autoskills@latest
```

OpenCode debe automáticamente usar la skill que mejor se ajusta a la tarea pedida, pero podemos especificarlo a mano si queremos asegurarnos o tenemos algunas muy similares.
(En las skills suele haber una sección donde pide al modelo que se use cuando se detecten ciertas palabras)

Este tipo de ampliación de conocimiento es ideal para la mayoría de modelos que no llegan al expertís y de esta forma podemos estar más cerca.

## Fichero DESIGN.md

Especificación de google, muy similar al AGENTS pero para el diseño. Recoge especificaciones como: tipo de letra, colores, bordes, sombras...
Algunas skills lo pueden pedir o mejorar si lo tenemos definido.

## AGENTES y SUBAGENTES

Los agentes y subagentes son trabajadores especializados y centrados que de forma separada realizan tareas de manera concurrente, por defecto tenemos 2 ya disponibles, general para investigaciones y explore para reconocimiento del proyecto.
Lo bueno de los agentes además de la concurrencia es el uso del contexto, cada uno tiene una sesión por lo que rellenan el contexto con lo que de verdad a cada uno le interesa acotando el tema.
Podemos crear los nuestros propios, para OpenCode debemos situarlos bajo `.opencode/agents/[agent-name.md]` para usarlos a nivel de proyecto o `~/.config/opencode/commands/command-name.md` para que sean de uso global
Por ejemplo podemos crear un agente para seguridad `security.md` y completar este template:

```md
---
description: Performs security audits and identifies vulnerabilities
mode: agent/subagent
model: GPT 5.5 // OPTIONAL
permissions:
  edit: deny
  bash:
    "*": ask
    "git status *": allow
    "git diff *": allow
    "git log *": allow
---

You are a security expert. Focus on identifying potential security issues.
[..]

```

La diferencia entre agentes o subagentes puede ser un poco difusa, pero básicamente un agente debe tener un contexto global del proyecto y un objetivo propio y nos interesa que mantenga ese contexto entre propmts, en cambio el subagente vive dentro de un agente, su objetivo es más acotado y no tiene porque tener el contexto global si solo de su labor, siempre es orquestado por un agente padre.
Los subagentes nos ayudan a centrar el tiro (más especializados), a ir más rápido (concurrencia) y consumir menos (cada uno tiene un contexto).

Entonces usaremos diferentes agentes cuando tenemos tareas completamente diferentes por ejemplo back y front y agente con subagentes cuando tenemos una tarea grande que queremos desengranar.

Después de crearlos hay que reabrir OpenCode.
Dentro de un prompt podemos delegar tareas a subagentes y hacer que la orquestación la dirija nuestro agente principal tal que así:

```plaintext
Para la tarea que hemos estado hablado:

Quiero que:
@explorer busque la librería que mejor se ajuste a nuestro caso de uso
@builder haga una implementación
@security revise que la librería que se añade no tiene vulnerabilidades y se actualiza con frecuencia

El agente principal debe esperar que estén los resultados de todos y ...
```

Esto lanzará 3 tareas concurrentes con sesiones diferentes.

## CUSTOM COMMANDS

Al igual que agentes podemos crear comandos propios que invocaremos en OpenCode con `/nombre-comando` debemos situarlos en `.opencode/commands/command-name.md` para usarlos a nivel de proyecto o `~/.config/opencode/commands/command-name.md` para que sean de uso global
El patrón es:

```md
---
description: ...
---

Que hace ese comando si recibe o no parámetros, que pasos debe aplicar...
```

En realidad es parecido a un prompt guardado.
