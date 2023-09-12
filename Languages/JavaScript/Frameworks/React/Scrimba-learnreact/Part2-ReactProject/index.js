//like where aren't using CDNs in html header we have to import react
import React from "react"
import ReactDOM from "react-dom/client" //React 18


//Custom element it's a function that returns a React JSX object, his name it's write in PascalCase and invocation it's with < /> instance of ()
function Page() {
    return (
        <div>
            <header>
                <nav>
                    <img src="./react-logo.png" width="40px" />
                </nav>
            </header>
            <h1>Reasons I'm excited to learn React</h1>
            <ol>
                <li>It's a popular library, so I'll be 
                able to fit in with the cool kids!</li>
                <li>I'm more likely to get a job as a developer
                if I know React</li>
            </ol>
            <footer>
                <small>© 2021 Ziroll development. All rights reserved.</small>
            </footer>
        </div>
    )
}


//React 18 changes from ReactDOM.render(where, what) to ReactDom.create(where).render(what)
ReactDOM.createRoot(document.getElementById("root")).render(<Page />)

//To wrap some tags we can use "wildcards" <> and </>

//Our element Page it's too big, then we can use Parent and child to avoid this
function Header() {
    return (
        <header>
            <nav>
                <img src="./react-logo.png" width="40px" />
            </nav>
        </header>
    )
}

function Elements() {
    return (
        <ol>
            <li>It's a popular library, so I'll be 
            able to fit in with the cool kids!</li>
            <li>I'm more likely to get a job as a developer
            if I know React</li>
        </ol>
    )
}

function Footer() {
    return (
        <footer>
            <small>© 2021 Pablo development. All rights reserved.</small>
        </footer>
    )
}

function PageSplitted() {
    return (
        <div>
            <Header/>
            <h1>Reasons I'm excited to learn React</h1>
            <Elements/>
            <Footer/>
        </div>
    )
}

ReactDOM.createRoot(document.getElementById("root")).render(<PageSplitted />)

//Also this components can be in dedicated files
import Header from "./Header"
