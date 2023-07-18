import "./Main.css"
import factsData from "../factsData"
import Fact from "./Fact"


export default function Main() {
  const factsToDisplay = factsData.map(factItem => <Fact fact={factItem.fact}/>)
  return (
    <main>
      <h1>Fun facts about React</h1>
      <ul>
        <li>Was first released in 2013</li>
        <li>Was originally created by Jordan Walke</li>
        <li>Has well over 100K stars on GitHub</li>
        <li>Is maintained by Facebook</li>
        <li>Powers thousands of enterprise apps, including mobile apps</li>
      </ul>
      <ul>
        {factsToDisplay}
      </ul>
    </main>
  )
}
