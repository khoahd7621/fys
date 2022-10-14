import classNames from 'classnames/bind';
import styles from './Product.module.scss';

import { Link } from 'react-router-dom';

import { BsLink45Deg } from 'react-icons/bs';
import { FaCartArrowDown, FaCartPlus } from 'react-icons/fa';

const cx = classNames.bind(styles);

const Product = () => {
  return (
    <div className={cx('product-block')} title={'Product'}>
      <div className={cx('thumb')}>
        <Link className={cx('primary-img')} to={''}>
          <img
            src={
              'https://bizweb.dktcdn.net/thumb/large/100/331/067/products/308066421-5438382269603243-2253586239615982651-n.jpg?v=1663678985000'
            }
            alt="primary-img"
          />
        </Link>
        <Link className={cx('secondary-img')} to={''}>
          <img
            src={
              'https://bizweb.dktcdn.net/thumb/large/100/331/067/products/307980002-5438382122936591-6042481426817014800-n.jpg?v=1663678985000'
            }
            alt="secondary-img"
          />
        </Link>
        <div className={cx('label')}>
          <span className={cx('sale')}>40%</span>
        </div>
        <div className={cx('cart-wrap')}>
          <button className={cx('cart-button')}>
            <BsLink45Deg />
          </button>
          <button className={cx('cart-button')}>
            <FaCartArrowDown />
          </button>
          <button className={cx('cart-button')}>
            <FaCartPlus />
          </button>
        </div>
      </div>
      <div className={cx('content')}>
        <div className={cx('name')}>
          <h4>
            <Link to={''}>Lively & Active Cap</Link>
          </h4>
        </div>
        <div className={cx('price')}>
          <span className={cx('current')}>180.000đ</span>
          <span className={cx('old')}>280.000đ</span>
        </div>
      </div>
    </div>
  );
};

export default Product;
