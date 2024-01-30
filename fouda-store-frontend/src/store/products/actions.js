import axios from "axios";

export default {
  async findAllProducts(context) {
    const response = await axios.get(
      process.env.VUE_APP_API_URI + "/products/"
    );
    context.commit("updateProducts", response.data);
  },
  async save(context, payload) {
    let bodyRequest = {
      label : payload.label,
      description : payload.description,
      prix: payload.prix,
      rating: payload.rating,
      image: payload.image,
    };
    await axios.post(
        process.env.VUE_APP_API_URI + "/products/",
        bodyRequest
    );
    context.dispatch("findAllProducts");
  },

};
