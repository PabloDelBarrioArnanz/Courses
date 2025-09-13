
# NPM Node Package Manager es el gestor de paquetes de Node.JS

Registro oficial de paquetes de la comunidad
Pero también es una línea de comandos para gestionar esos paquetes (alternativas pnpm y yarn)
Similar a Maven con mvn como línea de comandos y Maven Central como repositorio global.
Para crear un proyecto node en el que queremos dependencias de terceros tenemos que ejecutar:

```bash
npm init
```

Nos creará un fichero `package.json` con la configuración del proyecto, como las dependencias (dependencies), scripts, versión, etc.
(Los scripts son comandos personalizados que podemos ejecutar con `npm run <script>` por ejemplo `npm run dev` de react)

Por ejemplo si queremos instalar la librería `picocolors` tenemos que ejecutar:

```bash
npm install picocolors
```

Una vez ejecutado este comando tendremos en nuestro `package.json` una nueva sección llamada `dependencies` con la librería y su versión.

```json
  "dependencies": {
    "picocolors": "^1.1.1"
  }
```

Este signo `^` indica que se puede actualizar a cualquier versión menor, es decir, si la versión actual es 1.1.1 se puede actualizar a 1.2.0 pero no a 2.0.0. Por seguridad lo quitamos.

También se crea una carpeta `node_modules` donde se instalan todas las dependencias y sus dependencias (sub-dependencias).

Al igual que estas dependencias, podemos tener dependencias de desarrollo (devDependencies) que son aquellas que solo necesitamos en desarrollo y no en producción, como por ejemplo herramientas de testing o bundlers.
Están separadas porque en producción no son necesarias y así reducimos el tamaño del proyecto.

```bash
npm install standard -D
```

Una vez ejecutado este comando tendremos en nuestro `package.json` una nueva sección llamada `devDependencies` con la librería y su versión.

```json
  "devDependencies": {
    "standard": "^17.1.2"
  }
```

Es parte de la configuración para ejecutar linters y formateadores de código.

```json
  "eslintConfig": {
    "extends": "standard"
  }
```
