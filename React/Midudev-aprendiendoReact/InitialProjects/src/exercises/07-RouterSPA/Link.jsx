
import { EVENTS } from './consts.js'

export function navigate (href) {
    window.history.pushState({}, '', href) //ponemos la ruta en el historial para poder volver con el back
    const navigationEvent = new Event(EVENTS.PUSHSTATE)
    window.dispatchEvent(navigationEvent) //Enviamos evento de que queremos ir hacia esta ruta
}

export function Link ({ target, to, ...props}) {
    const handleClick = (event) => {
        const isMainEvent = event.button === 0 //primary click
        //Comprobamos si el usuario tiene pulsado el control o el mayus para no hacer nada y que se abra en pesataña o ventana nueva
        const isModifiedEvent = event.metaKey || event.altKey || event.ctrlKey || event.shiftKey
        const isManageableEvent = target === undefined || target === '_self'

        if (isMainEvent && isManageableEvent && !isModifiedEvent) { //No hacemos nada para que el navegador cargue normal
            event.preventDefault() //evitmos la recarga de la pagina
            navigate(to)
        }
    }

    return <a onClick={handleClick} href={to} target={target} {...props} />

}