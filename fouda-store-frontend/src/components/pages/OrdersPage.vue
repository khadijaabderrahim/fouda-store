<script setup>
import BaseTable from "../UI/BaseTable.vue";
import OrderStatus from "../orders/OrderStatus.vue";
import { onMounted, ref } from "vue";
import { useStore } from "vuex";
// import axios from 'axios';

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
    <input type="text" name="orderId" id="orderId" v-model="searchOrderRequest.orderId" />
    <label for="clientId">Client id</label>
    <input type="text" name="clientId" id="clientId" v-model="searchOrderRequest.clientId"/>
    <label for="clientId">order status</label>
    <select name="orderStatus" id="orderStatus" v-model="searchOrderRequest.orderStatus">
      <option :value="null"></option>
      <option value="NEW">new</option>
      <option value="FORWARDED">forwarded</option>
      <option value="DELIVERED">delivered</option>
      <option value="CANCELED">canceled</option>
    </select>
    <div class="action-panel">
      <base-button @click="loadData">search</base-button>
    </div>
  </base-card>
  <base-table>
    <template v-slot:title>
      <span>Orders</span>
      <base-button class="add-btn" title="add order" @click="add"
        ><add-icon></add-icon
      ></base-button>
    </template>
    <template v-slot:header>
      <th>Order number</th>
      <th>Client id</th>
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
          <router-link :to="/clients/ + order.client.id">{{
            order.client.id
          }}</router-link>
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
