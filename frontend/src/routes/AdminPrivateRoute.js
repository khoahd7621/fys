import { useSelector } from 'react-redux';
import { Navigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { publicRoutes } from './routes';

const AdminPrivateRoute = ({ children }) => {
  const { isAuthenticated, account } = useSelector((state) => state.user);

  if (isAuthenticated !== true) {
    toast.error('You need to login to access this page');
    return <Navigate to={publicRoutes.home} />;
  }

  if (isAuthenticated === true && account.role.toLocaleLowerCase() === 'admin') {
    return <>{children}</>;
  } else {
    toast.error("You don't have permission to access this page");
    return <Navigate to={publicRoutes.home} />;
  }
};

export default AdminPrivateRoute;
