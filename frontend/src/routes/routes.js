const publicRoutes = {
  home: '/',
  collection: '/collection',
  cart: '/cart',
  search: '/search',
  checkout: '/checkout',
  checkoutSuccess: '/checkout/thankyou',
  notFound: '*',
};

const privateRoutes = {
  login: '/login',
  register: '/register',
  recoverPassword: '/recover-password',
  account: '/account',
  order: '/account/order',
  orderDetail: '/account/order/:orderId',
  changePassword: '/account/change-password',
  addressNotebook: '/account/addresses',
};

export { publicRoutes, privateRoutes };
