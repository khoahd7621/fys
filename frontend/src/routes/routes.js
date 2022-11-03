const publicRoutes = {
  home: '/',
  collection: '/collection',
  cart: '/cart',
  search: '/search',
  checkout: '/checkout',
  checkoutSuccess: '/checkout/thankyou',
};

const privateRoutes = {
  login: '/login',
  register: '/register',
  recoverPassword: '/recover-password',
  account: '/account',
  order: '/account/order',
  orderDetail: '/account/order/:orderId',
  changePassword: '/account/change-password',
};

const adminRoutes = {
  default: '/admin',
  dashboard: '/admin/dashboard',
  manageCategory: '/admin/manage-category',
  manageSize: '/admin/manage-size',
  manageColor: '/admin/manage-color',
  manageProduct: '/admin/manage-product',
  manageUser: '/admin/manage-user',
};

export { publicRoutes, privateRoutes, adminRoutes };
