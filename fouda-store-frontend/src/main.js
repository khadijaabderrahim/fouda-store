import { createApp } from "vue";
import App from "./App.vue";
import router from "./router.js";
import DeleteIcon from "vue-material-design-icons/Delete.vue";
import EditIcon from "vue-material-design-icons/Pencil.vue";
import ViewIcon from "vue-material-design-icons/Eye.vue";
import AddIcon from "vue-material-design-icons/Plus.vue";
import QuestionIcon from "vue-material-design-icons/Help.vue";
import BabyBottleIcon from "vue-material-design-icons/BabyBottleOutline.vue";
import store from "./store/index.js";
import BaseCard from "./components/UI/BaseCard";
import PulseLoader from 'vue-spinner/src/PulseLoader.vue'

import 'vue-search-select/dist/VueSearchSelect.css'

import axios from "axios";



const app = createApp(App);
app.use(router);
app.use(store);
app.component("delete-icon", DeleteIcon);
app.component("edit-icon", EditIcon);
app.component("view-icon", ViewIcon);
app.component("add-icon", AddIcon);
app.component("question-icon", QuestionIcon);
app.component("baby-icon", BabyBottleIcon);
app.component("base-card", BaseCard);
app.component("pulse-loader", PulseLoader);

app.config.globalProperties.$filters = {
  formatDate(value) {
    return new Date(value).toLocaleString();
  },

  euroCurrency(value) {
    let arrondi = Math.round(value * 100) / 100;
    return arrondi + "€";
  },
};

// Request interceptor
axios.interceptors.request.use((config) => {
  let headers = {
    "Content-Type": "application/json",
    Authorization: "Basic dXNlcjE6dXNlcjFQYXNz"
  };
  config.headers = headers;
  return config;
});

axios.interceptors.response.use((response) => {
  
  return response
}, (error) => {
  alert('An error occurred in backend server. \nPlease contact your administrator')
  return Promise.reject(error)
})
app.mount("#app");
