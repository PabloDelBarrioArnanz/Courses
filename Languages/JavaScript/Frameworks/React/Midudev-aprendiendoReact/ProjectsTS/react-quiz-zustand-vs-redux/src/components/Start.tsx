import { Button } from "@mui/material"
import { LIMIT_QUESTIONS } from "../constants"
import { useQuestionsStore } from "../store/questions"

export const Start = () => {
    const fetchQuestions = useQuestionsStore(state => state.fetchQuestions)

    return (
        <Button sx={{ marginTop: 4 }} onClick={() => fetchQuestions(LIMIT_QUESTIONS)} variant='contained'>
            Empezar!
        </Button>
    )
}