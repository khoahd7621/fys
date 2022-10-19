import { Link, Outlet, useLocation } from 'react-router-dom';
import classNames from 'classnames';

import { BreadCrumb } from '~/components';
import { privateRoutes } from '~/routes/routes';
import { Nested } from '~/components/BreadCrumb/BreadCrumb';

const Account = () => {
  const location = useLocation();
  const nestedOrderRoutes = location.pathname.slice(0, location.pathname.lastIndexOf('/'));

  return (
    <div className="account-page">
      <BreadCrumb current={location.pathname.slice(location.pathname.lastIndexOf('/') + 1, location.pathname.length)}>
        {nestedOrderRoutes && nestedOrderRoutes === privateRoutes.order && (
          <Nested
            path={nestedOrderRoutes}
            name={nestedOrderRoutes.slice(nestedOrderRoutes.lastIndexOf('/') + 1, nestedOrderRoutes.length)}
          />
        )}
      </BreadCrumb>
      <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] px-3 py-5 mb-8">
        <div className="grid grid-cols-12">
          <div className="col-span-3">
            <section className="side-bar">
              <div className="title">
                <h2 className="text-xl uppercase mb-1">Account page</h2>
                <p className="font-bold">Welcome, Hoang Dang Khoa!</p>
              </div>
              <ul className="options mt-10">
                <li className={classNames('my-2', { 'font-semibold': location.pathname === privateRoutes.account })}>
                  <Link to={privateRoutes.account}>Account information</Link>
                </li>
                <li
                  className={classNames('my-2', {
                    'font-semibold':
                      location.pathname === privateRoutes.order || nestedOrderRoutes === privateRoutes.order,
                  })}
                >
                  <Link to={privateRoutes.order}>Your order</Link>
                </li>
                <li
                  className={classNames('my-2', {
                    'font-semibold': location.pathname === privateRoutes.changePassword,
                  })}
                >
                  <Link to={privateRoutes.changePassword}>Change password</Link>
                </li>
                <li
                  className={classNames('my-2', {
                    'font-semibold': location.pathname === privateRoutes.addressNotebook,
                  })}
                >
                  <Link to={privateRoutes.addressNotebook}>Address notebook (1)</Link>
                </li>
              </ul>
            </section>
          </div>
          <div className="col-span-9">
            <span>Right</span>
            <Outlet />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Account;
