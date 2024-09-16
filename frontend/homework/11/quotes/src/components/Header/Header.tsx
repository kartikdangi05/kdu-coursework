import HeaderTag from "./HeaderTag";
import './Header.scss'

interface ISearch{
  newQuote: () => void,
  tags : Set<string>,
  deleteTag: (tag : string) => void
  loading: boolean
}

export default function Header({newQuote, tags, deleteTag,loading} : Readonly<ISearch>) {

  const filters = Array.from(tags);

  return (
    <header className='heading'>
      <button onClick={() => newQuote()} id="newQuote" disabled={loading===true}>NEW QUOTE</button>
      <p className="filter-heading">Filters</p>
      <div className="filters">
        {
            filters.map((tag : string) => {
                return <HeaderTag key={tag} tag={tag} deleteTag={() => deleteTag(tag)}/>
            })
        }
      </div>
    </header>
  );
}