export default {
  loadClients(state, payload) {
    state.clients = payload;
  },

  loadClientDetail(state,payload) {
    state.clientDetails = payload;
  }
};
