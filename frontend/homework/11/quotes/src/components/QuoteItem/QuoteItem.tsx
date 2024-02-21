import QuoteTag from "./QuoteTag"
import { IDataItem } from "../Quotes/Quotes"
import './QuoteItem.scss'

interface IQuoteItemProps {
    quote: IDataItem;
    addTag: (tag: string) => void;
    deleteTag: (tag: string) => void;
}

export default function QuoteItem({quote,addTag,deleteTag} : Readonly<IQuoteItemProps>) {
  return (
    <div className="quote-item">
        <div className="quote-content">
            {quote.content}
        </div>
        <div className="quote-author">
            ~ {quote.author}
        </div>
        <div className="quote-date">
            {quote.dateModified}
        </div>
        <div className="quote-tags">
            {
                quote.tags.map((tag) =>{
                    return <QuoteTag key={tag} tag={tag} addTag={() => addTag(tag)}/>
                })
            }
        </div>
    </div>
  )
}
