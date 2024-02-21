import {FaTimes} from 'react-icons/fa'

interface IHeaderTag{
    tag: string
    deleteTag: () => void
}

export default function HeaderTag({tag, deleteTag} : Readonly<IHeaderTag>) {
  return (
    <div className="header-tag">
        <p>{tag}</p>
        <button onClick={deleteTag} id='delete-btn'><FaTimes /></button>
    </div>
  )
}
