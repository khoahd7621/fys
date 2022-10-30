import { useSelector } from 'react-redux';
import { Navigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { publicRoutes } from './routes';

const ClientPrivateRoute = ({ children }) => {
  const isAuthenticated = useSelector((state) => state.user.isAuthenticated);

  if (isAuthenticated !== true) {
    toast.error('You need to login to access this page');
    return <Navigate to={publicRoutes.home} />;
  }

  return <>{children}</>;
};

export default ClientPrivateRoute;
