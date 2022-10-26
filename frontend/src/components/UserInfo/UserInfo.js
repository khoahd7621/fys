import React from 'react';

const UserInfo = () => {
  return (
    <div className="user-infor">
      <div className="title">
        <h2 className="text-xl uppercase mb-7">Account information</h2>
      </div>
      <div className="content">
        <div className="mb-4 font-bold">
          Fullname: <span className="font-normal">Hoang Dang Khoa</span>
        </div>
        <div className="mb-4 font-bold">
          Email: <span className="font-normal">example@gmail.com</span>
        </div>
        <div className="mb-4 font-bold">
          Phone number: <span className="font-normal">0123456789</span>
        </div>
        <div className="mb-4 font-bold">
          Address: <span className="font-normal">123 Le Van Viet, Tp. Thu Duc, Tp. Ho Chi Minh</span>
        </div>
        <div className="form mt-6 mb-4">
          <div className="form-action max-w-[300px]">
            <button>Update account</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserInfo;
