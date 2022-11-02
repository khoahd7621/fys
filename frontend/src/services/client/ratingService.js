import axios from '~/utils/httpRequest';

const postRatingProduct = (productId, star, title, content) => {
  return axios.post('/api/v1/rating', {
    productId,
    star,
    title,
    content,
  });
};

export { postRatingProduct };
