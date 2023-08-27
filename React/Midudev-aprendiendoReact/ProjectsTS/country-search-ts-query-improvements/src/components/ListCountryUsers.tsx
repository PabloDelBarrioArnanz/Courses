import { type CountryUser } from "../types"

interface Props {
    users: CountryUser[]
    colorCells: boolean
    removeUser: (id: string) => void
}

export const ListCountryUsers: React.FC<Props> = ({ users, colorCells, removeUser }) => {
    return (
        <table>
            <thead>
                <tr>
                    <th>Foto</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>País</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody className={colorCells ? "table--showColors" : ""}>
                {
                    users?.map(user => (
                        <tr key={user.id}>
                            <td><img src={user.photo} alt="User photo"/></td>
                            <td>{user.name}</td>
                            <td>{user.surname}</td>
                            <td>{user.country}</td>
                            <td><button onClick={() => removeUser(user.id)}>Delete</button></td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
    )
}
