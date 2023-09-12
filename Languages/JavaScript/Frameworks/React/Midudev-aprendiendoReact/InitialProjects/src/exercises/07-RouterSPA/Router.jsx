
import React, { useState, useEffect, Children } from "react"
import { match } from "path-to-regexp"

import { EVENTS } from './consts.js'

export function Router ({ children, routes = [], defaultComponent: DefautlComponent = () => <h1>404</h1> }) {
    const [currentPath, setCurrentPath] = useState(window.location.pathname)

    useEffect(() => {
        const onLocationChange = () => setCurrentPath(window.location.pathname)
        //Al cargar la pagina por primera vez ponemos dos listener para cuando se vaya hacia delante o hacia detrás en las paginas se setee la ruta
        window.addEventListener(EVENTS.PUSHSTATE, onLocationChange)
        window.addEventListener(EVENTS.POPSTATE, onLocationChange) //popstate -> evento ya existente del navegador cuando el usuario hace click en ir hacia atrás

        return () => {
            window.removeEventListener(EVENTS.PUSHSTATE, onLocationChange)
            window.removeEventListener(EVENTS.POPSTATE, onLocationChange)
        }
    }, [])

    let routeParams = {}

    const routesFromChilds = Children.map(children, ({ props, type }) => {
        const { name } = type
        const isRoute = name === 'Route'
        
        return isRoute ? props : null
    })

    const routesToUse = routes.concat(routesFromChilds).filter(Boolean)

    const Page = routesToUse.find(({ path }) => {
        if (path === currentPath) return true
        
        const matchedUrl = match(path, { decode: decodeURIComponent })
        const matched = matchedUrl(currentPath)

        if (!matched) return false

        routeParams = matched.params // { query: 'Javascript' }
        return true
    })?.component
    return Page ? <Page routeParams={routeParams} /> : <DefautlComponent routeParams={routeParams} />
}