import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Collection, Default, Home, NotFound } from './components';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Default />}>
          <Route index element={<Home />} />
          <Route path="/collection/:type" element={<Collection />} />
        </Route>
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
