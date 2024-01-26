<script setup>
import { useStore } from "vuex";
import { ref, onMounted } from "vue";
import ProductItem from "../products/ProductItem.vue";
import BaseCard from "../UI/BaseCard.vue";
import { ModelSelect } from "vue-search-select";
import BaseButton from "../UI/BaseButton.vue"
import SpinnerPage from "../UI/SpinnerPage.vue"

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
  products.value.forEach((p) => {
    p.isSelectedForOrder = false;
  });
}

async function loadClientsToSelect() {
  await store.dispatch("clients/loadClients");
  let clients = store.getters["clients/findClients"];
  clientsToSelect.value = clients.map((c) => {
    return { value: c.id, text: c.id + " - " + c.firstname + " " + c.lastname };
  });
}

function createOrder(event, id) {
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
  orderToCreate.value.selectedProducts = products.value
    .filter((p) => p.isSelectedForOrder)
    .map((p) => p.id);
  await store.dispatch("orders/create", orderToCreate.value);
  cancelOrder();
}

function cancelOrder() {
  orderToCreate.value = {
    clientId: null,
    selectedProducts: [],
  };

  products.value.forEach((p) => (p.isSelectedForOrder = false));
}

function isCreateOrderEnabled() {
  if (products.value) {
    return getNumberOfSelectedProducts() > 0;
  }
}

function getSelectedProducts(){
  return products.value.filter((p) => p.isSelectedForOrder);
}
function getNumberOfSelectedProducts() {
  return getSelectedProducts().length;
}

function getPriceOfSelectedProducts() {
  return getSelectedProducts().map(p => p.prix).reduce((a,b) => a+b, 0)
}
function submitOrderDisabled() {

  let v = (orderToCreate.value.clientId == null ) || (getNumberOfSelectedProducts() < 1)
  return v;
}

onMounted(() => {
  loadProducts();
  loadClientsToSelect();
});
</script>

<template>
  <div v-if="products">
    <base-card>
      <base-button>Add product</base-button>
    </base-card>
    <base-card v-if="isCreateOrderEnabled()">
      <div>
        <span style="font-weight: bold">Number of selected products: </span>
        <span>{{ getNumberOfSelectedProducts() }}</span>
      </div>
          <div>
        <span style="font-weight: bold">price : </span>
        <span>{{ $filters.euroCurrency(getPriceOfSelectedProducts())  }}</span>
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
        <base-button  @click="submitOrder" :disabled="submitOrderDisabled()"
          >Submit order</base-button
        >
        <base-button @click="cancelOrder">Cancel order</base-button>
      </div>
    </base-card>

    <ul v-if="products">
      <li v-for="(product, index) in products" :key="product.id">
        <product-item
          v-model="products[index]"
          @add-product="createOrder"
        ></product-item>
      </li>
    </ul>
  </div>
  <div v-else>
    <spinner-page></spinner-page>
  </div>
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
