import { Form } from 'react-bootstrap'
import { AUTO_LANGUAGE, SUPPORTED_LANGUAGES } from '../constants'
import { SectionType, type FromLanguage, type Language } from '../types.d'

type Props =
    | { type: SectionType.From, value: FromLanguage, targetValue: Language, onChange: (language: FromLanguage) => void  }
    | { type: SectionType.To, value: Language, targetValue: FromLanguage, onChange: (language: Language) => void  }

//export const LanguageSelector = ({ onChange }: { onChange: (language: string) => void }) => {
//export const LanguageSelector = ({ onChange }: Props) => {
export const LanguageSelector: React.FC<Props> = ({ type, value, targetValue, onChange }) => {
    const handleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        event.preventDefault()
        onChange(event.target.value as Language)
    }
    
    return (
        <Form.Select aria-label='Selecciona el idioma' onChange={handleChange} value={value}>
            { type === SectionType.From && <option value={AUTO_LANGUAGE}>Detectar idioma</option> }
            {
                Object.entries(SUPPORTED_LANGUAGES)
                    .filter(([key, _]) =>  key !== targetValue)
                    .map(([key, literal]) => (
                        <option key={key} value={key}>
                            {literal}
                        </option>))
            }
        </Form.Select>
    )
}