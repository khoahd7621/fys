import { useNavigate } from 'react-router-dom';
import classNames from 'classnames/bind';
import styles from '../MiniCart.module.scss';

import { publicRoutes } from '~/routes/routes';

import Item from './Item/Item';

const cx = classNames.bind(styles);

const ContentBox = () => {
  const navigate = useNavigate();
  return (
    <div className={cx('cart__items')}>
      <div className={cx('list-items')}>
        <Item />
      </div>
      <div className={cx('cart__total-price', 'flex justify-between py-3')}>
        <span>Total:</span>
        <b>1.150.000đ</b>
      </div>
      <div className={cx('cart__actions', 'grid grid-cols-2 gap-4')}>
        <button onClick={() => navigate(publicRoutes.checkout)}>Checkout</button>
        <button onClick={() => navigate(publicRoutes.cart)}>Cart</button>
      </div>
    </div>
  );
};

export default ContentBox;
