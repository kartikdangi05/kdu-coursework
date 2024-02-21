import QuoteItem from "../QuoteItem/QuoteItem"
import { IDataItem } from "./Quotes"
import './Quote.scss'

interface IQuotes {
    data: IDataItem[],
    addTag: (tag: string) => void,
    deleteTag: (tag: string) => void,
    tags: Set<string>
}

export default function Quote({ data, addTag, deleteTag, tags }: Readonly<IQuotes>) {

    const filterArray = data.filter((quote) => {
        return tags.size === 0 || Array.from(tags).every(tag => quote.tags.includes(tag));
    });
    

    return (
        <div className="quote-container">
            {
                filterArray.length !== 0 ? filterArray.map((quote) => {
                    return <QuoteItem key={quote._id} quote={quote} addTag={addTag} deleteTag={deleteTag} />
                }) : data.map((quote) => {
                    return <QuoteItem key={quote._id} quote={quote} addTag={addTag} deleteTag={deleteTag} />
                })
            }
        </div>
    )
}
