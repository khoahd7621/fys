import axios from '~/utils/httpRequest';

const postCreateNewOrder = (payload) => {
  return axios.post('/api/v1/order', {
    ...payload,
  });
};

const getOrderByCode = (code) => {
  return axios.get(`/api/v1/order/${code}`);
};

const getAllOrderOfUser = (userId) => {
  return axios.get(`/api/v1/order/all?user-id=${userId}`);
};

const getOrderWithDetailOfUser = (orderId) => {
  return axios.get(`/api/v1/order/detail?order-id=${orderId}`);
};

export { postCreateNewOrder, getOrderByCode, getAllOrderOfUser, getOrderWithDetailOfUser };
