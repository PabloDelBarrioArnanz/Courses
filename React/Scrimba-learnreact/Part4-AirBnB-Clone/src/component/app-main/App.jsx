
import './App.css'
import gridLogo from '../../../assets/photo-grid.png'

export default function App() {
    return (
        <>
            <nav className='main'>
                <img className='main' src={gridLogo}/>
            </nav>
            <nav className='main-content'>
                <h1 className='main-content'>Online Experiences</h1>
                <p className='main-content'>Join unique interactive activities led by one-of-a-kind host --all without leaving home.</p>
            </nav>
        </>
    )
} 