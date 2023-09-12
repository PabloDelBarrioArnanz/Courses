import { Stack, Typography } from "@mui/material"
import { JavaScriptLogo } from "./JavaScriptLogo"

export const TitleLogo = () => {
    return (
        <Stack direction='row' gap={2} alignItems='center' justifyContent='center' sx={{marginTop: 6}}>
            <JavaScriptLogo />
            <Typography variant='h2' component='h1'>
                Javascript Quizz
            </Typography>
        </Stack>
    )
}