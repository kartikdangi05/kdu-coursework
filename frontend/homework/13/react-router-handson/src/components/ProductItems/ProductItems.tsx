import { useContext, useEffect } from "react";
import { ECommerceContext, IProduct } from "../../context/ECommerceContext";
import ProductItem from "./ProductItem";
import Loader from "../Loader/Loader";

const itemsStyle = {
  main: {
    backgroundColor: 'rgb(217, 217, 224)',
    padding: '2% 4%'
  },
  items : {
    display: 'flex',
    justifyContent: 'flex-start',
    alignItems: 'center',
    flexWrap: 'wrap',
    margin: '0 auto'
  },

  heading : {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    fontSize: '45px'
  },

  headingDesign : {
    color: 'rgb(36, 36, 138)',
    marginLeft: '2%'
  }
}

export default function ProductItems() {
  const {productLists,loading,filter,sort,search,filterProductLists,handleLists,handleFilter,handleSort} = useContext(ECommerceContext);

  useEffect(() => {
    changerFunc(productLists);
  },[search,filter,sort,productLists])

  const sortByPriceAsc = (a:IProduct, b:IProduct) => {
    return a.price - b.price;
  };

  const sortByPriceDesc = (a:IProduct, b:IProduct) => {
    return b.price - a.price;
  };

  /**
   * For showing filtered results
   * @param productLists 
   */
  const changerFunc = (productLists : IProduct[]) => {
    let resultArr = productLists;
  
    if(filter.length){
      resultArr = productLists.filter((product) => product.category === filter);
    }

    if(sort.length){
      if(sort == "ASC") resultArr = resultArr.sort(sortByPriceAsc);
      else  resultArr = resultArr.sort(sortByPriceDesc);
    }

    if(search.length){
      resultArr = resultArr.filter((product) => product.title.toLowerCase().includes(search));
    }

    handleLists(resultArr);
  }

  /**
   * For handling query params 
   */
  const params = new URLSearchParams(document.location.search);
  if(params.has('filter')){
    let filterVal = params.get('filter');
    let sortVal = params.get('sort');
    if(filterVal!=null){
      handleFilter(filterVal);
    }
    if(sortVal!=null){
      handleSort(sortVal);
    }
  }

  return (
    
    <div style={itemsStyle.main}>
        <h1 style={itemsStyle.heading}>
          <span>KDU </span><span style={itemsStyle.headingDesign}> MARKETPLACE</span>
        </h1>
        {loading ? <Loader/> : ''}
        <div style={itemsStyle.items as React.CSSProperties}>
          {
              filterProductLists.length == 0 ? 
              productLists.map((product) => {
                return <ProductItem key={product.id} product={product}/>
              }) :
              filterProductLists.map((product) => {
                return <ProductItem key={product.id} product={product}/>
              })
          }
        </div>
    </div>
  )
}
