import { useState } from 'react';
import classNames from 'classnames/bind';
import styles from './Tabs.module.scss';

import sizeTableImg from '~/assets/images/product/sizetable.webp';

const cx = classNames.bind(styles);

const Tabs = () => {
  const [index, setIndex] = useState(1);

  console.log('render');
  return (
    <>
      <div className={cx('tabs')}>
        <div
          className={cx({ active: +index === 1 })}
          onClick={() => {
            index !== 1 && setIndex(1);
          }}
        >
          Description
        </div>
        <div
          className={cx({ active: +index === 2 })}
          onClick={() => {
            index !== 2 && setIndex(2);
          }}
        >
          Size table
        </div>
        <div
          className={cx({ active: +index === 3 })}
          onClick={() => {
            index !== 3 && setIndex(3);
          }}
        >
          Review Product
        </div>
      </div>
      <div className="content mb-6">
        {+index === 1 && <div>Product description ...</div>}
        {+index === 2 && (
          <div>
            <div className="font-medium uppercase">Note: Size table does not apply to all products</div>
            <img className="max-w-[600px] mx-auto" src={sizeTableImg} alt={'Size chart'} title="size chart" />
          </div>
        )}
        {+index === 3 && <div>Ratings product ...</div>}
      </div>
    </>
  );
};

export default Tabs;
