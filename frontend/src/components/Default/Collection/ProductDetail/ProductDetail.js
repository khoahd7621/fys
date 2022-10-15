import DetailBlock from './DetailBlock/DetailBlock';

const ProductDetail = () => {
  return (
    <div className={'product-detail'}>
      <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] px-3">
        <div className="grid grid-cols-1 md:grid-cols-2">
          <div className="image-block">Image here</div>
          <DetailBlock />
        </div>
        <div className="grid grid-cols-1">
          <div className="product-tab"></div>
        </div>
        <div className="grid grid-cols-1">
          <div className="related-product"></div>
        </div>
      </div>
    </div>
  );
};

export default ProductDetail;
