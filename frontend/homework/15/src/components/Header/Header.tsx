import { useEffect, useState } from "react";
import './heading.scss'
import { useDispatch } from "react-redux";
import { searchTodo } from "../../redux/Reducer";

export default function Header() {
  const [search, setSearch] = useState("");
  const dispatch = useDispatch();

  useEffect(() => {
    searchFunc(search);
  },[search])

  const findItems = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearch(e.target.value);
  }

  const searchFunc = (search : string) => {
    dispatch(searchTodo(search));
  }
  
  return (
    <header className='heading'>
      <h1 className="item-lister">Item Lister</h1>
      <input
        type="search"
        id='search'
        placeholder='Search Items...'
        value={search}
        onChange={(e) => findItems(e)}
      />
    </header>
  );
}
