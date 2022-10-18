import { BreadCrumb } from '~/components';
import { AiOutlineGoogle } from 'react-icons/ai';
import { Link } from 'react-router-dom';

const Login = () => {
  return (
    <div className="login">
      <BreadCrumb current="Login an account" />
      <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] px-3 mt-8 mb-5">
        <div className="grid grid-cols-12">
          <div className="log-reg-block col-start-1 col-end-12 lg:col-start-4 lg:col-end-10">
            <h1 className="title">Login account</h1>
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
                <div>
                  <Link to={''} className="text-sm font-medium text-[#06b2eb] hover:text-black">
                    Forgot password?
                  </Link>
                </div>
                <div className="form-action">
                  <button>Login</button>
                </div>
              </form>
            </div>
            <div className="policy">
              Young Black by YB SHOP is committed to privacy and will <br /> never post or share information without
              your consent.
            </div>
            <div className="other-action">
              Don't have an account? Register <Link to={''}>here</Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
