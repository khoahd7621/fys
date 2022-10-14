import { useState } from 'react';
import { useParams } from 'react-router-dom';
import classNames from 'classnames/bind';

import styles from './Collection.module.scss';

import BreadCrumb from '~/components/Common/BreadCrumb/BreadCrumb';
import Product from './Product/Product';

const cx = classNames.bind(styles);

const Collection = () => {
  const sortTypes = ['Name A-Z', 'Name Z-A', 'New product', 'Low to high price', 'High to low price'];

  const { type } = useParams();
  const [currentSortType, setCurrentSortType] = useState(-1);

  const handleSortProduct = (index, type) => {
    setCurrentSortType(index);
  };

  return (
    <>
      <BreadCrumb current={type} />
      <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] px-3">
        <div className={cx('collection')}>
          <div className={cx('main-cate')}>
            <h1 className={cx('category')}>{type}</h1>
          </div>
          <div className={cx('products')}>
            <div className={cx('sort-cate')}>
              <h3>Sort by:</h3>
              <ul>
                {sortTypes.map((type, index) => (
                  <li key={`sort-type-${type}-${index}`} onClick={() => handleSortProduct(index, type)}>
                    <i className={cx({ active: +currentSortType === index })}></i>
                    <span>{type}</span>
                  </li>
                ))}
              </ul>
            </div>
            <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-2">
              <Product />
              <Product />
              <Product />
              <Product />
              <Product />
              <Product />
              <Product />
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Collection;
