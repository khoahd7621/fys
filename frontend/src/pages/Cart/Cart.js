import classNames from 'classnames/bind';

import styles from './Cart.module.scss';

import { BreadCrumb } from '~/components';

const cx = classNames.bind(styles);

const Cart = () => {
  return (
    <div className={cx('cart')}>
      <BreadCrumb current="cart" />
      <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] px-3 mb-5">
        <div className={cx('title', 'mt-7')}>
          <h1 className="text-[26px] uppercase">
            Cart <span className="text-[14px] lowercase">(12 products)</span>
          </h1>
        </div>
        <div className="grid grid-cols-12">
          <div className={cx('list-products', 'col-span-12 lg:col-span-9')}>
            <div className={cx('product', 'py-4')}>
              <div className="grid grid-cols-12">
                <div className="col-span-2 lg:col-span-3">
                  <div className={cx('image')}>
                    <img
                      className="lg:max-h-[180px]"
                      src={
                        'https://bizweb.dktcdn.net/thumb/medium/100/331/067/products/308066421-5438382269603243-2253586239615982651-n.jpg'
                      }
                      alt="product name"
                      title="product name"
                    />
                  </div>
                </div>
                <div className="col-span-10 lg:col-span-9">
                  <div className={cx('detail flex items-center justify-between lg:justify-start h-full')}>
                    <div className="infor w-[350px]">
                      <p className={cx('name', 'text-sm lg:text-base font-semibold uppercase')}>
                        Multiple personality t-shirt - s / white
                      </p>
                      <p className={cx('brands', 'text-sm font-medium text-[#a9a9a9] my-1')}>Brand: YG Shop</p>
                      <div className="mobile-price block lg:hidden text-sm font-normal">Price: 350.000đ</div>
                      <p className={cx('action')}>
                        <button className="hidden lg:block hover:underline">Delete</button>
                      </p>
                    </div>
                    <div className="price hidden lg:block w-[110px] text-lg font-semibold">350.000đ</div>
                    <div className={cx('quantity-block')}>
                      <div>
                        <button className={cx('decrease')}>-</button>
                        <span>13</span>
                        <button className={cx('increase')}>+</button>
                      </div>
                      <p className="mobile-action block lg:hidden hover:underline text-center mt-2 cursor-pointer">
                        Delete
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className={cx('cart-submit', 'col-span-12 lg:col-span-3')}>
            <div className={cx('fee')}>
              <div className={cx('temporarily')}>
                <span>Temporarily:</span>
                <span>11.312.000đ</span>
              </div>
              <div className={cx('amount')}>
                <span>Amount:</span>
                <span>11.312.000đ</span>
              </div>
              <button className={cx('checkout-btn')}>Pay now</button>
              <button className={cx('continue-shopping-btn')}>Continue shopping</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Cart;
