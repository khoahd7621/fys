import { Link } from 'react-router-dom';

import './AccountButton.scss';

import { AiOutlineUser } from 'react-icons/ai';

const AccountButton = () => {
  return (
    <div className="account-button ml-6">
      <AiOutlineUser className="text-3xl cursor-pointer" />
      <ul>
        <li>
          <Link to={''}>Sign in</Link>
        </li>
        <li>
          <Link to={''}>Register</Link>
        </li>
      </ul>
    </div>
  );
};

export default AccountButton;
