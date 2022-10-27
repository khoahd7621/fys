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
import { Dashboard, ManageCategory, ManageColor, ManageSize } from '~/layouts/Admin/page';

import ClientPrivateRoute from './ClientPrivateRoute';
import AdminPrivateRoute from './AdminPrivateRoute';
import LoginRegisterProtectRoute from './LoginRegisterProtectRoute';

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
          <Route
            path={privateRoutes.account}
            element={
              <ClientPrivateRoute>
                <Account />
              </ClientPrivateRoute>
            }
          >
            <Route index element={<UserInfo />} />
            <Route path={privateRoutes.order} element={<Order />} />
            <Route path={privateRoutes.orderDetail} element={<OrderDetail />} />
            <Route path={privateRoutes.changePassword} element={<ChangePassword />} />
          </Route>
          <Route path={privateRoutes.recoverPassword} element={<RecoverPassword />} />
          {/* Sign in/Sign up */}
          <Route
            path={privateRoutes.login}
            element={
              <LoginRegisterProtectRoute>
                <Login />
              </LoginRegisterProtectRoute>
            }
          />
          <Route
            path={privateRoutes.register}
            element={
              <LoginRegisterProtectRoute>
                <Register />
              </LoginRegisterProtectRoute>
            }
          />
        </Route>
        <Route path={publicRoutes.checkout} element={<Checkout />} />
        <Route path={`${publicRoutes.checkoutSuccess}/:orderId`} element={<CheckoutSuccess />} />

        {/* Admin */}
        <Route
          path={adminRoutes.default}
          element={
            <AdminPrivateRoute>
              <Admin />
            </AdminPrivateRoute>
          }
        >
          <Route index element={<Dashboard />} />
          <Route index path={adminRoutes.dashboard} element={<Dashboard />} />
          <Route index path={adminRoutes.manageCategory} element={<ManageCategory />} />
          <Route index path={adminRoutes.manageSize} element={<ManageSize />} />
          <Route index path={adminRoutes.manageColor} element={<ManageColor />} />
        </Route>

        {/* Notfound */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRouter;
