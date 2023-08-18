import { Toaster } from 'sonner'
import './App.css'
import { CreateNewUser } from "./components/CreateNewUser.tsx"
import { ListOfUsers } from "./components/ListOfUsers.tsx"

export const App = () => {

  return (
    <>
      <ListOfUsers />
      <CreateNewUser />
      <Toaster richColors />
    </>
  )
}