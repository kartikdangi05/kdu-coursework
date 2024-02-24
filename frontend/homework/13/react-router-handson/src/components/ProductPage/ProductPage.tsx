import { useContext, useEffect } from "react"
import { ECommerceContext } from "../../context/ECommerceContext"
import { useParams } from "react-router-dom";
import Loader from "../Loader/Loader";

const productStyle = {
    main: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        backgroundColor: 'rgb(217, 217, 224)',
        height: '100vh'
    },
    header: {
        fontSize: 'xx-large',
        padding: '5%',
        flex: '1',
        textAlign: 'center'
    },
    item: {
        display: 'flex',
        justifyContent: 'space-around',
        alignItems: 'center',
        width: '100%',
        flex: '5'
    },
    image: {
        height: '40vh',
        width: '40vh',
        padding: '0 15%',
        alignSelf: 'flex-start'
    },
    info: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'flex-start',
        alignSelf: 'flex-start',
        flex: '3',
        fontSize: '30px',
        marginRight: '8%'
    },
    title: {
        fontWeight: '600',
        marginBottom: '5%'
    },
    category: {
        fontWeight: '600',
        color: 'gray',
        marginBottom: '1.75%'
    },
    price: {
        fontWeight: '600',
        color: 'rgb(36, 36, 138)',
        marginBottom: '3.5%'
    },
    description: {
        fontSize: '25px',
        fontWeight: '600',
        marginBottom: '1.5%'
    },
    article: {
        fontSize: '25px',
    }
}

export default function ProductPage() {

    const {product, handleProduct, toggleLoading, loading} = useContext(ECommerceContext);
    const { id } = useParams();
    const { productLists } = useContext(ECommerceContext);

    useEffect(() => {
        toggleLoading(true);
        if (id) {
            const productId = parseInt(id);
            const prod = productLists.find(product => product.id === productId);
            if(prod)
                handleProduct(prod);
        }
        toggleLoading(false);
    },[productLists])
    return (
        <>
            {
                loading === true ? <Loader/> :
                product != null
                    ?
                    <div style={productStyle.main as React.CSSProperties}>
                        <h1 style={productStyle.header as React.CSSProperties}>{product.title}</h1>
                        <div style={productStyle.item}>
                            <img style={productStyle.image} src={product.image} alt={product.title} />
                            <div style={productStyle.info as React.CSSProperties}>
                                <p style={productStyle.title}>Product : {product.title}</p>
                                <p style={productStyle.category}>Category : {product.category}</p>
                                <p style={productStyle.price}>Price : $ {product.price}</p>
                                <p style={productStyle.description}>Product Description : </p>
                                <article style={productStyle.article}>{product.description}</article>
                            </div>
                        </div>
                    </div>
                    :
                    <h1>Product not found!</h1>
            }
        </>
    )
}
