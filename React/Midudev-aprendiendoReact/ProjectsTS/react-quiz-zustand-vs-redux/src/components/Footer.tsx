import { Button } from "@mui/material"
import { useQuestionsData } from "../hooks/useQuestionsData"
import { useQuestionsStore } from "../store/questions"

export const Footer = () => {
    const { correctQuestions, incorrectQuestions, unAnsweredQuestions } = useQuestionsData()
    const reset = useQuestionsStore(state => state.reset)

    return (
        <footer style={{ marginTop: '16px'}}>
            <strong>{`✅ ${correctQuestions}`} Correctas - {`❌ ${incorrectQuestions}`} - Incorrectas {`❓ ${unAnsweredQuestions}`} - Pendientes</strong>
        <div style={{ marginTop: '16px'}}>
            <Button onClick={() => reset()}>
                Resetear Juego
            </Button>
        </div>
        </footer>
    )
}