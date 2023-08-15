import { Form } from 'react-bootstrap'
import { SectionType } from '../types.d'

type Props =
    | { type: SectionType.From, loading?: undefined, value: string, onChange: (value: string) => void }
    | { type: SectionType.To, loading: boolean, value: string, onChange: (value: string) => void }

const getPlaceholder = ({ type, loading }: { type: SectionType, loading: boolean }): string => {
    if (type === SectionType.From) return 'Introducir texto'
    if (loading) return 'Traduciendo...'
    return 'Traducción'
}

export const TextArea: React.FC<Props> = ({ loading, type, value, onChange }) => {
  const isFromType = type === SectionType.From
  
  const commonStyles = { border: '0px', height: '200px', resize: 'none'}
  const style = isFromType ? 
    { ...commonStyles, border: '1px solid #6D6868' } :
    { ...commonStyles, backgroundColor: '#f5f5f5' }

  return (
    <Form.Control
      as='textarea' // que elemento renderizar 
      placeholder={ getPlaceholder({ type, loading: loading ??= false }) }
      style={style}
      autoFocus={isFromType}
      value={ loading ? 'Traduciendo...' : value }
      onChange={ event => { onChange(event.target.value) }}
    />
  )
}