
/*
    Los datos están separados en dos grupos los primitivos y los no primitivos
        - Primitivos
            - number
            - string
            - boolean
            - null
            - undefined
            - symbol
            - bigint
        - No primitivos
            - Objectos
            - Funciones
*/

/*

    Numbers
    7
    3.14
    19.95
    2.998e8
    -1

    Permiten estas operaciones => +, -, *, /, %, ** (exponente)

*/

/*

    Cadenas de texto
    Se pueden definir con ' o " indistintamente además para crear texto multilineal se usa `
    Se concatenan con + 

*/

/*

    Booleanos
    Su valor es true o false

*/

/* 

    Operadores de comparación
    <, >, <=, >=, ==, !=, ===, !==
    `Estoy Aprendiendo JavaScript` === 'Estoy Aprendiendo JavaScript' -> true

    'Alfa' > 'Beta' -> false | Usa el valor Unicode de las letras

    La igualdad débil compara valores de diferentes tipos (no se suele usar)

    Curiosidad
    true > false -> true
    false < true -> true
    true > true -> false
    false < false -> false

*/

/*

    Operadores lógicos
    &&, ||, !

*/

/*

    Variables

    let -> Puede ser reasignada
    let miVaraible
    let numero = 1
    let isCool = true

    const -> No puede ser reasignada
    const PI = 3.1415
    Error por reasignar una constante TypeError: Assignment to constant variable.
    Por lo tanto las constantes siempre tienen que ser inicializadas cuando se crean
    const myConstant -> fail

    var -> Es la forma más antigua.
    Sin embargo, a día de hoy, no es recomendable usar var ya que tiene comportamientos extraños que pueden causar errores en tu código
    TODO añadir diferencias entre var, const y let

*/

/* 

    null y undefined conceptualmente null es que algo está vacío y undefined es que no está definido

    let someVar -> undefined
    let someVar = undefined -> undefined
    let someVar = null -> null

    Curiosidad
    null === undefined -> false
    null === null -> true
    undefined === undefined -> true

    null == null -> true
    null == undefined -> true
    null != undefined -> false

*/

/*

    Operador typeof
    Devuelve una cadena de texto con el tipo de la variable
    typeof undefined // "undefined"
    typeof true // "boolean"
    typeof 42 // "number"
    typeof "Hola mundo" // "string"

    Curiosidad
    typeof null -> object
    Por lo tanto para comprobar si algo es null se usa ===

*/

/*

    console.log se utiliza para imprimir mensajes en la consola del navegador o del editor de código
    console.error(): Imprime un mensaje de error en la consola
    console.warn(): Imprime un mensaje de advertencia en la consola
    console.info(): Imprime un mensaje de información en la consola

    Permite mostrar varios datos separados por comas console.log('1', '2')
    
*/