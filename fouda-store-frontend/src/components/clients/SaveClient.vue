<template>
  <dialog class="dialog-style" open>
    <form>
      <h2>{{ title }}</h2>
      <ul class="error" v-if="errors">
        <li v-for="error in errors" :key="error">{{ error }}</li>
      </ul>
      <label for="firstname">firstname</label>
      <input type="text" v-model="clientToSave.firstname" />
      <label for="lastname">lastname</label>
      <input type="lastname" v-model="clientToSave.lastname" />
      <label for="email">email</label>
      <input type="email" v-model="clientToSave.email" />
      <base-button class="btn" @click.prevent="save"
        >save</base-button
      >
      <base-button class="btn" @click.prevent="cancel">cancel</base-button>
    </form>
  </dialog>
</template>

<script setup>
import BaseButton from "../UI/BaseButton.vue";
import { ref, reactive, defineProps, defineEmits } from "vue";
import { useStore } from "vuex";

const props = defineProps(["client", "title"]);
const store = useStore();
const clientToSave = reactive(props.client);

const emit = defineEmits(["close-dialog"]);
let errors = ref();

function cancel() {
  emit("close-dialog");
}

function checkForm() {
  errors.value = [];
  if (!clientToSave.firstname) {
    errors.value.push("firstname is required");
  }
  if (!clientToSave.lastname) {
    errors.value.push("lastname is required");
  }
  if (!clientToSave.email) {
    errors.value.push("email is required");
  } else {
    var re =
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!re.test(clientToSave.email)) {
      errors.value.push("valid email is required");
    }
  }
}

async function save() {
  checkForm();

  if (errors.value.length === 0) {
    console.log(clientToSave);
    await store.dispatch("clients/save", clientToSave);
    emit("close-dialog");
  }
}
</script>

<style scoped>
.dialog-style {
  width: 400px;
  height: auto;
  border-radius: 5px;
  margin: 50px auto;
  border: 1px #dbe4e5;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.2);
  padding: 5px 5px;
  background: #fcffff;
  color: #34374e;
  font-size: 14px;
}




dialog h2 {
  margin-top: 20px;
  text-align: center;
  font-size: 16px;
}

dialog input:focus {
  outline: 1px solid #2222224f;
}




.error li {
  padding: 5px;
  list-style: none;
  color: rgb(160, 41, 41);
}

form label,
input {
  display: block;
  margin: 5px 5px;
}
</style>
