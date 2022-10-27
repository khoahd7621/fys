const Validation = {
  isValidEmail: (email) => {
    return String(email)
      .toLowerCase()
      .match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
      );
  },
  isValidPhone: (phone) => {
    return String(phone)
      .toLowerCase()
      .match(/(84|0[3|5|7|8|9])+([0-9]{8})\b/g);
  },
  isInRange: (string, min, max) => {
    return String(string).length >= min && string.length <= max;
  },
  isMinLength: (string, min) => {
    return String(string).length >= min;
  },
  isMaxLength: (string, max) => {
    return String(string).length <= max;
  },
  isEmpty: (string) => {
    return String(string).trim().length === 0;
  },
};

export default Validation;
