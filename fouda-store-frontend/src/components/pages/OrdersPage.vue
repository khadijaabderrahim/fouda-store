<script setup>
import BaseTable from "../UI/BaseTable.vue";
import OrderStatus from "../orders/OrderStatus.vue";
import BaseButton from "../UI/BaseButton";
import { onMounted, ref } from "vue";
import { useStore } from "vuex";
import SpinnerPage from "@/components/UI/SpinnerPage";

const store = useStore();
const orders = ref();

const searchOrderRequest = {
  orderId: null,
  clientId: null,
  orderStatus: null,

}

async function loadData() {
  await store.dispatch("orders/loadOrders",searchOrderRequest);
  orders.value = store.getters["orders/findOrders"];
}

function totalPrice(item) {
  return item.productList.map((p) => p.prix).reduce((p1, p2) => p1 + p2, 0);
}


onMounted(() => {
  loadData();
});
</script>

<template>
  <base-card>
    <label for="orderId">Order id</label>
    <input type="number" name="orderId" id="orderId" v-model="searchOrderRequest.orderId" />
    <label for="clientId">Client</label>
    <input type="number" name="clientId" id="clientId" v-model="searchOrderRequest.clientId"/>
    <label for="clientId">order status</label>
    <select name="orderStatus" id="orderStatus" v-model="searchOrderRequest.orderStatus">
      <option :value="null"></option>
      <option value="NEW">new</option>
      <option value="FORWARDED">forwarded</option>
      <option value="DELIVERED">delivered</option>
      <option value="CANCELED">canceled</option>
    </select>
    <div class="action-panel">
      <base-button class="btn" @click="loadData">search</base-button>
    </div>
  </base-card>
  <base-table v-if="orders">
    <template v-slot:header>
      <th>Order number</th>
      <th>Client</th>
      <th>Order date</th>
      <th>Order status</th>
      <th>Items number</th>
      <th>Total price</th>
      <th>Actions</th>
    </template>

    <template v-slot:body>
      <tr v-for="order in orders" :key="order.id">
        <td>{{ order.id }}</td>
        <td>
          <router-link :to="/clients/ + order.client.id">
            {{order.client.id}} - {{ order.client.firstname }} - {{ order.client.lastname }}
          </router-link>
        </td>
        <td>{{ $filters.formatDate(order.orderDate) }}</td>
        <td><order-status :status="order.status"></order-status></td>
        <td>{{ order.productList.length }}</td>
        <td>{{ $filters.euroCurrency(totalPrice(order)) }}</td>
        <td>
          <router-link :to="/orders/ + order.id"
            ><view-icon></view-icon
          ></router-link>
        </td>
      </tr>
    </template>
  </base-table>
  <div v-else>
    <spinner-page></spinner-page>
  </div>
</template>

<style scoped>
a:link,
:visited {
  color: var(--gris-fonce);
}

a:hover,
a:active {
  color: rgb(164, 69, 69);
}
</style>
