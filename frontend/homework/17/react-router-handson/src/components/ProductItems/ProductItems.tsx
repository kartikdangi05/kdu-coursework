import { useEffect } from "react";
import ProductItem from "./ProductItem";
import Loader from "../Loader/Loader";
import { useDispatch, useSelector } from "react-redux";
import { DispatchType, RootState } from "../../redux/Store";
import { fetchProducts } from "../../redux/ProducrReducer";
import Snackbar from "../Snackbar/Snackbar";
import { showSnackbar } from "../../redux/SnackbarReducer";

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

  const {productsList, status} = useSelector((state : RootState) => state.products);
  console.log(status);
  const dispatch = useDispatch<DispatchType>();
  
  useEffect(() => {
    dispatch(fetchProducts());
  }, []);

  if(status === "succeeded")
    dispatch(showSnackbar({type: 'success', message: 'Fetched Products successfully!'}))
  
  if(status === "failed")
    dispatch(showSnackbar({type: 'fail', message: 'Error while fetching products!'}))


  return (
    
    <div style={itemsStyle.main}>
        <h1 style={itemsStyle.heading}>
          <span>KDU </span><span style={itemsStyle.headingDesign}> MARKETPLACE</span>
        </h1>
        {status == "loading" ? <Loader/> : ''}
        <div style={itemsStyle.items as React.CSSProperties}>
          {
              productsList.map((product) => {
                return <ProductItem key={product.id} product={product}/>
              }) 
          }
        </div>
        {
          status == "succeeded" || status == "failed" ? <Snackbar/> : ''
        }
    </div>
  )
}
