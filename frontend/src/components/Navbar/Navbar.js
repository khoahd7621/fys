import { Link } from 'react-router-dom';
import classNames from 'classnames/bind';

import styles from './Navbar.module.scss';

import { MdOutlineKeyboardArrowDown } from 'react-icons/md';

import { publicRoutes } from '~/routes/routes';

const cx = classNames.bind(styles);

const Navbar = () => {
  const navHome = {
    title: 'Home page',
    path: `${publicRoutes.home}`,
  };

  const navCollections = {
    title: 'Products',
    path: `${publicRoutes.collection}/all`,
    items: [
      {
        title: 'Sale 🔥',
        path: `${publicRoutes.collection}/sale`,
      },
      {
        title: 'T-shirt',
        path: `${publicRoutes.collection}/t-shirt`,
      },
    ],
  };

  const navInformations = {
    title: 'Information',
    path: '',
    items: [
      {
        title: 'Contact',
        path: '',
      },
      {
        title: 'Shop system',
        path: '',
      },
      {
        title: 'Payment Guide',
        path: '',
      },
    ],
  };

  return (
    <nav className={cx('main-nav', 'container mx-auto hidden lg:block lg:max-w-[970px] xl:max-w-[1150px] px-3')}>
      <div className="grid grid-cols-3">
        <div className={cx('nav-item')}>
          <Link className={cx('nav-link')} to={navHome.path}>
            {navHome.title}
          </Link>
        </div>
        <div className={cx('nav-item')}>
          <Link className={cx('nav-link')} to={navCollections.path}>
            {navCollections.title} <MdOutlineKeyboardArrowDown className={cx('arrow-down')} />
          </Link>
          <ul className={cx('list-items')}>
            {navCollections.items.map((item, index) => (
              <li key={`collections-${item.title}-${index}`} className={cx('item')}>
                <Link className={cx('link')} to={item.path}>
                  {item.title}
                </Link>
              </li>
            ))}
          </ul>
        </div>
        <div className={cx('nav-item')}>
          <Link className={cx('nav-link')} to={navInformations.path}>
            {navInformations.title} <MdOutlineKeyboardArrowDown className={cx('arrow-down')} />
          </Link>
          <ul className={cx('list-items')}>
            {navInformations.items.map((item, index) => (
              <li key={`information-${item.title}-${index}`} className={cx('item')}>
                <Link className={cx('link')} to={item.path}>
                  {item.title}
                </Link>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
