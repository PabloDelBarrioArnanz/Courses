//This line is getting the element from Html with id root and filling it with first parameter as content 
ReactDOM.render(<h1>Hello, everyone!</h1>, document.getElementById("root"))

ReactDOM.render(
    <ul>
        <li>Item1</li>
        <li>Item2</li>
    </ul>,
    document.getElementById("list"))


// 1 React is Composable

function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <a className="navbar-brand" href="#">Navbar</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                <li className="nav-item active">
                    <a className="nav-link" href="#">Home <span className="sr-only">(current)</span></a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="#">Link</a>
                </li>
                <li className="nav-item dropdown">
                    <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown
                    </a>
                    <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a className="dropdown-item" href="#">Action</a>
                    <a className="dropdown-item" href="#">Another action</a>
                    <div className="dropdown-divider"></div>
                    <a className="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
                <li className="nav-item">
                    <a className="nav-link disabled" href="#">Disabled</a>
                </li>
                </ul>
                <form className="form-inline my-2 my-lg-0">
                    <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" />
                    <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav>
    )
}

function MyComponent() {
    return (
        <h1>Hello from component</h1>
    )
}

ReactDOM.render(
    <Navbar/>,
    document.getElementById("navbar"))

ReactDOM.render(
    <MyComponent/>,
    document.getElementById("myComponent"))


// 2 React is declarative
function createHtmlElementImperativeJSVanilla() {
    const newH1 = document.createElement("h1")
    newH1.textContent = "Imperative here!"
    document.getElementById("imperative").append(newH1)
}

createHtmlElementImperativeJSVanilla()

function createHtmlDeclarative() {
    ReactDOM.render(
        <h1>Declarative here!</h1>,
        document.getElementById("declarative"))
}

createHtmlDeclarative()

// 3 JSX = javascript xml | it'ss like a function that when run returns an object with react can show  
const element = <h1 className="someClass">Declarative here!</h1>
console.log(element) //object
ReactDOM.render(element, document.getElementById("declarative"))

const newH1 = document.createElement("h1")
newH1.textContent = "Imperative here!"
console.log(newH1) //?

//Not possible to render two components at the same time if not wrapped 
//ReactDOM.render(<h1 className="someClass">Declarative here!</h1><p>Paragraph</p>, document.getElementById("declarative"))
const component = (
    <div>
        <h1 className="someClass">Declarative here!</h1>
        <p>Paragraph</p>
    </div>
)
//ReactDOM.render(component, document.getElementById("declarative"))