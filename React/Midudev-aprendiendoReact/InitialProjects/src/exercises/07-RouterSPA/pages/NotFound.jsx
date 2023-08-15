import { Link } from "../Link";

export default function NotFound () {
    return (
        <>
            <div>
                <h1>This is not fine</h1>
                <img src="https://midu.dev/images/this-is-fine-404.gif" 
                    alt='Gif del perro this is fine quemándose vivo' />
            </div>
            <Link to='/'>Volver a la Home</Link>
        </>
    )
}