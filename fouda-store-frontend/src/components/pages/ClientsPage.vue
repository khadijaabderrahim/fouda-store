<template>
  <div class="conatiner">
    <save-client
      v-if="formVisible"
      :client="clientToSave"
      :title="title"
      @close-dialog="closeSaveDialog"
    ></save-client>

    <base-alert
      v-if="deleteConfirm"
      @confirm-action="deleteClient"
      @cancel-action="cancelDelete"
    >
       Are you sure you
      want to delete the client {{ clientToDeleteName }} ?
    </base-alert>

    <base-table>
      <template v-slot:title>
        <span>Clients</span>
        <base-button class="add-btn" title="add client" @click="add"
          ><add-icon></add-icon
        ></base-button>
      </template>
      <template v-slot:header>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
        <th>Actions</th>
      </template>

      <template v-slot:body>
        <tr v-for="client in clients" :key="client.id">
          <td>{{ client.firstname }}</td>
          <td>{{ client.lastname }}</td>
          <td>{{ client.email }}</td>
          <td>
            <base-button
              @click="viewClient(client.id)"
              title="view client details"
              ><view-icon></view-icon
            ></base-button>
            <base-button @click="edit(client)" title="edit client"
              ><edit-icon></edit-icon
            ></base-button>
            <base-button @click="confirmDelete(client)" title="delete client"
              ><delete-icon></delete-icon
            ></base-button>
          </td>
        </tr>
      </template>
    </base-table>
  </div>
</template>

<script>
import BaseButton from "../UI/BaseButton.vue";
import SaveClient from "../clients/SaveClient.vue";
import BaseTable from "../UI/BaseTable.vue";
import BaseAlert from "../UI/BaseAlert.vue";
export default {
  components: {
    BaseButton,
    SaveClient,
    BaseTable,
    BaseAlert,
  },
  data() {
    return {
      formVisible: false,
      title: "",
      errors: [],
      dialogStyle: "dialog-init",
      disabledSave: false,
      deleteConfirm: false,
      clientToDeleteName: "",
      clientToDeleteId: 0,
      clientToSave: {},
    };
  },

  computed: {
    clients() {
      var clients = this.$store.getters["clients/findClients"];
      var sortedClients = clients.sort((a, b) => {
        let fa = a.id,
          fb = b.id;
        if (fa < fb) {
          return -1;
        }
        if (fa > fb) {
          return 1;
        }
        return 0;
      });

      return sortedClients;
    },
  },
  methods: {
    add() {
      this.title = "Add Client";
      this.formVisible = true;
    },

    closeSaveDialog() {
      this.formVisible = false;
      this.clientToSave = {};
    },

    edit(client) {
      this.title = "edit Client";
      this.clientToSave = client;
      this.formVisible = true;
    },

    confirmDelete(client) {
      console.log(client.id);
      this.clientToDeleteName = client.firstname + " " + client.lastname;
      this.clientToDeleteId = client.id;
      this.deleteConfirm = true;
    },

    cancelDelete() {
      this.clientToDeleteId = 0;
      this.clientToDeleteName = "";
      this.deleteConfirm = false;
    },

    deleteClient() {
      this.$store
        .dispatch("clients/delete", this.clientToDeleteId)
        .then((this.deleteConfirm = false));
    },

    viewClient(id) {
      this.$router.push("/clients/" + id);
    },
  },
  beforeCreate() {
    console.log(this.$store);
    this.$store.dispatch("clients/loadClients");
  },
};
</script>

<style scoped>

</style>
