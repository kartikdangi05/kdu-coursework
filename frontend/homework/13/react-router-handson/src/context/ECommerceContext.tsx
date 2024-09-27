import { Children, createContext, useState } from "react";

export interface IProduct{
    id: number;
    title: string;
    price: number;
    description: string;
    category: string;
    image: string;
    rating: {
        rate: number;
        count: number;
    };
}

export interface ECommerceType{
    productLists: IProduct[]
    product: IProduct | null
    loading: boolean
    toggleLoading: (state : boolean) => void 
    setProducts: (response : IProduct[]) => void
    filter: string
    sort: string
    handleFilter: (filter : string) => void
    handleSort: (sort : string) => void
    search: string
    handleSearch: (searchStr : string) => void
    filterProductLists: IProduct[]
    handleLists: (filterProductLists : IProduct[]) => void
    handleProduct: (product : IProduct) => void
}

interface Children{
    children: React.ReactNode
}

export const ECommerceContext = createContext<ECommerceType>({
    productLists: [],
    product: null,
    loading: false,
    toggleLoading: (state : boolean) => {},
    setProducts: (response : IProduct[]) => {},
    filter: '',
    sort: '',
    handleFilter: (filter : string) => {},
    handleSort: (sort : string) => {},
    search: '',
    handleSearch: (searchStr : string) => {},
    filterProductLists: [],
    handleLists: (filterProductLists : IProduct[]) => {},
    handleProduct: (product : IProduct) => {}
})


export const ECommerceProvider = ({children} : Children) => {

    const [productLists, setProductLists] = useState<IProduct[]>([]);
    const [product,setProduct] = useState<IProduct | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
    const [filter, setFilter] = useState<string>("");
    const [sort, setSort] = useState<string>("");
    const [search, setSearch] = useState<string>("");
    const [filterProductLists, setFilterProductLists] = useState<IProduct[]>(productLists);

    const handleSearch = (searchStr : string) => {
        setSearch(searchStr);
    }

    const toggleLoading = (state : boolean) => {
        setLoading(state);
    }

    const setProducts = (response : IProduct[]) => {
        setProductLists(response);
    }

    const handleFilter = (filter : string) => {
        setFilter(filter);
    }

    const handleSort = (sort : string) => {
        setSort(sort);
    }

    const handleLists = (filterProductLists : IProduct[]) => {
        setFilterProductLists(filterProductLists);
    }

    const handleProduct = (product : IProduct) => {
        setProduct(product);
    }

    const value : ECommerceType = {
        productLists,
        product,
        loading,
        toggleLoading,
        setProducts,
        filter,
        sort,
        handleFilter,
        handleSort,
        search,
        handleSearch,
        filterProductLists,
        handleLists,
        handleProduct
    }

    return(
        <ECommerceContext.Provider value={value}>
            {children}
        </ECommerceContext.Provider>
    )

}