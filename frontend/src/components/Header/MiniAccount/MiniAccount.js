import { Link } from 'react-router-dom';
import classNames from 'classnames/bind';

import styles from './MiniAccount.module.scss';

import { AiOutlineUser } from 'react-icons/ai';
import { privateRoutes } from '~/routes/routes';

const cx = classNames.bind(styles);

const MiniAccount = () => {
  return (
    <div className={cx('mini-account', 'ml-6 hidden lg:block')}>
      <AiOutlineUser className="text-3xl cursor-pointer" />
      <ul>
        <li>
          <Link to={privateRoutes.login}>Sign in</Link>
        </li>
        <li>
          <Link to={''}>Register</Link>
        </li>
      </ul>
    </div>
  );
};

export default MiniAccount;
