import { ArrowBackIosNew, ArrowForwardIos } from '@mui/icons-material'
import { Card, IconButton, List, ListItem, ListItemButton, ListItemText, Stack, Typography } from "@mui/material"
import SyntaxHighlighter from 'react-syntax-highlighter'
import { gradientDark } from 'react-syntax-highlighter/dist/esm/styles/hljs'
import { useQuestionsStore } from "../store/questions"
import { type Question as QuestionType } from "../types"


const Question = ({ info }: { info: QuestionType }) => {
    const selectAnswer = useQuestionsStore(state => state.selectAnswer)

    const createHandlerClick = (answerIndex: number) => () => {
        selectAnswer(info.id, answerIndex)
    }

    const getBackGroundcColor = (index: number) => {
        const { userSelectedAnswer, correctAnswer } = info

        if (userSelectedAnswer == null) return 'transparent'
        if (index !== correctAnswer && index !== userSelectedAnswer ) return 'transparent'
        if (index === correctAnswer ) return 'green'
        if (index === userSelectedAnswer ) return 'red'

        return 'transparent'

    }

    return (
        <Card variant='outlined' sx={{ bgcolor: '#222', p: 2 , textAlign: 'left', marginTop: 4 }}>
            <Typography variant='h5'>
                {info.question}
            </Typography>
            
            <SyntaxHighlighter language='javascript' style={ gradientDark }>
                {info.code}
            </SyntaxHighlighter>
            
            <List sx={{ bgcolor: '#333' }} disablePadding>
                {
                    //onClick={() => { selectAnswer(info.id, answerIndex) }}
                    info.answers.map((answer, index) => (
                        <ListItem key={index} disablePadding divider>
                            <ListItemButton disabled={info.userSelectedAnswer != null} onClick={createHandlerClick(index)} sx={{ bgcolor: getBackGroundcColor(index) }}>
                                <ListItemText primary={answer} sx={{ textAlign: 'center' }} />
                            </ListItemButton>
                        </ListItem>
                    ))
                }
            </List>
        </Card>
    )
}

export const Game = () => {
    const questions = useQuestionsStore(state => state.questions)
    const currentQuestion = useQuestionsStore(state => state.currentQuestion)
    const goNextQuestion = useQuestionsStore(state => state.goNextQuestion)
    const goPreviousQuestion = useQuestionsStore(state => state.goPreviousQuestion)
    
    const qustionInfo = questions[currentQuestion]

    return (
        <>
            <Stack direction='row' gap={2} alignItems='center' justifyContent='center'>
                <IconButton onClick={goPreviousQuestion} disabled={currentQuestion === 0}>
                    <ArrowBackIosNew />
                </IconButton>
                {currentQuestion}/{questions.length}
                <IconButton onClick={goNextQuestion} disabled={currentQuestion >= questions.length - 1}>
                    <ArrowForwardIos />
                </IconButton>
            </Stack>
            <Question info={qustionInfo} />
        </>
    )
}

// 45:00