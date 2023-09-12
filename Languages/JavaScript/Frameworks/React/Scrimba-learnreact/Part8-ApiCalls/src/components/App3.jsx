import React, { useEffect, useState } from "react"

export default function App3() {

    const [width, setWidth] = useState(window.innerWidth)

    useEffect(() => {
        const resizer = () => {
            console.log("Resized")
            setWidth(window.innerWidth)
        }

        window.addEventListener("resize", resizer)
        
        return () => { //This function is cleaning modifications on doom, bcs when it's unmounted will become into memory leeks 
            window.removeEventListener("resize", resizer)
        }
    }, []) //It's not possible to use window.innerWidth as dependency

    return (
        <h1>Window width: {width}</h1> //When its show and hidden it's recalculated window.innerWidth
    )
}
