
export const Square = ({children, isSelected, updateBoard}) => {
    const className = `square ${isSelected ? 'is-selected' : ''}`
    return (
        <div onClick={updateBoard} className={className}>
            {children}
        </div>
    )
} 