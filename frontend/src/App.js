import { BrowserRouter, Routes, Route } from 'react-router-dom';

import { Collection, Default, Home, NotFound, ProductDetail } from '~/components';
import { publicRoutePath } from '~/routes/constVars';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={publicRoutePath.home} element={<Default />}>
          <Route index element={<Home />} />
          <Route path={`${publicRoutePath.collection}/:type`} element={<Collection />} />
          <Route path={`${publicRoutePath.collection}/:type/:productname`} element={<ProductDetail />} />
        </Route>
        <Route path={publicRoutePath.notFound} element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
