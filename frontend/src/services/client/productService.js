import axios from '~/utils/httpRequest';

const getAllProductWithPaginate = (offset, limit) => {
  return axios.get(`/api/v1/product/all?offset=${offset}&limit=${limit}`);
};

const getAllProductByCategoryNameWithPaginate = (categoryName, offset, limit) => {
  return axios.get(`/api/v1/product?category-name=${categoryName}&offset=${offset}&limit=${limit}`);
};

const getNRelatedProductByCategoryId = (categoryId, numberElements) => {
  return axios.get(`/api/v1/product/related?categoryId=${categoryId}&numberElements=${numberElements}`);
};

export { getAllProductWithPaginate, getAllProductByCategoryNameWithPaginate, getNRelatedProductByCategoryId };
