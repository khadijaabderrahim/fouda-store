
<script setup>
import { defineProps, ref , onMounted } from "vue";
import { useStore } from "vuex";

import BaseTable from '../UI/BaseTable.vue';
import OrderStatus from '../orders/OrderStatus.vue'



const props = defineProps(["clientId"]);
const store = useStore();
const reactiveClient = ref({client:{},orders:[]});

async function loadClientDetails(id) {
  await store.dispatch("clients/getClientDetails", id);
  reactiveClient.value = store.getters["clients/getClientDetails"];

}

onMounted(() => {
  loadClientDetails(props.clientId);
});
</script>
<template>
  <base-card class="my-class">
    <h4>
      <span
        >{{ reactiveClient.client.firstname }}
        {{ reactiveClient.client.lastname }}
      </span>
    </h4>
    <div>
      <label for="email">Email : </label>
      <span id="email">{{ reactiveClient.client.email }}</span>
    </div>
  </base-card>

     <base-table>
      <template v-slot:title>
        <span>Orders</span>
      </template>
      <template v-slot:header>
        <th>Order number</th>
        <th>Order date</th>
        <th>Order status</th>
        <th>Total price</th>
      </template>

      <template v-slot:body>
        <tr v-for="order in reactiveClient.orders" :key="order.id">
          <td>
            <router-link :to="/orders/+order.number" >{{ order.number }}</router-link>
            </td>
          <td>{{ $filters.formatDate(order.date) }}</td>
          <td><order-status :status="order.status"></order-status></td>
          <td>{{$filters.euroCurrency(order.price)}}</td>
        </tr>
      </template>
    </base-table>
</template>
<style scoped>
label {
  font-weight: 600;
}

li {
  list-style: none;
}

.card.my-class {
  margin: 50px 0px;
  width: 400px;
}

h4 {
  color: #17a195;
}

summary {
  font-weight: 600;
}
</style>
