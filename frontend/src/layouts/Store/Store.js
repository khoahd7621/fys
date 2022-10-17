import { Outlet } from 'react-router-dom';
import { Footer, Header, Navbar } from '~/components';

const Store = () => {
  return (
    <div className="Store">
      <Header />
      <Navbar />
      <Outlet />
      <Footer />
    </div>
  );
};

export default Store;
