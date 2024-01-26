<script setup>
import { ref, defineEmits } from "vue";
import BaseButton from "../UI/BaseButton.vue";

const productToCreate = ref({});
const emit = defineEmits(["close-create-product"]);

function submitProduct() {
  console.log(productToCreate.value);
}

function cancelCreateProduct() {
  productToCreate.value = {};
  emit("close-create-product");
}
</script>

<template>
  <form>
    <div class="form-group">
      <label for="productName">Label</label>
      <input
        type="text"
        id="productName"
        placeholder="Product label"
        :v-model="productToCreate.label"
      />
    </div>
    <div class="form-group">
      <label for="productDescription">Description</label>
      <input
        type="text"
        id="productDescription"
        placeholder="Product description"
        :v-model="productToCreate.description"
      />
    </div>
    <div class="form-group">
      <label for="productPrice">Price</label>
      <input
        type="number"
        id="productPrice"
        placeholder="price"
        :v-model="productToCreate.prix"
      />
    </div>
    <div class="form-group">
      <label for="productPhoto">Product photo</label>
      <input
        type="text"
        id="productPhoto"
        placeholder="product Photo"
        :v-model="productToCreate.image"
      />
    </div>
    <div class="form-group">
      <base-button @click="submitProduct">Submit product</base-button>
      <base-button @click="cancelCreateProduct">Cancel</base-button>
    </div>
  </form>
</template>
