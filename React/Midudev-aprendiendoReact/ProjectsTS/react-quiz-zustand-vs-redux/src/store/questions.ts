import confetti from 'canvas-confetti'
import { create } from 'zustand'
import { devtools, persist } from 'zustand/middleware'
import { type Question } from '../types'

interface State {
    questions: Question[]
    currentQuestion: number,
    fetchQuestions: (limit: number) => Promise<void>
    selectAnswer: (questionId: number, answerIndex: number) => void
    goNextQuestion: () => void
    goPreviousQuestion: () => void
    reset: () => void
}

/*
Middleware
const logger = (config) => (set, get, api) => {
    return config(
        (...args) => {
            console.log('before')
            set(...args)
            console.log('after')
        },
        get,
        api
    )
}
*/


// set para actualizar y get para leer
// persist es el middleware de zustand
//export const useQuestionsStore = create<State>()(devtools(logger(persist((set, get) => {
export const useQuestionsStore = create<State>()(devtools(persist((set, get) => {
    return {
        questions: [], // <--- estado que se actualizará
        currentQuestion: 0,
        fetchQuestions: async (limit: number) => {
            const res = await fetch('http://localhost:5173/data.json')
            const json = await res.json()
            const questions = json.sort(() => Math.random() - 0.5).slice(0, limit)
            set({ questions })
        },
        selectAnswer: (questionId, answerIndex) => {
            const { questions } = get()
            const newQuestions = structuredClone(questions) // No se puede mutar el estado como en redux
            const questionIndex = newQuestions.findIndex(q => q.id === questionId)
            const questionInfo = newQuestions[questionIndex]
            
            const isCorrectUserAnswer = questionInfo.correctAnswer === answerIndex
            if (isCorrectUserAnswer) confetti()

            newQuestions[questionIndex] = {
                ...questionInfo,
                isCorrectUserAnswer: isCorrectUserAnswer,
                userSelectedAnswer: answerIndex
            }

            set({ questions: newQuestions })
        },
        goNextQuestion: () => {
            const { currentQuestion, questions } = get()
            const nextQuestion = currentQuestion + 1
            
            if (nextQuestion < questions.length)
                set({ currentQuestion: nextQuestion })
        },
        goPreviousQuestion: () => {
            const { currentQuestion } = get()
            const prevQuestion = currentQuestion - 1
            
            if (prevQuestion >= 0)
                set({ currentQuestion: prevQuestion })
        },
        reset: () => {
            set({ currentQuestion: 0, questions: [] })
        }
    }
}, {
    name: 'questions', // solo necesario para la persistencia
    getStorage: () => localStorage // default
})))