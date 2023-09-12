import 'bootstrap/dist/css/bootstrap.min.css'
import { useEffect } from 'react'
import { Button, Col, Container, Row, Stack } from 'react-bootstrap'
import './App.css'
import { ArrowsIcon, ClipboardIcon, SpeakerIcon } from './components/Icons'
import { LanguageSelector } from './components/LanguageSelector'
import { TextArea } from './components/TextArea'
import { AUTO_LANGUAGE } from './constants'
import { useDebounce } from './hooks/useDebounce'
import { useStore } from './hooks/useStore'
import { SectionType } from './types.d'

export const App = () => {
  const { 
    fromLanguage,
    toLanguage,
    fromText,
    result,
    loading,
    interChangeLanguages,
    setFromLanguage,
    setToLanguage,
    setFromText,
    setResult
  } = useStore()

  const debouncedText = useDebounce(fromText, 250)

  useEffect(() => {
    if (debouncedText == null || debouncedText === '') {
      setResult('')
      return
    }
    
    /*
    translate({ fromLanguage, toLanguage, text: fromText })
      .then(result => {
        if (result == null) return
        setResult(result)
      })
      .catch(() => { setResult('Error') })
    */
    setResult(debouncedText)
  }, [debouncedText, fromLanguage, toLanguage])

  const handleSpeek = () => {
    const utterance = new SpeechSynthesisUtterance(result)
    utterance.lang = toLanguage
    utterance.rate = 0.9
    speechSynthesis.speak(utterance)
  }

  return (
    <Container fluid>
      <h1>Google Translate</h1>
      <Row>
        <Col>
          <Stack gap={2}>
            <LanguageSelector type={SectionType.From} value={fromLanguage} targetValue={toLanguage} onChange={setFromLanguage} />
            <TextArea type={SectionType.From} value={fromText} onChange={setFromText} />
          </Stack>
        </Col>

        <Col xs='auto'>
          <Button variant='link' disabled={fromLanguage === AUTO_LANGUAGE} onClick={interChangeLanguages}>
            <ArrowsIcon />
          </Button>
        </Col>

        <Col>
          <Stack gap={2}>
            <LanguageSelector type={SectionType.To} value={toLanguage} targetValue={fromLanguage} onChange={setToLanguage} />
            <div style={{ position: 'relative' }}>
              <TextArea type={SectionType.To} value={result} onChange={setResult} loading={loading} />
              <Button
                variant='link' 
                style={{ position: 'absolute', left: 0, bottom: 0 }} 
                onClick={() => { navigator.clipboard.writeText(result) }}
              >
                <ClipboardIcon />
              </Button>
              <Button
                variant='link' 
                style={{ position: 'absolute', left: 20, bottom: 0 }} 
                onClick={handleSpeek}
              >
                <SpeakerIcon />
              </Button>
            </div>
          </Stack>
        </Col>
      </Row>
    </Container>
  )
}
