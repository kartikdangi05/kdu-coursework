import { useEffect, useState } from "react";

interface ISearch{
  searchFunc: (search : string) => void
}

export default function Header({searchFunc} : Readonly<ISearch>) {
  const [search, setSearch] = useState("");

  useEffect(() => {
    searchFunc(search);
  },[search])

  const findItems = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearch(e.target.value);
  }
  
  return (
    <header className='heading'>
      <h1>Item Lister</h1>
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
