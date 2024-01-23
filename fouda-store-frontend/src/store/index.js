import { createStore } from "vuex";
import ClientStore from "./clients/index.js";
import OrderStore from "./orders/index.js"

const store = createStore({
  modules: 
    {
    clients:ClientStore,
    orders: OrderStore
  },
});

export default store;
