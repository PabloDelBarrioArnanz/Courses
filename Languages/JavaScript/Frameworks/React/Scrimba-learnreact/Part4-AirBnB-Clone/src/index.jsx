
import React from "react"
import ReactDOM from "react-dom/client"

import experiencesData from "./experiencesData"

import App from "./component/app-main/App"
import Header from "./component/header/Header"
import Card from "./component/card/Card"

const experiences = experiencesData.map(experience => 
    <Card key={experience.id} {...experience} />
)

ReactDOM.createRoot(document.getElementById('root')).render(
    <div>
        <Header />
        <App />
        <div className="activities">
            {experiences}
        </div>
    </div>
)
