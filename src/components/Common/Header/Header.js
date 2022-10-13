import { useState } from 'react';
import { Link } from 'react-router-dom';

import './Header.scss';

import Search from '../Search/Search';
import AccountButton from './AccountButton/AccountButton';
import MiniCart from './MiniCart/MiniCart';

import { BsSearch } from 'react-icons/bs';

import logo from '~/assets/images/logo.webp';

const Header = () => {
  const [isShowSearchBar, setIsShowSearchBar] = useState(false);

  return (
    <header className={`header-container bg-[#f7f8fa] ${isShowSearchBar ? 'active-search-bar' : ''}`}>
      <Search isShowSearchBar={isShowSearchBar} setIsShowSearchBar={setIsShowSearchBar} />
      <div className="container mx-auto max-w-[1150px]">
        <div className="wrapper h-[76px] grid grid-cols-3 grid-flow-col">
          <div className="flex items-center font-normal">
            Free shipping for orders over <b className="ml-1 font-semibold">500.000đ</b>
          </div>
          <div className="flex items-center justify-center">
            <Link to={'/'}>
              <img className="h-[20px] md:h-[24px] lg:h-[30px]" src={logo} alt="logo" />
            </Link>
          </div>
          <div className="header__action flex items-center justify-end">
            <a className="flex flex-col items-center justify-center" href="tel:1800 1234">
              <b className="text-xl">1800 1234</b>
              <span className="leading-4">Purchase support</span>
            </a>
            <div className="text-2xl ml-6">
              <button onClick={() => setIsShowSearchBar(!isShowSearchBar)}>
                <BsSearch />
              </button>
            </div>
            <AccountButton />
            <MiniCart />
          </div>
        </div>
      </div>
    </header>
  );
};

export default Header;
