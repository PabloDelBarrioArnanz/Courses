
import { ChatCompletionRequestMessageRoleEnum, Configuration, OpenAIApi } from "openai"
import { AUTO_LANGUAGE, SUPPORTED_LANGUAGES } from "../constants"
import { FromLanguage, Language } from "../types.d"

const apiKey = ''

const configuration = new Configuration({ apiKey })
const openai = new OpenAIApi(configuration)

export async function translate({
  fromLanguage,
  toLanguage,
  text
}: {
  fromLanguage: FromLanguage
  toLanguage: Language
  text: string
}) {
  const messages = [
    {
      role: ChatCompletionRequestMessageRoleEnum.System,
      content: 'You are a IA that translate text. You receive a text from the user. Do not answer, just translate the text. The original language is surrounded by `{{` and `}}`. You can translate to any language. The language you translate to is surroundede by `[[` and `]]`.'    
    },
    {
      role: ChatCompletionRequestMessageRoleEnum.User,
      content: `Hola mundo {{Español}} [[English]]`
    },
    {
      role: ChatCompletionRequestMessageRoleEnum.Assistant,
      content: `Hello world`
    },
    {
      role: ChatCompletionRequestMessageRoleEnum.User,
      content: `How are you? {{English}} [[Deutsch]]`
    },
    {
      role: ChatCompletionRequestMessageRoleEnum.Assistant,
      content: `Wie geht es dir?`
    },
    {
      role: ChatCompletionRequestMessageRoleEnum.User,
      content: `Bon dia, com estas? {{auto}} [[Español]]`
    },
    {
      role: ChatCompletionRequestMessageRoleEnum.Assistant,
      content: `Buenos días, como estás?`
    }
  ]

  const fromLanguageCode = fromLanguage === AUTO_LANGUAGE ? AUTO_LANGUAGE : SUPPORTED_LANGUAGES[fromLanguage]
  const toLanguageCode = SUPPORTED_LANGUAGES[toLanguage]

  const completion = await openai.createChatCompletion({
    model: 'gpt-3.5-turbo',
    messages: [
      ...messages,
      {
        role: ChatCompletionRequestMessageRoleEnum.User,
        content: `${text} {{${fromLanguageCode}}}, [[${toLanguageCode}]]`
      }
    ]
  })

  return completion.data.choices[0]?.message?.content
}

