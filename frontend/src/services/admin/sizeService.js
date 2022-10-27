import axios from '~/utils/httpRequest';

const getListSizes = () => {
  return axios.get('/api/v1/admin/size');
};

const putUpdateSize = (id, newSize) => {
  return axios.put('/api/v1/admin/size', {
    id,
    newSize,
  });
};

const postCreateNewSize = (size) => {
  return axios.post('/api/v1/admin/size', {
    size,
  });
};

export { getListSizes, putUpdateSize, postCreateNewSize };
