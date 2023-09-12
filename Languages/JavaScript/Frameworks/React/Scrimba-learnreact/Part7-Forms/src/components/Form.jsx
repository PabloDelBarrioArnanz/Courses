
import React from "react"

export default function Form1() {
    const [personData, setPersonData] = React.useState({
        firstName: "",
        secondName: "",
        email: "",
        comments: "",
        isFriendly: true,
        employmentStatus: "",
        favColor: ""
    })

    function handleChange(event) {
        const {name, value, type, checked} = event.target
        setPersonData(prev => ({
            ...prev,
            [name]: type === "checkbox" ? checked : value //[..] are for evaluate expression instance of use it 
                                                          //form input has the info in value parameter and checkbox on checked
        }))
    }

    function handleSubmit(event) {
        event.preventDefault() //Avoid reset form after submit
    }

    return (
        <form onSubmit={handleSubmit}>
            <input
                name="firstName"
                type="text"
                placeholder="First name"
                onChange={handleChange}
                value={personData.firstName} //Controlled form
            />
            <input
                name="secondName"
                type="text"
                placeholder="Second name"
                onChange={handleChange}
                value={personData.secondName}
            />
            <input
                type="email"
                placeholder="Email"
                onChange={handleChange}
                name="email"
                value={personData.email}
            />
            <textarea 
                value={personData.comments}
                placeholder="Comments"
                onChange={handleChange}
                name="comments"
            />
            <input type="checkbox"
                id="isFriendly"
                name="isFriendly"
                onChange={handleChange}
                checked={personData.isFriendly}
            />
            <label htmlFor="isFriendly">Are you friendly?</label>
            <fieldset>
                <legend>Current employment status</legend>
                <input 
                    type="radio"
                    id="unemployed"
                    name="employmentStatus"
                    value="unemployed"
                    onChange={handleChange}
                    checked={personData.employmentStatus === "unemployed"}
                />
                <label htmlFor="unemployed">Unemployed</label>
                <br />
                <input 
                    type="radio"
                    id="part-time"
                    name="employmentStatus"
                    value="part-time"
                    onChange={handleChange}
                    checked={personData.employmentStatus === "part-time"}
                />
                <label htmlFor="part-time">Part-time</label>
                <br />
                <input 
                    type="radio"
                    id="full-time"
                    name="employmentStatus"
                    value="full-time"
                    onChange={handleChange}
                    checked={personData.employmentStatus === "full-time"}
                />
                <label htmlFor="full-time">Full-time</label>
            </fieldset>
            <br />
            <select
                id="favColor"
                name="favColor"
                onChange={handleChange}
                value={personData.favColor}
            >
                <option value="">Choose one</option>
                <option value="red">Red</option>
                <option value="orange">Orange</option>
                <option value="yellow">Yellow</option>
                <option value="green">Green</option>
                <option value="blue">Blue</option>
                <option value="indigo">Indigo</option>
                <option value="violet">Violet</option>
            </select>
            <label htmlFor="favColor">What is your favorite color?</label>
            <br />
            <button>Submit</button>
        </form>
    )

}