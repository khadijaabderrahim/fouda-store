<script setup>
import {defineProps, ref} from "vue";
import {useStore} from "vuex";
import SpinnerPage from "@/components/UI/SpinnerPage";
import BaseCard from "@/components/UI/BaseCard";
import BaseTable from "@/components/UI/BaseTable";
import OrderStatus from "@/components/orders/OrderStatus";

const order = ref(null);
const props = defineProps(["orderId"]);
const store = useStore();

async function loadOrderDetails() {
  await store.dispatch("orders/getOrderDetails", props.orderId);
  order.value = await store.getters["orders/getOrderDetails"];
}

function totalAmount() {
  return order.value.productList.map(p => p.prix).reduce((a, b) => a + b, 0);
}

loadOrderDetails();
</script>

<template>
  <div v-if="order">
    <base-card>
      <div class="form-group">
        <h4>
          <label>Order number</label>
          <span>{{ order.id }}</span>
        </h4>
      </div>
      <div class="form-group">
        <label for="client">Client </label>
        <span id="client">{{order.client.id}}  -  {{ order.client.firstname }} {{order.client.lastname}}</span>
      </div>
      <div class="form-group">
        <label for="orderDate">Order date </label>
        <span id="orderDate">{{ $filters.formatDate(order.orderDate) }}</span>
      </div>
      <div class="form-group">
        <label for="itemsNumber">Items number </label>
        <span id="itemsNumber">{{ order.productList.length }}</span>
      </div>
      <div class="form-group">
        <label for="itemsTotalAmount">Total amount </label>
        <span id="itemsTotalAmount">{{ $filters.euroCurrency(totalAmount()) }}</span>
      </div>
      <div class="form-group">
        <label for="status">Status </label>
        <order-status id="status" :status="order.status"></order-status>
      </div>
    </base-card>
    <base-table>
      <template v-slot:header>
        <th>Id</th>
        <th>Label</th>
        <th>Description</th>
        <th>Price</th>
        <th>Rating</th>
      </template>

      <template v-slot:body>
        <tr v-for="product in order.productList" :key="product.id">
          <td>{{ product.id }}</td>
          <td>{{ product.label }}</td>
          <td>{{ product.description }}</td>
          <td>{{ product.prix }}</td>
          <td>{{ product.rating }}/10</td>
        </tr>
      </template>
    </base-table>
  </div>
  <div v-else>
    <spinner-page></spinner-page>
  </div>
</template>
<style scoped>
.form-group label {
  padding-top: 0px;
}
</style>
