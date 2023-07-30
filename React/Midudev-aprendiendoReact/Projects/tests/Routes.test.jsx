
import { cleanup, render, screen } from "@testing-library/react"
import { describe, it, expect, beforeEach } from "vitest"
import { Router } from "../src/exercises/07-RouterSPA/Router"

describe('Router', () => {
    beforeEach(() => cleanup())
    it('should render when no routes', () => {
        render(<Router routes={[]} />)
        expect(true).toBeTruthy()
    })

    it('should render 404 if no routes match', () => {
        render(<Router routes={[]} defaultComponent={() => <h1>404</h1>} />)
        //console.log(screen.debug())
        
    })
})

/*
Antes 1% de todo

Ahora
 Durante los 300k
    un 3% de vacacional
    un 1% de aereos y grupos
 entre 300 y 600
    un 2,5% de vacacional
    un 1% de aereos y grupos
 entre 600 y 830
    un 2% de vacacional
    un 1% de aereos y grupos
 de 830 ----->
    0% de vacacional
    0% de aereos y grupos

 Ahora un grupo de 100k
    o cerrar el grupo con 1
    o cerra otras cosas al 2% y quedarte 100k al 0%
*/