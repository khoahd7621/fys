import axios from '~/utils/httpRequest';

const putChangePassword = (oldPassword, newPassword, confirmPassword) => {
  return axios.put(`/api/v1/user/change-password`, {
    oldPassword,
    newPassword,
    confirmPassword,
  });
};

const putUpdateUserInformation = (firstName, lastName, phone, address) => {
  return axios.put(`/api/v1/user`, {
    firstName,
    lastName,
    phone,
    address,
  });
};

export { putChangePassword, putUpdateUserInformation };
