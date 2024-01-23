export default {

    updateOrders(state,payload) {
        state.orders = payload;
    },

    updateOrderDetails(state,payload) {
        state.orderDetails = payload;
        console.log(state.orderDetails);
    }
}