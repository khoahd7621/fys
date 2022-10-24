import { Link } from 'react-router-dom';
import { BiCheck } from 'react-icons/bi';

import logo from '~/assets/images/logo.webp';
import { publicRoutes } from '~/routes/routes';

const CheckoutSuccess = () => {
  return (
    <div className="checkout-thankyou bg-[#e6e8ea]">
      <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] min-h-screen px-3">
        <div className="logo pt-7 pb-5">
          <img src={logo} alt="YB Shop" title="Young Black Shop" />
        </div>
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
          <div className="primary">
            <section className="status">
              <div className="flex items-center py-4">
                <div className="w-[72px] h-[72px] flex items-center justify-center border-2 border-[#8ec343] rounded-full">
                  <BiCheck className="text-5xl text-[#8ec343]" />
                </div>
                <div className="ml-3 text-lg font-bold">Thank you for your order</div>
              </div>
            </section>
            <section className="content mb-4">
              <div className="border border-slate-300 py-3 px-4">
                <div className="grid grid-cols-2">
                  <section className="purchase-info">
                    <div className="title">
                      <h1 className="text-xl font-medium">Purchase information</h1>
                    </div>
                    <div className="my-2">Tran Van Tuan</div>
                    <div className="my-2">0123456789</div>
                  </section>
                  <section className="delivery-address">
                    <div className="title">
                      <h1 className="text-xl font-medium">Delivery address</h1>
                    </div>
                    <div className="my-2">222 Le Van Viet, Thanh pho Thu Duc, Thanh pho Ho Chi Minh</div>
                  </section>
                  <section className="payment-method">
                    <div className="title">
                      <h1 className="text-xl font-medium">Payment methods</h1>
                    </div>
                    <div className="my-2">Cash on Delivery (COD)</div>
                  </section>
                  <section className="shipping-method">
                    <div className="title">
                      <h1 className="text-xl font-medium">Shipping method</h1>
                    </div>
                    <div className="my-2">YB Fast same price</div>
                  </section>
                </div>
              </div>
            </section>
            <section className="update-orer-infor mb-4">
              <div className="border border-slate-300 py-3 px-4">
                <div className="title">
                  <h1 className="text-xl font-medium">Update order information</h1>
                </div>
                <div className="my-2">You can update your order information via Messenger right here</div>
              </div>
            </section>
            <section className="action">
              <div className="flex items-center justify-end">
                <Link to={publicRoutes.home} className="text-xl px-5 py-4 bg-black text-white rounded-md">
                  Continue shopping
                </Link>
              </div>
            </section>
          </div>
          <div className="secondary">
            <div className="bg-white border border-slate-300">
              <section className="header">
                <div className="px-3 py-2">Order WEB34418 (2)</div>
              </section>
              <section className="list-items">
                <div className="wrapper border-t border-slate-300 px-3 py-2">
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
              </section>
              <section className="amount-money">
                <div className="border-t border-slate-300 px-3 py-2">
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
              </section>
            </div>
          </div>
        </div>
        <div className="footer">
          <div className="border-t border-slate-300 mt-4 py-4 flex flex-col items-end">
            <div className="privacy flex gap-3">
              <button>Refund Policy</button>
              <button>Privacy Policy</button>
              <button>Terms of use</button>
            </div>
            <div className="copyright my-3 text-[#737373]">&#169; 2022 Young Black by YB Shop</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CheckoutSuccess;
