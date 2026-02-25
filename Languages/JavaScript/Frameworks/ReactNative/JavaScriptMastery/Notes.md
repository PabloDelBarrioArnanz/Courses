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

Algo importante sobre las rutas es que si creamos un carpeta con el nombre entre paréntesis se excluirá de la ruta, se usa para agrupar pantallas pero sin complicar la ruta

app/route/home.tsx -> route/home
app/(route)/home.tsx -> route/home

--> RootLayout -> Stack -> Screen -> Tab/s -> tab

Podemos entender la navegabilidad como un árbol jerárquico:

- Los stacks son marcos de trabajo que ocupan toda la pantalla, el atributo name tiene que matcher con una carpeta o fichero

```js
<Stack>
  <Stack.Screen name="(tabs)" options={{ headerShown: false }} />
  <Stack.Screen name="movies/[id]" options={{ headerShown: false }} />
</Stack>
```

- Dentro de esos stacks podemos tener tabs que son penstañas agrupadas por un menú inferior, los nombres también tiene que cuadrar con ficheros

```js
<Tabs>
  <Tabs.Screen name="index" />
  <Tabs.Screen name="search" />
</Tabs>
```

En este ejemplo la pantalla de detalle no tendrá la barra inferior porque no esta dentro de las Tabs.

Otra forma de navegar es de una manera programática manipulando el router a nuestra voluntad. Se suele instanciar en el componente padre y se pasan sus métodos como parámetros a los hijos.

```js
import { useRouter } from "expo-router";

const router = useRouter();
```

## HomeScreen UI

En el fichero app.json se hace referencia a los logos de la app
