<script setup>
import { defineProps, ref } from "vue";
import { useStore } from "vuex";

const order = ref(null);
const props = defineProps(["orderId"]);
const store = useStore();

async function loadOrderDetails() {
  await store.dispatch("orders/getOrderDetails", props.orderId);
  order.value = await store.getters["orders/getOrderDetails"];
}

loadOrderDetails();
</script>

<template>
  <div v-if="order">
    <div>order details</div>
    <div>{{ order.client.firstname }} {{ order.client.lastname }}</div>
    <div>{{ order.client.email }}</div>
    <ul>
      <li v-for="p in order.productList" :key="p.id">
        {{ p.label }} {{ p.prix }}
      </li>
    </ul>
  </div>
  <div v-else>
    <span>loading orders ...</span>
  </div>
</template>
