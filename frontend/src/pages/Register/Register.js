import { BreadCrumb } from '~/components';
import { AiOutlineGoogle } from 'react-icons/ai';
import { Link } from 'react-router-dom';
import { privateRoutes } from '~/routes/routes';

const Register = () => {
  return (
    <div className="register">
      <BreadCrumb current="Register an account" />
      <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] px-3 mt-8 mb-5">
        <div className="grid grid-cols-12">
          <div className="log-reg-block col-start-1 col-end-12 lg:col-start-4 lg:col-end-10">
            <h1 className="title">Register an account</h1>
            <div className="by-oauth2">
              <button className="google">
                <span className="image">
                  <AiOutlineGoogle />
                </span>
                <span className="name">Google</span>
              </button>
            </div>
            <div className="devider">
              <span>Or</span>
            </div>
            <div className="by-account">
              <form className="form">
                <div className="grid grid-cols-2 gap-4">
                  <div className="form-group">
                    <label htmlFor="last-name">
                      Last name <span>*</span>
                    </label>
                    <input id="last-name" className="form-control" type="text" placeholder="Enter last name" />
                    <p className="form-text"></p>
                  </div>
                  <div className="form-group">
                    <label htmlFor="first-name">
                      First name <span>*</span>
                    </label>
                    <input id="first-name" className="form-control" type="text" placeholder="Enter first name" />
                    <p className="form-text"></p>
                  </div>
                </div>
                <div className="form-group">
                  <label htmlFor="phone">
                    Phone <span>*</span>
                  </label>
                  <input id="phone" className="form-control" type="text" placeholder="Enter phone number" />
                  <p className="form-text"></p>
                </div>
                <div className="form-group">
                  <label htmlFor="email">
                    Email <span>*</span>
                  </label>
                  <input id="email" className="form-control" type="email" placeholder="Enter email address" />
                  <p className="form-text"></p>
                </div>
                <div className="form-group">
                  <label htmlFor="password">
                    Password <span>*</span>
                  </label>
                  <input id="password" className="form-control" type="password" placeholder="Enter password" />
                  <p className="form-text"></p>
                </div>
                <div className="form-action">
                  <button>Create an account</button>
                </div>
              </form>
            </div>
            <div className="policy">
              Young Black by YB SHOP is committed to privacy and will <br /> never post or share information without
              your consent.
            </div>
            <div className="other-action">
              Already have an account? Login <Link to={privateRoutes.login}>here</Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;
