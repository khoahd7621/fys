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
  reducers: {},
});

export const {} = userSlice.actions;

export default userSlice.reducer;
