import { useState } from 'react';
import classNames from 'classnames/bind';
import styles from './Tabs.module.scss';

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
      <div className="content"></div>
    </>
  );
};

export default Tabs;
