import { useContext, useRef } from "react"
import { ECommerceContext } from "../../context/ECommerceContext"

const navStyle = {
  navbar: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    padding: '0.5%',
    backgroundColor: 'rgb(36, 36, 138)',
    color: 'white'
  },
  search: {
    flex: '1',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center'
  },
  filterSearch: {
    flex: '2',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    fontSize: '20px'
  },
  inputSearch: {
    padding: '1%',
    fontSize: '16px',
    border: '2px solid gray'
  },
  searchBtn: {
    border: '2px solid gray',
    padding: '1%',
    color: 'black',
    backgroundColor: 'rgb(217, 217, 224)'
  },
  filter: {
    marginRight: '0.5%'
  },
  sort: {
    marginLeft: '2%',
    marginRight: '0.5%'
  },
  select: {
    padding: '0.5%',
    fontSize: '17px'
  }
}

export default function NavBar() {

  const { productLists, filter, sort, handleFilter, handleSort, handleSearch } = useContext(ECommerceContext);

  const searchBarRef = useRef<HTMLInputElement>(null);

  const handleButtonClick = () => {
    if(searchBarRef.current != null)
      handleSearch(searchBarRef.current.value)
  };

  const category: Set<string> = new Set();
  productLists.map((product) => {
    category.add(product.category);
  })

  const categoryArr: string[] = Array.from(category);


  return (
    <div style={navStyle.navbar}>
      <div style={navStyle.search}>
        <input ref={searchBarRef} style={navStyle.inputSearch} type="text" placeholder="Search.." />
        <button style={navStyle.searchBtn} onClick={handleButtonClick} aria-label="Search">
          <i className="fi fi-rr-search"></i>
        </button>
      </div>
      <div style={navStyle.filterSearch}>
        <p style={navStyle.filter}>Filter :</p>
        <select style={navStyle.select} value={filter} onChange={(e) => handleFilter(e.target.value)}>
          <option value="">None</option>
          {
            categoryArr.map((category) => {
              return <option key={category} value={category}>{category}</option>
            })
          }
        </select>
        <p style={navStyle.sort}>Sort : </p>
        <select style={navStyle.select} value={sort} onChange={(e) => handleSort(e.target.value)}>
          <option value="">None</option>
          <option value="ASC">ASC</option>
          <option value="DESC">DESC</option>
        </select>
      </div>
    </div>
  )
}
