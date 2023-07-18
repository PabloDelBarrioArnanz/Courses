
import './Card.css'
import starPhoto from '../../../assets/star.png'

//export default function Card(props) { //simple way and access with props.attributeName
export default function Card({personPhoto, rating, description, price, reservation: {openSpots, location}}) {
    return (
        <div className='person'>
            {setSpecialBanner(openSpots, location)}
            <img src={`../../../assets/${personPhoto}`}/>
            <div className='person-title'>
                <img src={starPhoto}/>
                <p>{rating}</p>
            </div>
            <p>{description}</p>
            <p>{price}</p>
        </div>
    )
}

function setSpecialBanner(openSpots, location) {
    let banner = ""
    if (openSpots < 1 ) {
        banner = "SOLD OUT"
    } else if (location === 'Online') {
        banner = "ONLINE"
    }
    return banner != "" && <div className='badge'>{banner}</div>
}


/*
    Not found props will be displayed any ways but with empty content
    <p>{notFoundProp}</p> --> <p></p>
    We can avoid this behavior checking conditionally if prop exists
    {prop.notFound && <p>{notFoundProp}</p>}
    An other way to check it --> <h3 style={{display: props.setup ? "block" : "none"}}>Setup: {props.setup}</h3>
    Setting no display when prop doesn't exists
*/

/*
    How to pass non-string props
    <MyComponent bool={true} number={10} object={{name: 'Pablo'}}>
*/