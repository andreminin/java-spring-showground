<template>
  <div id="app" class="container mt-4">
    <h1 class="mb-4">Управление пользователями</h1>

    <div class="row">
      <div class="col-md-8">
        <SearchBar @search="handleSearch" />
      </div>
      <div class="col-md-4 text-end">
        <button class="btn btn-primary" @click="showAddForm">
          Добавить пользователя
        </button>
      </div>
    </div>

    <UserList
      :users="users"
      @edit-user="showEditForm"
      @delete-user="deleteUser"
    />

    <UserForm
      v-if="showForm"
      :user="selectedUser"
      :isEditing="isEditing"
      @save-user="saveUser"
      @cancel="hideForm"
    />
  </div>
</template>

<script>
import UserList from './components/UserList.vue';
import UserForm from './components/UserForm.vue';
import SearchBar from './components/SearchBar.vue';
import api from './services/api';

export default {
  name: 'App',
  components: {
    UserList,
    UserForm,
    SearchBar
  },
  data() {
    return {
      users: [],
      showForm: false,
      selectedUser: null,
      isEditing: false
    };
  },
  mounted() {
    this.loadUsers();
  },
  methods: {
    async loadUsers() {
      try {
        const response = await api.getUsers();
        this.users = response.data;
      } catch (error) {
        console.error('Ошибка загрузки пользователей:', error);
        alert('Не удалось загрузить пользователей');
      }
    },

    async handleSearch(query) {
      try {
        const response = await api.searchUsers(query);
        this.users = response.data;
      } catch (error) {
        console.error('Ошибка поиска:', error);
        alert('Не удалось выполнить поиск');
      }
    },

    showAddForm() {
      this.selectedUser = {
        firstName: '',
        lastName: '',
        email: '',
        phone: ''
      };
      this.isEditing = false;
      this.showForm = true;
    },

    showEditForm(user) {
      this.selectedUser = { ...user };
      this.isEditing = true;
      this.showForm = true;
    },

    hideForm() {
      this.showForm = false;
      this.selectedUser = null;
    },

    async saveUser(user) {
      try {
        if (this.isEditing) {
          await api.updateUser(user.id, user);
        } else {
          await api.createUser(user);
        }

        this.hideForm();
        this.loadUsers(); // Перезагружаем список
        alert('Пользователь успешно сохранен');
      } catch (error) {
        console.error('Ошибка сохранения:', error);
        alert(error.response?.data || 'Не удалось сохранить пользователя');
      }
    },

    async deleteUser(user) {
      if (confirm(`Вы уверены, что хотите удалить пользователя ${user.firstName} ${user.lastName}?`)) {
        try {
          await api.deleteUser(user.id);
          this.loadUsers(); // Перезагружаем список
          alert('Пользователь успешно удален');
        } catch (error) {
          console.error('Ошибка удаления:', error);
          alert('Не удалось удалить пользователя');
        }
      }
    }
  }
};
</script>

<style>
@import 'bootstrap/dist/css/bootstrap.css';
</style>