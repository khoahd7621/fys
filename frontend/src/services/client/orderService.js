import axios from '~/utils/httpRequest';

const postCreateNewOrder = (payload) => {
  return axios.post('/api/v1/order', {
    ...payload,
  });
};

const getOrderByCode = (code) => {
  return axios.get(`/api/v1/order/${code}`);
};

export { postCreateNewOrder, getOrderByCode };
