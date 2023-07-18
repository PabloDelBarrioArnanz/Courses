
import { useEffect, useState } from "react"
//import memeData from "../memeData"


export default function Input() {

    //const memeDataList = useState(memeData.data.memes)
    const [memeDataList, setMemeDataList] = useState({})


    //The use effect function can't be async bcs can return a function to cleanup but an async function will return a promise
    /*useEffect(() => {
        console.log("Fetching memes images")
        fetch("https://api.imgflip.com/get_memes")
            .then(data => data.json())
            .then(jsonData => jsonData.data.memes)
            .then(memes => setMemeDataList(memes))
    }, [])
    */

    useEffect(() => {
        console.log("Fetching memes images")
        async function getMemes() {
            const res = await fetch("https://api.imgflip.com/get_memes")
            const data = await res.json()
            setMemeDataList(data.data.memes)
        }
        getMemes()
    }, [])

    function getRandomMeme() {
        return memeDataList[Math.floor(Math.random() * memeDataList.length)].url
    }

    const [meme, updateMeme] = useState({
        topText: "",
        bottomText: "",
        randomImage: "https://i.imgflip.com/30b1gx.jpg"
    })

    function getNewMeme() {
        updateMeme(oldMeme => ({
            ...oldMeme,
            randomImage: getRandomMeme()
        }))
        console.log(meme.randomImage)
    }

    function handleNewText(event) {
        const {name, value} = event.target
        updateMeme(oldMeme => ({
            ...oldMeme,
            [name]: value
        }))
    }
    
    return (
        <main>
            <form className="form">
                <input 
                    type="text"
                    placeholder="Top text"
                    className="form--input"
                    name="topText"
                    value={meme.topText}
                    onChange={handleNewText}
                />
                <input 
                    type="text"
                    placeholder="Bottom text"
                    className="form--input"
                    name="bottomText"
                    value={meme.bottomText}
                    onChange={handleNewText}
                />
                <button 
                    className="form--button"
                    onClick={getNewMeme}
                    type="button"
                >
                    Get a new meme image 🖼
                </button>
                <div className="meme">
                    <img className=".meme--image" src={meme.randomImage}/>
                    <h1 className="meme--text top">{meme.topText}</h1>
                    <h1 className="meme--text bottom">{meme.bottomText}</h1>
                </div>
            </form>
        </main>
    )
}

/*
    if whe use getMemeImage() the function will be executed on render phase
    to use parameters we need to use a lambda { () => getMemeImage("") }
    if your state needs the oldValue to calculate new don't access it directly, use better a lambda with the value
    setUrl(prev => prev...)

    if we have an array in a state we can't modify it with operation witch returns a new object like push bc state will no be the same
*/