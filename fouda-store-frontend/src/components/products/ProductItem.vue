<script setup>
import BaseCard from "../UI/BaseCard.vue";
import { defineModel } from "vue";

const product = defineModel();

</script>

<template>
  <base-card class="product-item-panel">
    <img :src="product.image" alt="product" />
    <p style="margin-botton: 10px">{{ product.description }}</p>
    <div class="price">
      <span>{{ $filters.euroCurrency(product.prix) }}</span>
    </div>
    <div class="checkbox">
      <input type="checkbox" v-model="product.isSelectedForOrder" />
    </div>
  </base-card>
</template>

<style scoped>
.product-item-panel {
  height: 250px;
  width: auto;
  margin: 10px 10px;
}

.product-item-panel img {
  height: 170px;
  width: 180px;
}

.price {
  padding: 3px;
  margin-top: 10px;
}

.price span {
  background-color: var(--vert);
  margin: 0px;
  padding: 5px;
  border-radius: 3px;
  color: var(--gris-clair);
}

.checkbox {
  float: right;
  transform: scale(1.5);
}
</style>
