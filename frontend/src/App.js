import { BrowserRouter, Routes, Route } from 'react-router-dom';

import { Header, Navbar, Footer } from './components/Common';
import { Collection, Home, NotFound } from './components';

const App = () => {
  return (
    <BrowserRouter>
      <div className="App">
        <Header />
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/collection/:type" element={<Collection />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
        <Footer />
      </div>
    </BrowserRouter>
  );
};

export default App;
