import './CartButton.scss';

import { BsCart } from 'react-icons/bs';
import ListItem from './ListItem/ListItem';

const CartButton = () => {
  return (
    <div className="cart-button ml-6">
      <div className="cart__total-item">0</div>
      <BsCart className="text-2xl" />
      <ListItem />
    </div>
  );
};

export default CartButton;
