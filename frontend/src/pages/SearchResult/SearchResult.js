import { useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';

import { BiSearch } from 'react-icons/bi';
import { toast } from 'react-toastify';

import { BreadCrumb, ProductCard } from '~/components';
import { publicRoutes } from '~/routes/routes';

const Search = () => {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();

  const [keyWord, setKeyWord] = useState('');

  const handleSubmitSearch = (event) => {
    event.preventDefault();
    if (keyWord.trim().length === 0) {
      toast.error('Please enter a keyword');
    } else {
      navigate(`${publicRoutes.search}?query=${keyWord}`);
    }
  };

  console.log('>>> Query params: ', searchParams.get('query'));
  return (
    <div className="search-result">
      <BreadCrumb current="Search result" />
      <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] px-3 mt-8 mb-5">
        <h3 className="title text-2xl uppercase text-center mb-5">
          {/* There is no result with this keyword. */}
          Have 47 right search results.
        </h3>
        {/* No result */}
        <div className="grid grid-cols-12">
          <form className="form-search col-start-4 col-end-10" onSubmit={(event) => handleSubmitSearch(event)}>
            <label className="block text-[#333f48] mb-[10px] text-center" htmlFor="search-result-input">
              Please enter another keyword
            </label>
            <div className="flex items-center justify-center">
              <input
                id="search-result-input"
                className="flex-1 h-10 px-5 focus:outline-none border"
                type="text"
                placeholder="What do you want to search today?"
                value={keyWord}
                onChange={(event) => {
                  setKeyWord(event.target.value);
                }}
              />
              <button className="h-10 px-5 bg-black text-white">
                <BiSearch />
              </button>
            </div>
          </form>
        </div>
        {/* Have result */}
        <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-2">
          <ProductCard />
        </div>
      </div>
    </div>
  );
};

export default Search;
