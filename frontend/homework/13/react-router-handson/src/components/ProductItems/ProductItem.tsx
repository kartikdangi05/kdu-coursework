import { Link } from "react-router-dom";
import { IProduct } from "../../context/ECommerceContext"

const itemStyle = {
  item: {
    display: 'flex',
    flexBasis: '22%',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    fontSize: '17px',
    backgroundColor: 'white',
    marginTop: '2%',
    marginRight: '1%',
    marginLeft: '1.2%',
    padding: '1% 0.4%',
    textDecoration: 'none',
    color: 'black'
  },
  link: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    flexDirection: 'column',
    textDecoration: 'none',
    color: 'black',
    width: '100%',
  },
  image: {
    height: '15vh',
    width: '15vh'
  },
  titlePrice: {
    display: 'flex',
    alignItems: 'flex-start',
    justifyContent: 'center',
    textAlign: 'center',
    height: '16vh',
    paddingTop: '15%',
    width: '100%',
    fontSize: 'larger'
  },
  title: {
    flex: '3',
    fontWeight: '550',
    fontSize: '21px'
  },
  price: {
    flex: '1',
    fontWeight: '600',
    color: 'rgb(36, 36, 138)',
    fontSize: '24px',
  },
  rating: {
    fontSize: '25px'
  },
  rate: {
    color: 'green',
    fontSize: '28px'
  }
};

export default function ProductItem({ product }: Readonly<{ product: IProduct }>) {
  return (
    <div style={itemStyle.item as React.CSSProperties}>
      <Link to={`/product/${product.id}`} style={itemStyle.link as React.CSSProperties}>
        <img src={product.image} alt={product.title} style={itemStyle.image} />
        <div style={itemStyle.titlePrice as React.CSSProperties} >
          <p style={itemStyle.title}>{product.title}</p>
          <p style={itemStyle.price}>$ {product.price}</p>
        </div>
      </Link>
      <div>
        <p style={itemStyle.rating}>Ratings : <span style={itemStyle.rate}>{product.rating.rate}</span> ({product.rating.count})</p>
      </div>
    </div>
  )
}
