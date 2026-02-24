# React Native from JavaScript Mastery

## Creación del proyecto

Crear el proyecto, se puede hacer con el comando general de expo que crea un proyecto completo y luego con la utilidad de la carpeta script borramos los extras y dejamos el proyecto mínimo para empezar o usamos el comando mínimo directamente.

Luego para instalar tailwind que es opcional tenemos que seguir los pasos de su [guía](https://www.nativewind.dev/docs/getting-started/installation) que son unos cuanto pero fáciles.
Una vez tenemos tailwind funcionando dentro del fichero tailwind.config.js podemos definir nuestro tema, con nuestro colores primarios, secundarios, acentos... con variantes y eso

## Routing & Navigation

La carpeta app representa el router y el nombre del fichero es la url a esa ruta
El orden de rederización es

- \_layout.tsx: Donde se define la estructura de la pagina y aspectos globales

- \index.tsx: Componente donde podemos crear items para renderizar

- \movies\[id].tsx: Cualquier fichero ya se dinámico o no se puede enlazar desde nuestros componentes

Por ejemplo si estamos en el index:

```js
  import { Link } from "react-router";

  <Link href="/onboarding">Onboarding</Link>
  <Link href="/movie/avengers">Avengers Movie</Link>
```

Luego en el componente de detalles [id].tsx podemos recuperar el id con:

```js
import { useLocalSearchParams } from "expo-router";

const { id } = useLocalSearchParams();
```
