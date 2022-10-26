import { Link } from 'react-router-dom';

import { ProSidebar, Menu, MenuItem, SubMenu, SidebarHeader, SidebarFooter, SidebarContent } from 'react-pro-sidebar';
import 'react-pro-sidebar/dist/scss/styles.scss';
import './SideBar.scss';

import { FaGem, FaGithub, FaLuggageCart, FaTshirt } from 'react-icons/fa';
import { MdDashboard, MdAccountBox } from 'react-icons/md';

const SideBar = ({ collapsed }) => {
  return (
    <ProSidebar collapsed={collapsed}>
      <SidebarHeader>
        <div className="px-5 uppercase font-bold text-lg overflow-hidden text-ellipsis whitespace-nowrap tracking-wide h-[72px]">
          <Link to="/admin/dashboard" className="text-black no-underline">
            <span className="leading-[72px] text-xl">Young Black</span>
          </Link>
        </div>
      </SidebarHeader>
      <SidebarContent>
        <Menu iconShape="circle">
          <MenuItem icon={<MdDashboard />}>
            Dashboard
            <Link to="/admin/dashboard"></Link>
          </MenuItem>
        </Menu>
        <Menu iconShape="circle">
          <MenuItem icon={<FaLuggageCart />}>
            Manage Order
            <Link to="/admin/dashboard"></Link>
          </MenuItem>
        </Menu>
        <Menu iconShape="circle">
          <MenuItem icon={<FaTshirt />}>
            Manage Product
            <Link to="/admin/dashboard"></Link>
          </MenuItem>
        </Menu>
        <Menu iconShape="circle">
          <SubMenu title={'Common Manage'} icon={<FaGem />}>
            <MenuItem>
              Manage Category
              <Link to="/admin/dashboard" />
            </MenuItem>
            <MenuItem>
              Manage Size
              <Link to="/admin/dashboard" />
            </MenuItem>
            <MenuItem>
              Manage Color
              <Link to="/admin/dashboard" />
            </MenuItem>
          </SubMenu>
        </Menu>
        <Menu iconShape="circle">
          <MenuItem icon={<MdAccountBox />}>
            Manage Account
            <Link to="/admin/dashboard"></Link>
          </MenuItem>
        </Menu>
      </SidebarContent>
      <SidebarFooter className="text-center text-white text-sm">
        <div className="my-4 mx-8 p-2 border rounded-3xl bg-black">
          <a
            href="https://github.com/khoahd7621"
            target="_blank"
            className="flex flex-col items-center"
            rel="noopener noreferrer"
          >
            <FaGithub />
            <span className="whitespace-nowrap overflow-hidden text-ellipsis">By KhoaHD7621</span>
          </a>
        </div>
      </SidebarFooter>
    </ProSidebar>
  );
};

export default SideBar;
