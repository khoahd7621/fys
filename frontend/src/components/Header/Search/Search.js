import { useRef } from 'react';
import classNames from 'classnames/bind';

import styles from './Search.module.scss';

import AnimateInput from './AnimateInput/AnimateInput';

import { BiSearch } from 'react-icons/bi';
import { IoClose } from 'react-icons/io5';

const cx = classNames.bind(styles);

const Search = ({ isShowSearchBar, setIsShowSearchBar }) => {
  const inputRef = useRef(null);

  const handleSubmitSearch = (event) => {
    event.preventDefault();
    console.log('Input value: ', inputRef.current.value);
  };

  return (
    <>
      <div
        className={cx(
          'search-bar',
          { active: isShowSearchBar },
          'flex justify-center items-center absolute w-full h-[70px] z-[100] bg-white',
        )}
      >
        <form
          action="search"
          className="flex justify-center items-center"
          onSubmit={(event) => handleSubmitSearch(event)}
        >
          <AnimateInput ref={inputRef} />
          <button className="h-10 flex justify-center items-center">
            <BiSearch className="text-xl" />
          </button>
        </form>
        <button
          className="absolute top-1/2 right-4 translate-y-[-50%] flex justify-center items-center"
          onClick={() => setIsShowSearchBar(false)}
        >
          <IoClose className="text-3xl" />
        </button>
      </div>
      <div
        className={cx('backdrop', { active: isShowSearchBar }, 'fixed left-0 top-0 w-full h-full bg-black opacity-50')}
        onClick={() => setIsShowSearchBar(false)}
      ></div>
    </>
  );
};

export default Search;