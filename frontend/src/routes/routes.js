const publicRoutes = {
  home: '/',
  collection: '/collection',
  cart: '/cart',
  search: '/search',
  notFound: '*',
};

const privateRoutes = {
  login: '/login',
  register: '/register',
  recoverPassword: '/recover-password',
};

export { publicRoutes, privateRoutes };
