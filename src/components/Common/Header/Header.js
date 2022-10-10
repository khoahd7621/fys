import { useState } from 'react';

import './Header.scss';

import Search from '../Search/Search';

const Header = () => {
  const [isShowSearchBar, setIsShowSearchBar] = useState(false);

  return (
    <header className={`header-container ${isShowSearchBar ? 'active-search-bar' : ''}`}>
      <Search isShowSearchBar={isShowSearchBar} setIsShowSearchBar={setIsShowSearchBar} />
      <div>
        Header component
        <button onClick={() => setIsShowSearchBar(!isShowSearchBar)}>Show search</button>
      </div>
    </header>
  );
};

export default Header;
