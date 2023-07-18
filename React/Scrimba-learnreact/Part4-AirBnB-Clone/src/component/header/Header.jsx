
import './Header.css'
import logo from '../../../assets/airbnb-logo.png'

export default function Header() {
    return (
        <nav className='header'>
            <img className='header' src={logo}/>
        </nav>
    )
} 