import Item from './Item/Item';

const ListItem = () => {
  return (
    <div className="cart__items">
      <Item />
      <div className="cart__total-price flex justify-between py-3">
        <span>Total:</span>
        <b>1.150.000đ</b>
      </div>
      <div className="cart__actions grid grid-cols-2 gap-4">
        <button>Payment</button>
        <button>Cart</button>
      </div>
    </div>
  );
};

export default ListItem;
