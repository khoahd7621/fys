import axios from '~/utils/httpRequest';

const getAllProductWithPaginate = (offset, limit) => {
  return axios.get(`/api/v1/product/all?offset=${offset}&limit=${limit}`);
};

const getAllProductByCategoryNameWithPaginate = (categoryName, offset, limit) => {
  return axios.get(`/api/v1/product?category-name=${categoryName}&offset=${offset}&limit=${limit}`);
};

export { getAllProductWithPaginate, getAllProductByCategoryNameWithPaginate };
