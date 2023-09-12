import { useQuestionsStore } from "../store/questions"

export const useQuestionsData = () => {
    const question = useQuestionsStore(state => state.questions)

    let correctQuestions = 0
    let incorrectQuestions = 0
    let unAnsweredQuestions = 0

    question.forEach(question => {
        if (question.userSelectedAnswer == null) unAnsweredQuestions++
        else if (question.isCorrectUserAnswer) correctQuestions++
        else incorrectQuestions++
    })

    return { correctQuestions, incorrectQuestions, unAnsweredQuestions }
}
