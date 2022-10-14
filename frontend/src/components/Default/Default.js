import { Outlet } from 'react-router-dom';
import { Footer, Header, Navbar } from '../Common';

const Default = () => {
  return (
    <div className="Default">
      <Header />
      <Navbar />
      <Outlet />
      <Footer />
    </div>
  );
};

export default Default;
