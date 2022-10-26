import { BrowserRouter, Route, Routes } from 'react-router-dom';

import { Admin, NotFound, Store } from '~/layouts';
import { adminRoutes, privateRoutes, publicRoutes } from '~/routes/routes';

import {
  Cart,
  Collection,
  Home,
  ProductDetail,
  SearchResult,
  Login,
  Register,
  RecoverPassword,
  Checkout,
  CheckoutSuccess,
  Account,
} from '~/pages';
import { ChangePassword, Order, OrderDetail, UserInfo } from '~/components';
import { Dashboard } from '~/layouts/Admin/page';

const AppRouter = () => {
  return (
    <BrowserRouter>
      <Routes>
        {/* Store */}
        <Route path={publicRoutes.home} element={<Store />}>
          <Route index element={<Home />} />
          <Route path={`${publicRoutes.collection}/:type`} element={<Collection />} />
          <Route path={`${publicRoutes.collection}/:type/:productname`} element={<ProductDetail />} />
          <Route path={`${publicRoutes.cart}`} element={<Cart />} />
          <Route path={publicRoutes.search} element={<SearchResult />} />
          <Route path={privateRoutes.account} element={<Account />}>
            <Route index element={<UserInfo />} />
            <Route path={privateRoutes.order} element={<Order />} />
            <Route path={privateRoutes.orderDetail} element={<OrderDetail />} />
            <Route path={privateRoutes.changePassword} element={<ChangePassword />} />
          </Route>
          <Route path={privateRoutes.recoverPassword} element={<RecoverPassword />} />
          {/* Sign in/Sign up */}
          <Route path={privateRoutes.login} element={<Login />} />
          <Route path={privateRoutes.register} element={<Register />} />
        </Route>
        <Route path={publicRoutes.checkout} element={<Checkout />} />
        <Route path={`${publicRoutes.checkoutSuccess}/:orderId`} element={<CheckoutSuccess />} />

        {/* Admin */}
        <Route path={adminRoutes.default} element={<Admin />}>
          <Route index element={<Dashboard />} />
          <Route index path={adminRoutes.dashboard} element={<Dashboard />} />
        </Route>

        {/* Notfound */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRouter;
