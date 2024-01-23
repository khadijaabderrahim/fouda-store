export default {
  loadClients(state, payload) {
    state.clients = payload;
  },

  loadClientDetail(state,payload) {
    console.log(payload);
    state.clientDetails = payload;
  }
};
