
import React from "react"

//export default function ContactStar({ contact, setContact }) {
export default function ContactStar({ isFavorite, handleFavChange }) {
    
    let startIcon = isFavorite ? "star-filled.png" : "star-empty.png"

    /*function toggleFavorite() {
        setContact(prev => ({
            ...prev,
            isFavorite: !prev.isFavorite
        }))
    }*/
    
    return (
        <img 
            src={`../../images/${startIcon}`} 
            className="card--favorite"
            onClick={handleFavChange}
        />
    )
    
}