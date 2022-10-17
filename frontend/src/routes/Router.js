import { BrowserRouter, Routes, Route } from 'react-router-dom';

import { NotFound, Store } from '~/layouts';
import { publicRoutes } from '~/routes/routes';

import { Cart, Collection, Home, ProductDetail } from '~/pages';

const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={publicRoutes.home} element={<Store />}>
          <Route index element={<Home />} />
          <Route path={`${publicRoutes.collection}/:type`} element={<Collection />} />
          <Route path={`${publicRoutes.collection}/:type/:productname`} element={<ProductDetail />} />
          <Route path={`${publicRoutes.cart}`} element={<Cart />} />
        </Route>
        <Route path={publicRoutes.notFound} element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default Router;
