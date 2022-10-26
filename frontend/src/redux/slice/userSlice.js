import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  account: {
    id: '',
    email: '',
    phone: '',
    address: '',
    firstName: '',
    lastName: '',
    role: '',
    status: '',
    createdAt: '',
    updateAt: '',
    accessToken: '',
    refreshToken: '',
  },
  isAuthenticated: false,
};

export const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    fetchUserLoginSuccess: (state, action) => {
      console.log(action.payload);
      state.account = {
        ...action.payload,
      };
      state.isAuthenticated = true;
    },
  },
});

export const { fetchUserLoginSuccess } = userSlice.actions;

export default userSlice.reducer;
