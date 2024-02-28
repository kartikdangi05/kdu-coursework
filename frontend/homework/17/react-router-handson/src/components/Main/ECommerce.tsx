import NavBar from "../NavBar/NavBar"
import ProductItems from "../ProductItems/ProductItems"

export default function ECommerce() {

  return (
    <>
      <NavBar />
      <div className="main">
        <ProductItems/>
      </div>
    </>
  )
}
