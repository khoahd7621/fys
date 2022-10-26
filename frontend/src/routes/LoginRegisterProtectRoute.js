import { useSelector } from 'react-redux';
import { Navigate, useLocation } from 'react-router-dom';
import { toast } from 'react-toastify';
import { publicRoutes } from './routes';

const LoginRegisterProtectRoute = ({ children }) => {
  const location = useLocation();
  const isAuthenticated = useSelector((state) => state.user.isAuthenticated);

  const currentPath = location.pathname
    .slice(location.pathname.lastIndexOf('/') + 1, location.pathname.length)
    .toLocaleLowerCase();

  if (isAuthenticated === true && currentPath === 'login') {
    toast.error('You need to logout before access login page');
    return <Navigate to={publicRoutes.home} />;
  }

  if (isAuthenticated === true && currentPath === 'register') {
    toast.error('You need to logout before access register page');
    return <Navigate to={publicRoutes.home} />;
  }

  return <>{children}</>;
};

export default LoginRegisterProtectRoute;
