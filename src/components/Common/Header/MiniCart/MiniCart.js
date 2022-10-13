import classNames from 'classnames/bind';
import styles from './MiniCart.module.scss';

import { BsCart } from 'react-icons/bs';

import ContentBox from './ContentBox/ContentBox';

const cx = classNames.bind(styles);

const MiniCart = () => {
  return (
    <div className={cx('mini-cart', 'ml-6')}>
      <div className={cx('total-items')}>0</div>
      <BsCart className={cx('text-2xl')} />
      <ContentBox />
    </div>
  );
};

export default MiniCart;
