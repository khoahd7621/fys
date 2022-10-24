import classNames from 'classnames/bind';
import styles from './Item.module.scss';

import { VscTrash } from 'react-icons/vsc';

const cx = classNames.bind(styles);

const Item = () => {
  return (
    <div className={cx('item')}>
      <a className={cx('link')} href="#">
        <img
          className={cx('image')}
          src="https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"
          alt=""
        />
      </a>
      <div className={cx('detail')}>
        <a className={cx('title')} href="">
          Multiple Person Hahaha asdfsadfsadf
        </a>
        <p className={cx('price')}>350.000đ</p>
        <div className={cx('quantity-select')}>
          <button className={cx('decrese')}>-</button>
          <span>1</span>
          <button className={cx('increase')}>+</button>
        </div>
      </div>
      <VscTrash className={cx('delete', 'text-2xl cursor-pointer')} />
    </div>
  );
};

export default Item;
