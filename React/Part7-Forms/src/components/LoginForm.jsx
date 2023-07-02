import React, {useState} from "react"

export default function LoginForm() {
    
    const [loginData, setLoginData] = useState({
        email: "",
        password: "",
        confirmationPassword: "",
        okayToEmail: false
    })

    function handleData(event) {
        const { name, value, checked, type } = event.target
        setLoginData(prev => ({
            ...prev,
            [name]: type == "checkbox" ? checked : value
        }))
    }

    function handleSubmit(event) {
        event.preventDefault()
        if (loginData.password == loginData.confirmationPassword) {
            console.log("Logging success!")
            if (loginData.okayToEmail)
                console.log("Thanks to subscribe to newsletter")
        } else {
            console.log("Password doesn't match!")
        }
    }
    
    return (
        <div className="form-container">
            <form className="form" onSubmit={handleSubmit}>
                <input
                    name="email"
                    type="email"
                    placeholder="Email address"
                    className="form--input"
                    onChange={handleData}
                    value={loginData.email}
                />
                <input
                    name="password"
                    type="password" 
                    placeholder="Password"
                    className="form--input"
                    onChange={handleData}
                    value={loginData.password}
                />
                <input 
                    name="confirmationPassword"
                    type="password"
                    placeholder="Confirm password"
                    className="form--input"
                    onChange={handleData}
                    value={loginData.confirmationPassword}
                />
                <div className="form--marketing">
                    <input
                        id="okayToEmail"
                        type="checkbox"
                        name="okayToEmail"
                        onChange={handleData}
                        checked={loginData.okayToEmail}
                    />
                    <label htmlFor="okayToEmail">I want to join the newsletter</label>
                </div>
                <button 
                    className="form--submit"
                >
                    Sign up
                </button>
            </form>
        </div>
    )
}
