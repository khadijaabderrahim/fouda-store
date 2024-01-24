<script setup>
import { useStore } from "vuex";
import { ref, onMounted } from "vue";
import ProductItem from "../products/ProductItem.vue";
import BaseCard from "../UI/BaseCard.vue";
import { ModelSelect } from "vue-search-select";

const store = useStore();
const products = ref(null);

const orderToCreate = ref({
  clientId: null,
  selectedProducts: [],
});

const clientsToSelect = ref();

async function loadProducts() {
  await store.dispatch("products/findAllProducts");
  products.value = store.getters["products/findProducts"];
}

async function loadClientsToSelect() {
  await store.dispatch("clients/loadClients");
  let clients = store.getters["clients/findClients"];
  clientsToSelect.value = clients.map((c) => {
    return { value: c.id, text: c.id + " - " + c.firstname + " " + c.lastname };
  });
}

function createOrder(event, id) {
  console.log(event.target)
  console.log(id)
  if (event.target.checked) {
    orderToCreate.value.selectedProducts.push(id);
  } else {
    const index = orderToCreate.value.selectedProducts.indexOf(id);
    if (index > -1) {
      // only splice array when item is found
      orderToCreate.value.selectedProducts.splice(index, 1); // 2nd parameter means remove one item only
    }
  }
}

async function submitOrder() {
  await store.dispatch("orders/create", orderToCreate.value);
  cancelOrder();
}

function cancelOrder() {
  orderToCreate.value = {
    clientId: null,
    selectedProducts: [],
  };
}

function isCreateOrderDisabked(){
  return this.orderToCreate.selectedProducts.length < 1;
}
onMounted(() => {
  loadProducts();
  loadClientsToSelect();
});
</script>

<template>
  <base-card>
    <base-button class="btn" >Add product</base-button>
    <base-button class="btn" :class="{ disabled: isCreateOrderDisabked }"
      >Create order</base-button
    >
  </base-card>
  <base-card v-if="orderToCreate.selectedProducts.length > 0">
    <div>
      <span style="font-weight: bold">Number of selected products: </span>
      <span>{{ orderToCreate.selectedProducts.length }}</span>
    </div>
    <div class="form-group">
      <label for="clientsSelector">Client: </label>
      <model-select
        class="search-client"
        id="clientsSelector"
        :options="clientsToSelect"
        v-model="orderToCreate.clientId"
        name="my name"
        placeholder="Select client"
      >
      </model-select>
    </div>

    <div class="form-group">
      <base-button class="btn" @click="submitOrder">Submit order</base-button>
      <base-button class="btn" @click="cancelOrder">Cancel order</base-button>
    </div>
  </base-card>

  <ul v-if="products">
    <li v-for="product in products" :key="product.id">
      <product-item
        :product="product"
        @add-product="createOrder"
      ></product-item>
    </li>
  </ul>
</template>

<style scoped>
span {
  margin-top: 500px;
}

li {
  float: left;
  list-style: none;
  margin: 0px auto;
  max-width: 250px;
  max-height: 300px;
  height: auto;
}

.ui.selection.dropdown {
  min-height: 14px;
  width: 50%;
  margin-left: 10px;
}
</style>
