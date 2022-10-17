import ProductCard from '../ProductCard/ProductCard';

const RelatedProduct = () => {
  return (
    <div className="related-product mt-3 mb-3">
      <h2 className="text-2xl font-bold text-center mt-3 mb-5">Related Product</h2>
      <div className="grid grid-cols-5">
        <ProductCard />
        <ProductCard />
        <ProductCard />
        <ProductCard />
        <ProductCard />
      </div>
    </div>
  );
};

export default RelatedProduct;
