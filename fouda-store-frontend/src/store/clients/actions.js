import axios from "axios";
export default {
  async loadClients(context) {
    console.log("from loadClients action");
    console.log(process.env.VUE_APP_TITLE);
    const response = await axios.get(process.env.VUE_APP_API_URI + "/clients/");
    context.commit("loadClients", response.data);
  },
  async save(context, payload) {
    let headers = { headers: { "Content-Type": "application/json" } };
    let bodyRequest = {
      id: payload.id,
      firstname: payload.firstname,
      lastname: payload.lastname,
      email: payload.email,
    };
    await axios.post(
      process.env.VUE_APP_API_URI + "/clients/",
      bodyRequest,
      headers
    );
    context.dispatch("loadClients");
  },

  async delete(context, id) {
    console.log(id);
    await axios.delete(process.env.VUE_APP_API_URI +"/clients/" + id);
    context.dispatch("loadClients");
  },


  async getClientDetails(context,id) {

    const response = await axios.get(process.env.VUE_APP_API_URI +"/clients/" + id);
    context.commit("loadClientDetail",response.data);


  }
};
