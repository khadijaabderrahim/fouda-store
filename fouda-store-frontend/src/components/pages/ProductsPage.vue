<script setup>
import { useStore } from "vuex";
import { ref, onMounted } from "vue";
import ProductItem from "../products/ProductItem.vue";
import BaseCard from "../UI/BaseCard.vue";

const store = useStore();
const products = ref(null);
const selectedProducts = ref([]);

const active = ref(true);


async function loadProducts() {
  await store.dispatch("products/findAllProducts");
  products.value = store.getters["products/findProducts"];
}

function createOrder(event, id) {
  if (event.target.checked) {
    selectedProducts.value.push(id);
  } else {
    const index = selectedProducts.value.indexOf(id);
    if (index > -1) {
      // only splice array when item is found
      selectedProducts.value.splice(index, 1); // 2nd parameter means remove one item only
    }
  }

  if(selectedProducts.value.length > 0) {
    active.value = false;
  } else {
    active.value = true;
  }
  console.log(selectedProducts.value.length);
}

onMounted(() => {
  loadProducts();
});
</script>

<template>
  <base-card>
    <base-button class="btn">Add product</base-button>
    <base-button class="btn" :class="{disabled: active}"
      >Create order</base-button
    >
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
}
</style>
