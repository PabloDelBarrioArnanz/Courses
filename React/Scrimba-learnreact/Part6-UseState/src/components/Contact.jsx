
import React, { useState } from "react"

import ContactStar from './ContactStar'

export default function Contact() {
    
    const [contact, setContact] = useState({
        firstName: "John",
        lastName: "Doe",
        phone: "+1 (719) 555-1212",
        email: "itsmyrealname@example.com",
        isFavorite: false
    })

    function toggleFavorite() {
        setContact(prev => ({
            ...prev,
            isFavorite: !prev.isFavorite
        }))
    }
    
    return (
        <main>
            <article className="card">
                <img src="./images/user.png" className="card--image" />
                { /*<ContactStar contact={contact} setContact={setContact} />*/ }
                <ContactStar isFavorite={contact.isFavorite} handleFavChange={toggleFavorite} />
                <div className="card--info">
                    <h2 className="card--name">
                        {contact.firstName} {contact.lastName}
                    </h2>
                    <p className="card--contact">{contact.phone}</p>
                    <p className="card--contact">{contact.email}</p>
                </div>
                
            </article>
        </main>
    )
}