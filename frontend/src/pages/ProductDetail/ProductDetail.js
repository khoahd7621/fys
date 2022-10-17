import { useParams } from 'react-router-dom';

import DetailBlock from './DetailBlock/DetailBlock';
import ImageBlock from './ImageBlock/ImageBlock';
import Tabs from './Tabs/Tabs';
import { BreadCrumb } from '~/components';
import { Nested } from '~/components/BreadCrumb/BreadCrumb';

import { publicRoutes } from '~/routes/routes';

const ProductDetail = () => {
  const { type, productname } = useParams();

  return (
    <div className={'product-detail'}>
      <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] px-3">
        <BreadCrumb current={productname}>
          <Nested path={`${publicRoutes.collection}/${type}`} name={type} />
        </BreadCrumb>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-2">
          <ImageBlock />
          <DetailBlock />
        </div>
        <div className="product-tab">
          <Tabs />
        </div>
        <div className="product-anchor block md:flex items-center p-3 border border-[#f5f5f5]">
          <div className="flex items-center">
            <div className="image relative w-[100px] h-[100px] mr-3">
              <img
                className="absolute top-0 bottom-0 left-0 right-0 max-h-full w-auto m-auto"
                src={
                  'https://bizweb.dktcdn.net/thumb/large/100/331/067/products/312080676-5515792315195571-1246325261985329414-n.jpg?v=1665978000173'
                }
                alt="Product anchor"
              />
            </div>
            <div className="content">
              <h3 className="font-bold uppercase mr-5">INVADER T-SHIRT</h3>
              <div className="text-sm font-light">
                Price: <span className="text-lg font-medium">320.000đ</span>
              </div>
            </div>
          </div>
          <button className="bg-black text-white block w-full md:w-[250px] mt-4 md:mt-0 ml-auto px-4 py-1 rounded-sm">
            <div className="uppercase font-medium">Buy now</div>
            <div>Delivery to your place</div>
          </button>
        </div>
        <div className="grid grid-cols-1">
          <div className="related-product"></div>
        </div>
      </div>
    </div>
  );
};

export default ProductDetail;
