const Validation = {
  isValidEmail: (email) => {
    return String(email)
      .toLowerCase()
      .match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
      );
  },
  isMinLength: (string, min) => {
    return string.length >= min;
  },
  isMaxLength: (string, max) => {
    return string.length <= max;
  },
};

export default Validation;
