


function queTipoSoy<T>(argumento: T) {
    return argumento;
}

function toString<T>(argumento: T): string {
    return argumento + '';
}

let soyString = queTipoSoy('Soy String');
let soyNumber = queTipoSoy<number>(100); //explicito