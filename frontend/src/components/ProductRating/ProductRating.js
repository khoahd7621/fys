import classNames from 'classnames/bind';
import { Rating } from 'react-simple-star-rating';

import './FixedStar.scss';
import styles from './ProductRating.module.scss';

import { AiFillStar } from 'react-icons/ai';
import { HiOutlineUserCircle } from 'react-icons/hi';

const cx = classNames.bind(styles);

const ProductRating = () => {
  return (
    <div className={cx('product-rating')}>
      <div className={cx('rating')}>
        <p className={cx('title', 'font-semibold text-2xl')}>Ratings ProductName...</p>
        <div className={cx('grid grid-cols-2 mt-6 mb-5')}>
          <div className={cx('star', 'left max-w-[260px] p-3 border-r')}>
            <div className={cx('top', 'flex gap-2 items-end mb-4')}>
              <p className={cx('point', 'font-bold text-2xl leading-6 text-[#fe8c23]')}>4.9</p>
              <Rating
                className={cx('list-stars')}
                fillColorArray={['#f14f45', '#f17a45', '#f19745', '#f1d045', '#f1de45']}
                initialValue={3}
                readonly
                size={20}
              />
              <p className={cx('total', 'text-base')}>7 ratings</p>
            </div>
            <ul className={cx('rating-list')}>
              <li className="flex items-center gap-2 mb-2">
                <div className={cx('number flex items-center gap-1 text-sm')}>
                  5 <AiFillStar />
                </div>
                <div className={cx('timeline flex-1')}>
                  <div className="w-full bg-gray-200 rounded-full h-1.5 dark:bg-gray-300">
                    <div className="bg-[#f1de45] h-1.5 rounded-full" style={{ width: '86%' }}></div>
                  </div>
                </div>
                <div className={cx('percent', 'text-sm w-9')}>86%</div>
              </li>
              <li className="flex items-center gap-2 mb-2">
                <div className={cx('number flex items-center gap-1 text-sm')}>
                  4 <AiFillStar />
                </div>
                <div className={cx('timeline flex-1')}>
                  <div className="w-full bg-gray-200 rounded-full h-1.5 dark:bg-gray-300">
                    <div className="bg-[#f17a45] h-1.5 rounded-full" style={{ width: '10%' }}></div>
                  </div>
                </div>
                <div className={cx('percent', 'text-sm w-9')}>10%</div>
              </li>
              <li className="flex items-center gap-2 mb-2">
                <div className={cx('number flex items-center gap-1 text-sm')}>
                  3 <AiFillStar />
                </div>
                <div className={cx('timeline flex-1')}>
                  <div className="w-full bg-gray-200 rounded-full h-1.5 dark:bg-gray-300">
                    <div className="bg-[#f19745] h-1.5 rounded-full" style={{ width: '1%' }}></div>
                  </div>
                </div>
                <div className={cx('percent', 'text-sm w-9')}>1%</div>
              </li>
              <li className="flex items-center gap-2 mb-2">
                <div className={cx('number flex items-center gap-1 text-sm')}>
                  2 <AiFillStar />
                </div>
                <div className={cx('timeline flex-1')}>
                  <div className="w-full bg-gray-200 rounded-full h-1.5 dark:bg-gray-300">
                    <div className="bg-[#f17a45] h-1.5 rounded-full" style={{ width: '1%' }}></div>
                  </div>
                </div>
                <div className={cx('percent', 'text-sm w-9')}>1%</div>
              </li>
              <li className="flex items-center gap-2 mb-2">
                <div className={cx('number flex items-center gap-1 text-sm')}>
                  1 <AiFillStar />
                </div>
                <div className={cx('timeline flex-1')}>
                  <div className="w-full bg-gray-200 rounded-full h-1.5 dark:bg-gray-300">
                    <div className="bg-[#f14f45] h-1.5 rounded-full" style={{ width: '1%' }}></div>
                  </div>
                </div>
                <div className={cx('percent', 'text-sm w-9')}>1%</div>
              </li>
            </ul>
          </div>
          <div className={cx('right', 'flex justify-end items-start')}>
            <button className="text-base uppercase p-2 bg-black text-white hover:bg-slate-700">Write ratings</button>
          </div>
        </div>
      </div>
      <div className={cx('comments')}>
        <div className={cx('comment', 'border-t pt-3')}>
          <div className={cx('top', 'flex items-center gap-2 mb-2')}>
            <div className="text-4xl">
              <HiOutlineUserCircle />
            </div>
            <div>
              {' '}
              <div className={cx('title')}>Khoa</div>
              <div className={cx('rate', 'flex gap-2 items-end')}>
                <div className={cx('star')}>
                  <Rating
                    fillColorArray={['#f14f45', '#f17a45', '#f19745', '#f1d045', '#f1de45']}
                    initialValue={2}
                    readonly
                    size={20}
                  />
                </div>
                <div className={cx('date')}>22/22/2022</div>
              </div>
            </div>
          </div>
          <div className={cx('bottom')}>
            <div className={cx('title', 'font-medium')}>Good</div>
            <p className={cx('comment')}>
              Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
              industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and
              scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into
              electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release
              of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software
              like Aldus PageMaker including versions of Lorem Ipsum.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductRating;
