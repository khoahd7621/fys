import { Link, useLocation } from 'react-router-dom';
import classNames from 'classnames/bind';

import styles from './Checkout.module.scss';

import { privateRoutes, publicRoutes } from '~/routes/routes';

import { BiUserCircle, BiLogOut } from 'react-icons/bi';
import { MdOutlineKeyboardArrowLeft } from 'react-icons/md';

import logo from '~/assets/images/logo.webp';
import imgPayment from '~/assets/images/payment/payment1.svg';

const cx = classNames.bind(styles);

const Checkout = () => {
  const location = useLocation();

  const paymentTypes = [
    {
      name: 'Cash on delivery (COD)',
      image: imgPayment,
      content: `<p>You only have to pay after receiving the goods.</p>
                <i className="block font-bold my-2">Viewing is allowed, not testing.</i>`,
      isSelected: false,
    },
    {
      name: 'Bank transfer',
      image: imgPayment,
      content: `<div>Chủ tài khoản: Hoang Dang Khoa</div>
                <ul>
                  <li>Vietcombank - Hồ Chí Minh: <b>1234 56789 1234</b></li>
                  <li>Agribank - Đồng Nai: <b>1234 5678 91234</b></li>
                </ul>`,
      isSelected: false,
    },
  ];

  return (
    <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] min-h-screen px-3">
      <div className="h-screen grid grid-cols-1 lg:grid-cols-3 gap-4">
        <div className="lg:col-span-2 flex flex-col justify-between">
          <div className="main">
            <div className="my-6">
              <Link to={publicRoutes.home}>
                <img src={logo} alt="" title="Young Black Shop" />
              </Link>
            </div>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
              <div className="purchase-information">
                <div className="header flex items-center justify-between mb-3">
                  <span className="text-lg font-bold">Purchase information</span>
                  <Link to={privateRoutes.login} state={{ from: location }} className="flex items-center gap-1">
                    <BiUserCircle />
                    Login
                  </Link>
                  {/* <button className="flex items-center gap-1">
                  <BiLogOut />
                  Logout
                </button> */}
                </div>
                <div className="relative mb-4">
                  <input
                    type="email"
                    id="floating_email"
                    className={cx(
                      { border: true },
                      { 'border-gray-300': true },
                      { 'border-2': false },
                      { 'border-[#ff0000]': false },
                      'block rounded-md px-2.5 pb-2.5 pt-5 w-full text-sm text-gray-900 bg-white appearance-none focus:ring-0 peer',
                    )}
                    placeholder={' '}
                  />
                  <label
                    htmlFor="floating_email"
                    className="absolute text-sm text-gray-500 duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] left-2.5 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-4"
                  >
                    Email
                  </label>
                  <p className="mt-2 text-xs text-[#ff0000]"></p>
                </div>
                <div className="relative mb-4">
                  <input
                    type="text"
                    id="floating_full-name"
                    className={cx(
                      { border: true },
                      { 'border-gray-300': true },
                      { 'border-2': false },
                      { 'border-[#ff0000]': false },
                      'block rounded-md px-2.5 pb-2.5 pt-5 w-full text-sm text-gray-900 bg-white appearance-none focus:ring-0 peer',
                    )}
                    placeholder={' '}
                  />
                  <label
                    htmlFor="floating_full-name"
                    className="absolute text-sm text-gray-500 duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] left-2.5 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-4"
                  >
                    Fullname
                  </label>
                  <p className="mt-2 text-xs text-[#ff0000]"></p>
                </div>
                <div className="relative mb-4">
                  <input
                    type="text"
                    id="floating_phone-number"
                    className={cx(
                      { border: true },
                      { 'border-gray-300': true },
                      { 'border-2': false },
                      { 'border-[#ff0000]': false },
                      'block rounded-md px-2.5 pb-2.5 pt-5 w-full text-sm text-gray-900 bg-white appearance-none focus:ring-0 peer',
                    )}
                    placeholder={' '}
                  />
                  <label
                    htmlFor="floating_phone-number"
                    className="absolute text-sm text-gray-500 duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] left-2.5 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-4"
                  >
                    Phone number
                  </label>
                  <p className="mt-2 text-xs text-[#ff0000]"></p>
                </div>
                <div className="relative mb-4">
                  <input
                    type="text"
                    id="floating_address"
                    className={cx(
                      { border: true },
                      { 'border-gray-300': true },
                      { 'border-2': false },
                      { 'border-[#ff0000]': false },
                      'block rounded-md px-2.5 pb-2.5 pt-5 w-full text-sm text-gray-900 bg-white appearance-none focus:ring-0 peer',
                    )}
                    placeholder={' '}
                  />
                  <label
                    htmlFor="floating_address"
                    className="absolute text-sm text-gray-500 duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] left-2.5 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-4"
                  >
                    Address
                  </label>
                  <p className="mt-2 text-xs text-[#ff0000]"></p>
                </div>
                <div className="relative mb-4">
                  <textarea
                    type="text"
                    id="floating_note"
                    className="block border border-gray-300 rounded-md px-2.5 pb-2.5 pt-5 w-full text-sm text-gray-900 bg-white appearance-none focus:ring-0 peer"
                    placeholder={' '}
                  ></textarea>
                  <label
                    htmlFor="floating_note"
                    className="absolute text-sm text-gray-500 duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] left-2.5 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-4"
                  >
                    Note (optional)
                  </label>
                </div>
              </div>
              <div className="transportation-payment">
                <div className="transportation">
                  <div className="header flex items-center justify-between mb-3">
                    <span className="text-lg font-bold">Transportation</span>
                  </div>
                  <div className="flex items-center justify-between border border-gray-300 rounded-md px-2.5 py-4 w-full text-sm text-gray-900 bg-white appearance-none focus:ring-0 peer cursor-pointer">
                    <div className="wrapper flex items-center gap-2">
                      <div className="relative w-4 h-4 bg-black border border-gray-300 rounded-full cursor-pointer">
                        <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-1 h-1 bg-white rounded-full"></div>
                      </div>
                      <div className="cursor-pointer">YB Fast same price</div>
                    </div>
                    <div className="price">30.000đ</div>
                  </div>
                </div>
                <div className={cx('payment', 'mt-5')}>
                  <div className="header flex items-center justify-between mb-3">
                    <span className="text-lg font-bold">Payment</span>
                  </div>
                  <div className={cx('list-options')}>
                    <div className={cx('option', { active: true })}>
                      <div className={cx('top')}>
                        <div className={cx('wrapper')}>
                          <div className={cx('point')}></div>
                          <div className={cx('title')}>Cash on delivery (COD)</div>
                        </div>
                        <div className={cx('image')}>
                          <img src={imgPayment} alt="Cash" />
                        </div>
                      </div>
                      <div className={cx('bottom')}>
                        <p>You only have to pay after receiving the goods.</p>
                        <i className="block font-bold my-2">Viewing is allowed, not testing.</i>
                      </div>
                    </div>
                    <div className={cx('option', { active: false })}>
                      <div className={cx('top')}>
                        <div className={cx('wrapper')}>
                          <div className={cx('point')}></div>
                          <div className={cx('title')}>Bank transfer</div>
                        </div>
                        <div className={cx('image')}>
                          <img src={imgPayment} alt="Cash" />
                        </div>
                      </div>
                      <div className={cx('bottom')}>
                        <div>Chủ tài khoản: Hoang Dang Khoa</div>
                        <ul>
                          <li>
                            Vietcombank - Hồ Chí Minh: <b>1234 56789 1234</b>
                          </li>
                          <li>
                            Agribank - Đồng Nai: <b>1234 5678 91234</b>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="footer border-t py-4 flex flex-col items-end">
            <div className="privacy flex gap-3">
              <button>Refund Policy</button>
              <button>Privacy Policy</button>
              <button>Terms of use</button>
            </div>
            <div className="copyright my-3 text-[#737373]">&#169; 2022 Young Black by YB Shop</div>
          </div>
        </div>
        <div className="lg:col-span-1 bg-[#fafafa] border-l">
          <div className="sidebar">
            <div className="header pt-5 pb-5 px-7 border-b">
              <h2 className="title text-lg font-bold">Order (15 products)</h2>
            </div>
            <div className="content pt-5 pb-16 px-7">
              <div className="list-items mb-4 min-h-[90px] max-h-[calc(100vh-480px)] overflow-y-auto">
                <div className="product">
                  <div className="flex items-center text-sm mt-2 mb-4">
                    <div className="image relative w-[50px] h-[50px] border rounded-lg">
                      <img
                        src="https://bizweb.dktcdn.net/thumb/large/100/331/067/products/311010762-5484494261658710-2078074986821866170-n.jpg?v=1665062885420"
                        alt="Product name"
                        className="border rounded-lg"
                      />
                      <span className="quantity absolute -top-2 -right-2 w-5 h-5 rounded-full bg-black text-white text-center text-xs leading-5">
                        1
                      </span>
                    </div>
                    <div className="description pl-3 flex-1 uppercase">
                      <div className="name">Born pink Blackpink T-shirt</div>
                      <div className="property text-[#717171]">S / Cream</div>
                    </div>
                    <div className="price w-fit pl-3 text-[#717171]">380.000đ</div>
                  </div>
                </div>
              </div>
              <div className="order-summary py-4 border-t">
                <div className="flex items-center justify-between">
                  <span className="text-[#717171]">Temporarily:</span>
                  <span className="text-[#717171]">5.280.000đ</span>
                </div>
                <div className="flex items-center justify-between mt-3">
                  <span className="text-[#717171]">Transporation cost:</span>
                  <span className="text-[#717171]">30.000đ</span>
                </div>
                <div className="amount flex items-center justify-between border-t pt-4 mt-4">
                  <span className="text-lg font-medium text-[#717171]">Total:</span>
                  <span className="text-xl font-semibold ">5.280.000đ</span>
                </div>
              </div>
              <div className="action flex items-center justify-between">
                <Link to={publicRoutes.cart} className="flex items-center hover:underline">
                  <MdOutlineKeyboardArrowLeft className="text-2xl" /> Back to cart
                </Link>
                <button className="py-3 px-6 rounded-md bg-black text-white uppercase">Order</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
