import getters from "../products/getters";
import mutations from "../products/mutations";
import actions from "../products/actions";

export default {
  namespaced: true,
  state() {
    return {
      products: [],
    };
  },
  getters: getters,
  mutations: mutations,
  actions: actions,
};
