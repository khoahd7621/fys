import React from 'react';
import ReactDOM from 'react-dom/client';

import { Provider } from 'react-redux';
import { persistor, store } from '~/redux/store';
import { PersistGate } from 'redux-persist/integration/react';

import App from '~/App';
import GlobalStyle from '~/components/GlobalStyle/GlobalStyle';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <PersistGate loading={null} persistor={persistor}>
        <GlobalStyle>
          <App />
          <ToastContainer />
        </GlobalStyle>
      </PersistGate>
    </Provider>
  </React.StrictMode>,
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
