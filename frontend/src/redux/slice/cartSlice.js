import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  cart: {
    items: [],
  },
};

export const cartSlice = createSlice({
  name: 'cart',
  initialState,
  reducers: {
    addProductToCart: (state, action) => {
      const payload = action.payload;
      const currentIndex = state.cart.items
        .map((item) => item.product.variantSizeId)
        .indexOf(payload.product.variantSizeId);
      if (currentIndex !== -1) {
        state.cart.items[currentIndex] = {
          product: payload.product,
          quantity: state.cart.items[currentIndex].quantity + payload.quantity,
        };
      } else {
        state.cart.items.push({
          product: payload.product,
          quantity: payload.quantity,
        });
      }
    },
    removeProductFromCart: (state, action) => {},
    clearWholeCart: (state, action) => {},
  },
});

export const { addProductToCart, removeProductFromCart, clearWholeCart } = cartSlice.actions;

export default cartSlice.reducer;
