
import axios from "axios";
export default {

    async loadOrders(context) {

        const response = await axios.get(process.env.VUE_APP_API_URI+"/orders/");

        context.commit('updateOrders',response.data);



    },

    async getOrderDetails(context,id) {
        const response = await axios.get(process.env.VUE_APP_API_URI+"/orders/"+id);
        context.commit('updateOrderDetails',response.data);
    }
}