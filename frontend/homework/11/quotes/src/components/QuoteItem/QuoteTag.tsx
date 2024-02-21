import './QuoteTag.scss'

interface ITag {
    tag: string;
    addTag: () => void;
}

export default function QuoteTag({tag, addTag} : Readonly<ITag>) {
  return (
    <div className="quote-tag">
        <button onClick={addTag} id='tag'>{tag}</button>
    </div>
  )
}
