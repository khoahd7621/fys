import { useSelector } from 'react-redux';
import { useEffect, useState } from 'react';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import classNames from 'classnames/bind';
import _ from 'lodash';
import { v4 as uuidv4 } from 'uuid';

import styles from './Checkout.module.scss';

import { toast } from 'react-toastify';
import { MdOutlineKeyboardArrowLeft } from 'react-icons/md';
// import { BiUserCircle, BiLogOut } from 'react-icons/bi';

import { publicRoutes } from '~/routes/routes';

import logo from '~/assets/images/logo.webp';
import imgPayment from '~/assets/images/payment/payment1.svg';

import { formatVietnamMoney } from '~/utils/format';
import Validation from '~/utils/validation';
import { postCreateNewOrder } from '~/services/client/orderService';

const cx = classNames.bind(styles);

const Checkout = () => {
  const navigate = useNavigate();
  const account = useSelector((state) => state.user.account);
  const cart = useSelector((state) => state.cart.cart);

  const [purchaseInfo, setPurchaseInfo] = useState({
    email: account.email ?? '',
    fullName: account.firstName ? `${account.firstName} ${account.lastName}` : '',
    phone: account.phone ?? '',
    address: account.address ?? '',
    note: '',
  });
  const [transportMethod, setTransportMethod] = useState({});
  const [listTransportMethod, setListTransportMethod] = useState([
    {
      id: 1,
      name: 'YB Fast same price',
      value: 'YB_FAST_SAME_PRICE',
      price: 30000,
    },
  ]);
  const [paymentMethod, setPaymentMethod] = useState({});
  const [listPaymentMethod, setListPaymentMethod] = useState([
    {
      id: 1,
      name: 'Cash on delivery (COD)',
      image: imgPayment,
      value: 'CASH',
      content: `<p>You only have to pay after receiving the goods.</p>
                <i className="block font-bold my-2">Viewing is allowed, not testing.</i>`,
    },
    {
      id: 2,
      name: 'Bank transfer',
      image: imgPayment,
      value: 'BANKING',
      content: `<div>Chủ tài khoản: Hoang Dang Khoa</div>
                <ul>
                  <li>Vietcombank - Hồ Chí Minh: <b>1234 56789 1234</b></li>
                  <li>Agribank - Đồng Nai: <b>1234 5678 91234</b></li>
                </ul>`,
    },
  ]);

  useEffect(() => {
    document.title = 'Checkout';
  }, []);

  if (cart?.items.length === 0) {
    toast.error("You don't have any item in cart.");
    return <Navigate to={publicRoutes.cart} />;
  }

  const totalMoney =
    cart?.items &&
    cart?.items.length > 0 &&
    cart.items.reduce((previous, current) => {
      if (current.product.product.promotion) {
        return previous + current.quantity * current.product.product.discountPrice;
      }
      return previous + current.quantity * current.product.product.price;
    }, 0);
  const totalItems = cart?.items?.reduce((previous, current) => previous + current.quantity, 0);

  const handleChangeInput = (event) => {
    setPurchaseInfo({
      ...purchaseInfo,
      [event.target.name]: event.target.value,
    });
  };

  const handleChooseTransportMethod = (method) => {
    setTransportMethod({
      ...method,
    });
  };

  const handleChangePaymentMethod = (method) => {
    setPaymentMethod({
      ...method,
    });
  };

  const validateDataSubmit = () => {
    if (!purchaseInfo.email) {
      toast.error('Please enter your email address.');
      return false;
    }
    if (!purchaseInfo.fullName) {
      toast.error('Please enter your full name.');
      return false;
    }
    if (!purchaseInfo.phone) {
      toast.error('Please enter your phone number.');
      return false;
    }
    if (!Validation.isValidPhone(purchaseInfo.phone)) {
      toast.error('Invalid phone number.');
      return false;
    }
    if (!purchaseInfo.address) {
      toast.error('Please enter your address.');
      return false;
    }
    if (_.isEmpty(transportMethod)) {
      toast.error('Please chooose transport method.');
      return false;
    }
    if (_.isEmpty(paymentMethod)) {
      toast.error('Please chooose payment method.');
      return false;
    }
    return true;
  };

  const handleCheckoutOrder = async () => {
    if (validateDataSubmit()) {
      const payload = {
        code: uuidv4(),
        fullName: purchaseInfo.fullName,
        phone: purchaseInfo.phone,
        address: purchaseInfo.address,
        note: purchaseInfo.note ? purchaseInfo.note : 'none',
        totalPrice: totalMoney,
        deliveryFee: transportMethod.price,
        deliveryMethod: transportMethod.value,
        paymentMethod: paymentMethod.value,
        products:
          cart?.items &&
          cart?.items.length > 0 &&
          cart.items.map((item) => {
            return {
              variantSizeId: item.product.variantSizeId,
              quantity: item.quantity,
              price: item.product.product.promotion ? item.product.product.discountPrice : item.product.product.price,
            };
          }),
      };
      const response = await postCreateNewOrder(payload);
      if (response && +response.code === 0) {
        navigate(`${publicRoutes.checkoutSuccess}/${response.data.code}`);
        toast.success('Order successfully.');
      } else {
        toast.error(response.message);
      }
    }
  };

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
                  {/* <Link to={privateRoutes.login} state={{ from: location }} className="flex items-center gap-1">
                    <BiUserCircle />
                    Login
                  </Link>
                  <button className="flex items-center gap-1">
                    <BiLogOut />
                    Logout
                  </button> */}
                </div>
                <div className="relative mb-4">
                  <input
                    disabled
                    type="email"
                    id="floating_email"
                    className={cx(
                      'border border-gray-300 block rounded-md px-2.5 pb-2.5 pt-5 w-full text-sm text-gray-900 bg-white appearance-none focus:ring-0 peer disabled:bg-slate-200',
                    )}
                    placeholder={' '}
                    value={purchaseInfo.email}
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
                      { border: purchaseInfo.fullName },
                      { 'border-gray-300': purchaseInfo.fullName },
                      { 'border-2': !purchaseInfo.fullName },
                      { 'border-[#ff0000]': !purchaseInfo.fullName },
                      'block rounded-md px-2.5 pb-2.5 pt-5 w-full text-sm text-gray-900 bg-white appearance-none focus:ring-0 peer',
                    )}
                    placeholder={' '}
                    name="fullName"
                    value={purchaseInfo.fullName}
                    onChange={(event) => handleChangeInput(event)}
                  />
                  <label
                    htmlFor="floating_full-name"
                    className="absolute text-sm text-gray-500 duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] left-2.5 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-4"
                  >
                    Full name
                  </label>
                  <p className="mt-2 text-xs text-[#ff0000]"></p>
                </div>
                <div className="relative mb-4">
                  <input
                    type="text"
                    id="floating_phone-number"
                    className={cx(
                      { border: purchaseInfo.phone },
                      { 'border-gray-300': purchaseInfo.phone },
                      { 'border-2': !purchaseInfo.phone },
                      { 'border-[#ff0000]': !purchaseInfo.phone },
                      'block rounded-md px-2.5 pb-2.5 pt-5 w-full text-sm text-gray-900 bg-white appearance-none focus:ring-0 peer',
                    )}
                    placeholder={' '}
                    name="phone"
                    value={purchaseInfo.phone}
                    onChange={(event) => handleChangeInput(event)}
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
                      { border: purchaseInfo.address },
                      { 'border-gray-300': purchaseInfo.address },
                      { 'border-2': !purchaseInfo.address },
                      { 'border-[#ff0000]': !purchaseInfo.address },
                      'block rounded-md px-2.5 pb-2.5 pt-5 w-full text-sm text-gray-900 bg-white appearance-none focus:ring-0 peer',
                    )}
                    placeholder={' '}
                    name="address"
                    value={purchaseInfo.address}
                    onChange={(event) => handleChangeInput(event)}
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
                  {listTransportMethod &&
                    listTransportMethod.length > 0 &&
                    listTransportMethod.map((method, index) => (
                      <div
                        key={`transport-method-${method.id}-${index}`}
                        className="flex items-center justify-between border border-gray-300 rounded-md px-2.5 py-4 w-full text-sm text-gray-900 bg-white appearance-none focus:ring-0 peer cursor-pointer"
                        onClick={() => handleChooseTransportMethod(method)}
                      >
                        <div className="wrapper flex items-center gap-2">
                          <div
                            className={cx(
                              { 'bg-black': transportMethod.id === method.id },
                              'relative w-4 h-4 border border-gray-300 rounded-full cursor-pointer',
                            )}
                          >
                            {transportMethod.id === method.id && (
                              <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-1 h-1 bg-white rounded-full"></div>
                            )}
                          </div>
                          <div className="cursor-pointer">{method.name}</div>
                        </div>
                        <div className="price">{formatVietnamMoney.format(method.price)}</div>
                      </div>
                    ))}
                </div>
                <div className={cx('payment', 'mt-5')}>
                  <div className="header flex items-center justify-between mb-3">
                    <span className="text-lg font-bold">Payment</span>
                  </div>
                  <div className={cx('list-options')}>
                    {listPaymentMethod &&
                      listPaymentMethod.length > 0 &&
                      listPaymentMethod.map((method, index) => (
                        <div
                          key={`payment-method-${method.id}-${index}`}
                          className={cx('option', { active: paymentMethod.id === method.id })}
                          onClick={() => handleChangePaymentMethod(method)}
                        >
                          <div className={cx('top')}>
                            <div className={cx('wrapper')}>
                              <div className={cx('point')}></div>
                              <div className={cx('title')}>{method.name}</div>
                            </div>
                            <div className={cx('image')}>
                              <img src={method.image} alt="Cash" />
                            </div>
                          </div>
                          <div className={cx('bottom')} dangerouslySetInnerHTML={{ __html: method.content }} />
                        </div>
                      ))}
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
              <h2 className="title text-lg font-bold">Order ({totalItems} products)</h2>
            </div>
            <div className="content pt-5 pb-16 px-7">
              <div className="list-items mb-4 min-h-[90px] max-h-[calc(100vh-480px)] overflow-y-auto">
                {cart?.items &&
                  cart?.items.length > 0 &&
                  cart?.items.map((item, index) => (
                    <div key={`checkout-product-${index}`} className="product">
                      <div className="flex items-center text-sm mt-2 mb-4">
                        <div className="image relative w-[50px] h-[50px] border rounded-lg">
                          <img
                            src={item?.product?.product.primaryImageUrl}
                            alt={item?.product?.product.name}
                            title={item?.product?.product.name}
                            className="border rounded-lg"
                          />
                          <span className="quantity absolute -top-2 -right-2 w-5 h-5 rounded-full bg-black text-white text-center text-xs leading-5">
                            {item.quantity}
                          </span>
                        </div>
                        <div className="description pl-3 flex-1 uppercase">
                          <div className="name">{item?.product?.product?.name}</div>
                          <div className="property text-[#717171]">{`${item?.product?.size?.size} / ${item?.product?.color?.name}`}</div>
                        </div>
                        <div className="price w-fit pl-3 text-[#717171]">
                          {item?.product?.product?.promotion
                            ? formatVietnamMoney.format(item?.product?.product?.discountPrice)
                            : formatVietnamMoney.format(item?.product?.product?.price)}
                        </div>
                      </div>
                    </div>
                  ))}
              </div>
              <div className="order-summary py-4 border-t">
                <div className="flex items-center justify-between">
                  <span className="text-[#717171]">Temporarily:</span>
                  <span className="text-[#717171]">{formatVietnamMoney.format(totalMoney)}</span>
                </div>
                <div className="flex items-center justify-between mt-3">
                  <span className="text-[#717171]">Transporation cost:</span>
                  <span className="text-[#717171]">
                    {transportMethod.price ? formatVietnamMoney.format(transportMethod.price) : '-'}
                  </span>
                </div>
                <div className="amount flex items-center justify-between border-t pt-4 mt-4">
                  <span className="text-lg font-medium text-[#717171]">Total:</span>
                  <span className="text-xl font-semibold ">
                    {formatVietnamMoney.format(
                      transportMethod.price ? transportMethod.price + totalMoney : 0 + totalMoney,
                    )}
                  </span>
                </div>
              </div>
              <div className="action flex items-center justify-between">
                <Link to={publicRoutes.cart} className="flex items-center hover:underline">
                  <MdOutlineKeyboardArrowLeft className="text-2xl" /> Back to cart
                </Link>
                <button
                  className="py-3 px-6 rounded-md bg-black text-white uppercase"
                  onClick={() => handleCheckoutOrder()}
                >
                  Order
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
