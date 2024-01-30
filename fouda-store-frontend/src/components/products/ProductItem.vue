<script setup>
import BaseCard from "../UI/BaseCard.vue";
import { defineModel , ref} from "vue";
import DescriptionDialog from "@/components/UI/DescriptionDialog";

const product = defineModel();
const displayDescription = ref(false)

</script>

<template>
  <base-card class="product-item-panel" @mouseover="displayDescription = true" @mouseleave="displayDescription = false" >
    <img :src="product.image" alt="product" />
    <p>{{ product.label }}</p>
    <div class="price">
      <span>{{ $filters.euroCurrency(product.prix) }}</span>
    </div>
    <div class="checkbox">
      <input type="checkbox" v-model="product.isSelectedForOrder" />
    </div>
  </base-card>

  <description-dialog v-if="displayDescription" :content="product.description"></description-dialog>
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
