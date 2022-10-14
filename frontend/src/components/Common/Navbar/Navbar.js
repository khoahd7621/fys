import { Link } from 'react-router-dom';
import classNames from 'classnames/bind';

import styles from './Navbar.module.scss';

import { MdOutlineKeyboardArrowDown } from 'react-icons/md';

import { publicRoutePath } from '~/routes/constVars';

const cx = classNames.bind(styles);

const Navbar = () => {
  return (
    <nav className={cx('main-nav', 'container mx-auto hidden lg:block lg:max-w-[970px] xl:max-w-[1150px] px-3')}>
      <div className="grid grid-cols-3">
        <div className={cx('nav-item')}>
          <Link className={cx('nav-link')} to={publicRoutePath.home}>
            Home page
          </Link>
        </div>
        <div className={cx('nav-item')}>
          <Link className={cx('nav-link')} to={`${publicRoutePath.collection}/all`}>
            Products <MdOutlineKeyboardArrowDown className={cx('arrow-down')} />
          </Link>
          <ul className={cx('list-items')}>
            <li className={cx('item')}>
              <Link className={cx('link')} to={`${publicRoutePath.collection}/sale`}>
                Sale 🔥
              </Link>
            </li>
            <li className={cx('item')}>
              <Link className={cx('link')} to={`${publicRoutePath.collection}/t-shirt`}>
                T-shirt
              </Link>
            </li>
          </ul>
        </div>
        <div className={cx('nav-item')}>
          <Link className={cx('nav-link')} to={''}>
            Information <MdOutlineKeyboardArrowDown className={cx('arrow-down')} />
          </Link>
          <ul className={cx('list-items')}>
            <li className={cx('item')}>
              <Link className={cx('link')} to={''}>
                Contact
              </Link>
            </li>
            <li className={cx('item')}>
              <Link className={cx('link')} to={''}>
                Shop system
              </Link>
            </li>
            <li className={cx('item')}>
              <Link className={cx('link')} to={''}>
                Payment Guide
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
