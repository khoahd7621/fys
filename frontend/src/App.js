import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Collection, Default, Home, NotFound, ProductDetail } from './components';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Default />}>
          <Route index element={<Home />} />
          <Route path="collection/:type" element={<Collection />} />
          <Route path="collection/:type/:productname" element={<ProductDetail />} />
        </Route>
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
