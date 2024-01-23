
import axios from "axios";
export default {

    async loadOrders(context,searchOrderRequest) {

        const response = await axios.post(process.env.VUE_APP_API_URI+"/orders/search",searchOrderRequest,{headers:{"Content-Type": "application/json"}});

        context.commit('updateOrders',response.data);



    },

    async getOrderDetails(context,id) {
        const response = await axios.get(process.env.VUE_APP_API_URI+"/orders/"+id);
        context.commit('updateOrderDetails',response.data);
    }
}