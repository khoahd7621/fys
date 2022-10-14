import { Link } from 'react-router-dom';
import classNames from 'classnames/bind';
import styles from './BreadCrumb.module.scss';

import { RiArrowRightSLine } from 'react-icons/ri';

import { publicRoutePath } from '~/routes/constVars';

const cx = classNames.bind(styles);

const BreadCrumb = ({ current = '', prevLink = [] }) => {
  return (
    <div className={cx('bread-crumb')}>
      <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] px-3">
        <ul className={cx('list-items')}>
          <li className={cx('item', 'link')}>
            <Link to={publicRoutePath.home}>Home page</Link>
          </li>
          <li className={cx('item', 'active')}>
            <span>
              <RiArrowRightSLine />
            </span>
            <strong>{current}</strong>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default BreadCrumb;
