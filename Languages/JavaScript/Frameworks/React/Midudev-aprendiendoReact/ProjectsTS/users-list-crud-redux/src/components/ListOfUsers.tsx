import { Card, Table, TableRow, TableCell, TableHead, TableHeaderCell, TableBody, Badge, Title } from "@tremor/react"
import { DeleteIcon, EditIcon } from './Icons'
import { useAppSelector } from '../hooks/store'
import { useUserActions } from '../hooks/useUsersActions'

export const ListOfUsers = () => {
    const users = useAppSelector((state) => state.users) // users por el nombre del slice configurado en el store
    const { removeUser } =  useUserActions()
    return (
        <Card>
            <Title>
                Usuarios
                <Badge style={{ marginLeft: "8px" }}>
                    {users.length}
                </Badge>
            </Title>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableHeaderCell> Id </TableHeaderCell>
                        <TableHeaderCell> Nombre </TableHeaderCell>
                        <TableHeaderCell> Email </TableHeaderCell>
                        <TableHeaderCell> Acciones </TableHeaderCell>
                    </TableRow>
                </TableHead>

                <TableBody>
                    {
                        users.map((user) => (
                            <TableRow key={user.id}>
                                <TableCell>{user.id}</TableCell>
                                <TableCell style={{ display: "flex", alignItems: "center" }}>
                                    <img
                                        style={{ width: "32px", height: "32px", borderRadius: "50%", marginRight: "8px" }}
                                        src={`https://unavatar.io/github/${user.github}`}
                                        alt={user.name}
                                    />
                                    {user.name}
                                </TableCell>
                                <TableCell>{user.email}</TableCell>
                                <TableCell>Accciones</TableCell>
                                <TableCell>
                                    <EditIcon />
                                </TableCell>
                                <TableCell>
                                    <button type="button" onClick={ () => removeUser(user.id) } >
                                        <DeleteIcon />
                                    </button>
                                </TableCell>
                            </TableRow>
                        ))
                    }
                </TableBody>
            </Table>
        </Card>
        )
}