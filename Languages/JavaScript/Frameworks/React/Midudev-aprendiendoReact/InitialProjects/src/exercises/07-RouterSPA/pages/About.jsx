import { lazy } from "react"
import { Link } from "../Link"

export default function About () {
    return (
        <>
            <h1>About</h1>
            <div>
                <p>Creando un clon de React router</p>
            </div>
            <Link to="/">Ir a la home</Link>
        </>
    )
}