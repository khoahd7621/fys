import { FaBars } from 'react-icons/fa';

const Navbar = ({ collapsed, setCollapsed }) => {
  return (
    <nav className="relative w-full flex flex-wrap items-center justify-between py-4 bg-gray-100 text-gray-500 hover:text-gray-700 focus:text-gray-700 shadow navbar navbar-expand navbar-light">
      <div className="container-fluid w-full flex flex-wrap items-center justify-between px-6">
        <div className="collapse navbar-collapse flex-grow items-center">
          <ul className="navbar-nav flex flex-col pl-0 list-style-none mr-auto">
            <li className="nav-item p-2">
              <button
                className="nav-link text-gray-500 hover:text-gray-700 focus:text-gray-700 p-0"
                onClick={() => setCollapsed(!collapsed)}
              >
                <FaBars />
              </button>
            </li>
          </ul>
        </div>
        <div className="flex items-center relative">
          <div className="dropdown relative">
            <button
              className="dropdown-toggle flex items-center hidden-arrow"
              id="dropdownMenuButton2"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <img
                src="https://mdbootstrap.com/img/new/avatars/2.jpg"
                className="rounded-full h-8 w-8"
                alt="avatar"
                loading="lazy"
              />
            </button>
            <ul
              className="dropdown-menu min-w-max absolute hidden bg-white text-base z-50 float-left py-2 list-none text-left rounded-lg shadow-lg mt-1 m-0 bg-clip-padding border-none left-auto right-0"
              aria-labelledby="dropdownMenuButton2"
            >
              <li>
                <button className="dropdown-item text-sm py-2 px-4 font-normal block w-full whitespace-nowrap bg-transparent text-gray-700 hover:bg-gray-100 ">
                  Logout
                </button>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
