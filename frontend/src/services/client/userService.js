import axios from '~/utils/httpRequest';

const putChangePassword = (oldPassword, newPassword, confirmPassword) => {
  return axios.put(`/api/v1/user/change-password`, {
    oldPassword,
    newPassword,
    confirmPassword,
  });
};

export { putChangePassword };
