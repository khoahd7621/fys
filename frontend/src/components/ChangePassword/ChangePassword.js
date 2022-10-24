const ChangePassword = () => {
  return (
    <div className="change-password">
      <div className="title">
        <h2 className="text-xl uppercase mb-7">Change password</h2>
      </div>
      <div className="content">
        <p>To ensure security please set a password with at least 8 characters</p>
        <form className="form mt-5">
          <div className="form-group">
            <label htmlFor="old-password">
              Old password <span>*</span>
            </label>
            <input id="old-password" type="password" className="form-control" placeholder="Enter old password" />
            <p className="form-text"></p>
          </div>
          <div className="form-group">
            <label htmlFor="new-password">
              New password <span>*</span>
            </label>
            <input id="new-password" type="password" className="form-control" placeholder="Enter new password" />
            <p className="form-text"></p>
          </div>
          <div className="form-group">
            <label htmlFor="confirm-new-password">
              Confirm new password <span>*</span>
            </label>
            <input
              id="confirm-new-password"
              type="password"
              className="form-control"
              placeholder="Confirm new password"
            />
            <p className="form-text"></p>
          </div>
          <div className="form-action w-14">
            <button>Change password</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ChangePassword;
