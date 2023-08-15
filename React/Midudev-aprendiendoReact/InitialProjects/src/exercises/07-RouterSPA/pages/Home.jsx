
import { Link } from "../Link"

export default function HomePage () {
    //Las redirecciones no deben ser botones porque ni dejan abrir en otra pestaña ni copiar la dirección ni los screen-readers lo detectan
    return (
        <>
            <h1>Home</h1>
            <p>Esta es un página de ejemplo para crear un React Router desde cero</p>
            <Link to="/about">Ir a sobre nosotros</Link>
        </>
    )
}