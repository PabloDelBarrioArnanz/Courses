import { Container } from "@mui/material"
import { Footer } from "./components/Footer"
import { Game } from "./components/Game"
import { Start } from "./components/Start"
import { TitleLogo } from "./components/TitleLogo"
import './main.css'
import { useQuestionsStore } from "./store/questions"

export const App = () => {
	const questions = useQuestionsStore(state => state.questions)

  	return (
    	<main>
			<Container maxWidth="sm">
				<TitleLogo />

				{ questions.length === 0 && <Start /> }
				{ questions.length > 0 && <Game /> }
				{ questions.length > 0 && <Footer /> }

			</Container>
    	</main>
  	)
}
