import { VscTrash } from 'react-icons/vsc';

const Item = () => {
  return (
    <div className="cart__item flex">
      <a className="item__image" href="">
        <img
          src="//bizweb.dktcdn.net/100/331/067/products/308066421-5438382269603243-2253586239615982651-n.jpg?v=1663678985660"
          alt=""
        />
      </a>
      <div className="item__detail">
        <a className="title" href="">
          <b>Multiple Person Hahaha asdfsadfsadf</b>
        </a>
        <p className="price">350.000đ</p>
        <div className="quantity-select">
          <button className="decrese">-</button>
          <span>1</span>
          <button className="increase">+</button>
        </div>
      </div>
      <div className="item__delete text-2xl cursor-pointer">
        <VscTrash />
      </div>
    </div>
  );
};

export default Item;
