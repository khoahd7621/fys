import { useSelector } from 'react-redux';
import { Navigate } from 'react-router-dom';
import { publicRoutes } from './routes';

const AuthRoute = ({ children }) => {
  const isAuthenticated = useSelector((state) => state.user.isAuthenticated);

  if (isAuthenticated !== true) {
    return <Navigate to={publicRoutes.home} />;
  }

  return <>{children}</>;
};

export default AuthRoute;
