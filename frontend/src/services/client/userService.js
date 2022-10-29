import axios from '~/utils/httpRequest';

const postRegisterNewUser = ({ firstName, lastName, phone, email, password }) => {
  return axios.post('/api/v1/register', {
    firstName,
    lastName,
    phone,
    email,
    password,
  });
};

export { postRegisterNewUser };
