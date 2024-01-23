import axios from "axios";

export default {
  async findAllProducts(context) {
    const response = await axios.get(
      process.env.VUE_APP_API_URI + "/products/"
    );
    context.commit("updateProducts", response.data);
  },
};
