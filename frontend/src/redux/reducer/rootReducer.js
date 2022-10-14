import { combineReducers } from 'redux';
import counterReducer from '../slice/counterSlice';

const rootReducer = combineReducers({
  counter: counterReducer,
});

export default rootReducer;
