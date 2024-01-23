import { createStore } from "vuex";
import ClientStore from "./clients/index.js";
import OrderStore from "./orders/index.js";
import ProductStore from "./products/index.js"

const store = createStore({
  modules: 
    {
    clients:ClientStore,
    orders: OrderStore,
    products: ProductStore
  },
});

export default store;
