import React from "react"
import App3 from './App3'

export default function App3Main() {

    const [show, setShow] = React.useState(true)
    
    function toggle() {
        setShow(prevShow => !prevShow)
    }
    
    return (
        <div className="container">
            <button onClick={toggle}>
                Toggle WindowTracker
            </button>
            {show && <App3 />} 
        </div>
    ) //When the component App3 is hidden it follows updating bcs has modify the doom, we need to clean up modifications mede on useEffects
}

