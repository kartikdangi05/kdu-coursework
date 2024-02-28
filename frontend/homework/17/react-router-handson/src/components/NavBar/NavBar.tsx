const navStyle = {
  navbar: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    padding: '0.5%',
    backgroundColor: 'rgb(36, 36, 138)',
    color: 'white',
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


  return (
    <div style={navStyle.navbar}>
      <div style={navStyle.search}>
        <input  style={navStyle.inputSearch} type="text" placeholder="Search.." />
        <button style={navStyle.searchBtn} aria-label="Search">
          <i className="fi fi-rr-search"></i>
        </button>
      </div>
      <div style={navStyle.filterSearch}>
        <p style={navStyle.filter}>Filter :</p>
        <select style={navStyle.select}>
          <option value="">None</option>
        </select>
        <p style={navStyle.sort}>Sort : </p>
        <select style={navStyle.select}>
          <option value="">None</option>
          <option value="ASC">ASC</option>
          <option value="DESC">DESC</option>
        </select>
      </div>
    </div>
  )
}
