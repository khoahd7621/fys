import axios from '~/utils/httpRequest';

const postCreateNewProduct = ({
  name,
  price,
  description,
  isVisible,
  primaryImageName,
  primaryImageUrl,
  secondaryImageName,
  secondaryImageUrl,
  category,
  sizes,
  colors,
}) => {
  return axios.post('/api/v1/admin/product', {
    name,
    price,
    description,
    isVisible,
    primaryImageName,
    primaryImageUrl,
    secondaryImageName,
    secondaryImageUrl,
    category,
    sizes,
    colors,
  });
};

export { postCreateNewProduct };
