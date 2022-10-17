import { Link } from 'react-router-dom';
import classNames from 'classnames/bind';

import styles from './Footer.module.scss';

import { FaFacebookF, FaTwitter, FaInstagram, FaYoutube } from 'react-icons/fa';
import payment1 from '~/assets/images/payment/payment1.svg';

import BackToTop from '../BackToTop/BackToTop';

const cx = classNames.bind(styles);

const Footer = () => {
  return (
    <footer className={cx('footer')}>
      <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] px-3">
        <div className="grid grid-cols-1 lg:grid-cols-2 lg:gap-2 pb-5">
          <div className="grid grid-cols-2 md:grid-cols-3 gap-2">
            <div className={cx('widget')}>
              <h3>Customer</h3>
              <ul>
                <li>
                  <Link to={''}>Cart</Link>
                </li>
                <li>
                  <Link to={''}>Register</Link>
                </li>
                <li>
                  <Link to={''}>Sign in</Link>
                </li>
                <li>
                  <Link to={''}>Check your order</Link>
                </li>
              </ul>
            </div>
            <div className={cx('widget')}>
              <h3>Information</h3>
              <ul>
                <li>
                  <Link to={''}>Privacy Policy</Link>
                </li>
                <li>
                  <Link to={''}>Shipping Policy</Link>
                </li>
                <li>
                  <Link to={''}>Return Policy</Link>
                </li>
                <li>
                  <Link to={''}>Usage rules</Link>
                </li>
              </ul>
            </div>
            <div className={cx('widget')}>
              <h3>E-commerce floor</h3>
              <ul>
                <li>
                  <a href="https://shopee.vn/" target="_blank" title="Shopee">
                    Shopee Ho Chi Minh
                  </a>
                </li>
                <li>
                  <a href="https://shopee.vn/" target="_blank" title="Shopee">
                    Shopee Hanoi
                  </a>
                </li>
                <li>
                  <a href="https://shopee.vn/" target="_blank" title="Shopee">
                    Shopee Da Nang
                  </a>
                </li>
                <li>
                  <a href="https://www.lazada.vn/" target="_blank" title="Lazada">
                    Lazada - LazMall
                  </a>
                </li>
              </ul>
            </div>
            <div className={cx('widget', 'col-span-2 md:col-span-3')}>
              <div className={cx('payment', 'flex')}>
                <h3>Payment Method</h3>
                <img src={payment1} alt="cash" className="ml-3" />
              </div>
            </div>
          </div>
          <div>
            <div className={cx('widget')}>
              <h3>Connect with Young Black</h3>
              <ul>
                <li className="pb-32">
                  Address: <br />
                  718 Điện Biên Phủ, Tân Bình, HCM
                  <br />
                  95A Hoàng Hữu Nam, Phú Nhuận, HCM
                  <br />
                  180 Hồ Gươm, Đống Đa, Hà Nội
                  <br />
                  488 Nguyễn Tri Phương, Đà Nẵng
                </li>
                <li>
                  Phone number: <a href="tel:1800 1234">1800 1234</a>
                </li>
              </ul>
              <div className={cx('social')}>
                <a className={cx('facebook')} href="https://www.facebook.com/" target="_blank">
                  <FaFacebookF />
                </a>
                <a className={cx('twitter')} href="https://www.twitter.com/" target="_blank">
                  <FaTwitter />
                </a>
                <a className={cx('instagram')} href="https://www.instagram.com/" target="_blank">
                  <FaInstagram />
                </a>
                <a className={cx('youtube')} href="https://www.youtube.com/" target="_blank">
                  <FaYoutube />
                </a>
              </div>
              <div className={cx('mailchimp', 'mb-5')}>
                <h3>Sign up to get news from Young Black</h3>
                <form>
                  <input type="text" placeholder="Your email" />
                  <button>Register</button>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div className="copyright text-white text-center py-3">
          &#169; Copyright by Young Black | Designed by YG SHOP with ❤️
        </div>
      </div>
      <BackToTop />
    </footer>
  );
};

export default Footer;
