import logo from "../../assets/react-logo.png"
import './Navbar.css'

export default function Navbar() {
  return (
    <nav className="header">
      <img className="logo" src={logo} />
        <h3>ReactFacts</h3>
        <h4>React Course - Vite Project 1</h4>
    </nav>
  )
}

/*
    Not found props will be displayed any ways but with empty content
    <p>{notFoundProp}</p> --> <p></p>
    We can avoid this behavior checking conditionally if prop exists
    {prop.notFound && <p>{notFoundProp}</p>}
    An other way to check it --> <h3 style={{display: props.setup ? "block" : "none"}}>Setup: {props.setup}</h3>
    Setting no display when prop doesn't exists
*/

/*
    How to pass non-string props
    <MyComponent bool={true} number={10} object={{name: 'Pablo'}}>
*/

/*
Transforming text to html structures
const pokemons = ["bulbasaur", "charmander", "squirtle"]

const paragraphs = pokemons.map(pokemon => pokemon[0].toUpperCase() + pokemon.slice(1))
  .map(pokemon => <p>${pokemon}</p>)
  //<p key={pokemon}>{pokemon}</p>

  It's possible to display many elements from arrays 
  return (
      <div>
          {paragraphs}
      </div>
  )
*/