<script setup>
import BaseCard from "../UI/BaseCard.vue";
import {defineModel} from "vue";
import DescriptionDialog from "@/components/UI/DescriptionDialog";

const product = defineModel();


</script>

<template>
  <base-card class="product-item-panel">
    <div class="tooltip">
      <img :src="product.image" alt="product"/>
      <p>{{ product.label }}</p>
      <div class="price">
        <span>{{ $filters.euroCurrency(product.prix) }}</span>
      </div>
      <div class="checkbox">
        <input type="checkbox" v-model="product.isSelectedForOrder"/>
      </div>
      <span class="tooltiptext">{{product.description}}</span>
    </div>

  </base-card>

  <description-dialog v-if="displayDescription" :content="product.description"></description-dialog>
</template>

<style scoped>
.product-item-panel {
  height: 250px;
  width: 200px;
  margin: 10px 10px 10px 0px;
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

.tooltip {
  position: relative;
  display: inline-block;
  border:none;
}

.tooltip .tooltiptext {
  visibility: hidden;
  width: 120px;
  background-color: var(--gris-fonce);
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 5px 5px;

  /* Position the tooltip */
  position: absolute;
  z-index: 1;
}

.tooltip:hover .tooltiptext {
  visibility: visible;
}
</style>
