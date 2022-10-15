import classNames from 'classnames/bind';
import styles from './DetailBlock.module.scss';

import { GiPencilRuler } from 'react-icons/gi';
import { FaPhoneSquare, FaLocationArrow } from 'react-icons/fa';

import selectImg from '~/assets/images/product/select-pro.webp';

const cx = classNames.bind(styles);

const DetailBlock = () => {
  return (
    <div className={cx('detail-block')}>
      <div className={cx('top')}>
        <h1 className={cx('title')}>Invader t-shirt</h1>
        <div className={cx('sku')}>
          <div className={cx('item')}>
            SKU: <span className={cx('variant-sku')}>N103-S-B</span>
          </div>
          <div className={cx('item')}>
            Vendor: <span className={cx('vendor')}>Updating</span>
          </div>
        </div>
      </div>
      <div className={cx('offer')}>
        <div className={cx('price')}>
          <span className={cx('special')}>320.000đ</span>
          <span className={cx('old')}>400.000đ</span>
          <span className={cx('save')}>
            Save:
            <span>80.000đ</span>
          </span>
        </div>
        <div className={cx('inventory-quantity')}>
          <span className={cx('title')}>Status:</span>
          <span className={cx('in-stock')}>In stock</span>
        </div>
      </div>
      <div className={cx('select-swatch')}>
        <div className={cx('size')}>
          <div className={cx('header')}>
            <span>Size:</span>
            <button className={cx('size-table')}>
              <GiPencilRuler />
              <span>Size table</span>
            </button>
          </div>
          <div className={cx('options')}>
            <div className={cx('option', 'selected')}>
              S<img src={selectImg} alt="checked" />
            </div>
            <div className={cx('option')}>
              M<img src={selectImg} alt="checked" />
            </div>
            <div className={cx('option')}>
              L<img src={selectImg} alt="checked" />
            </div>
            <div className={cx('option')}>
              XL
              <img src={selectImg} alt="checked" />
            </div>
          </div>
        </div>
        <div className={cx('color')}>
          <div className={cx('header')}>
            <span>Color:</span>
          </div>
          <div className={cx('options')}>
            <div className={cx('option', 'selected')}>
              Black
              <img src={selectImg} alt="checked" />
            </div>
            <div className={cx('option')}>
              White
              <img src={selectImg} alt="checked" />
            </div>
          </div>
        </div>
      </div>
      <div className={cx('quantity-select')}>
        <label>Quantity:</label>
        <div className={cx('custom')}>
          <button className={cx('decrease')}>-</button>
          <span>1</span>
          <button className={cx('increase')}>+</button>
        </div>
      </div>
      <div className={cx('btn-buy')}>
        <button className={cx({ disabled: false })}>
          <span className={cx('txt-main')}>
            Buy now with price <span>320.000đ</span>
          </span>
          <span className={cx('txt-add')}>Order and deliver locally</span>
        </button>
      </div>
      <div className={cx('call')}>
        Order hotline:
        <a href="tel:1800 1234" title="1800 1234">
          <FaPhoneSquare /> 1800 1234
        </a>
        (09:00 - 22:00)
      </div>
      <div className={cx('store-place')}>
        <FaLocationArrow />
        <span>
          See a list of store
          <a href="https://google.com" title="YG Shop store system here" target={'_blank'}>
            here
          </a>
        </span>
      </div>
    </div>
  );
};

export default DetailBlock;
