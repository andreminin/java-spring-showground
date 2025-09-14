<template>
  <div class="mt-4">
    <table class="table table-striped">
      <thead>
        <tr>
          <th>ID</th>
          <th>Имя</th>
          <th>Фамилия</th>
          <th>Email</th>
          <th>Телефон</th>
          <th>Действия</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.id }}</td>
          <td>{{ user.firstName }}</td>
          <td>{{ user.lastName }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.phone || '-' }}</td>
          <td>
            <button class="btn btn-sm btn-outline-primary me-2" 
                    @click="$emit('edit-user', user)">
              Редактировать
            </button>
            <button class="btn btn-sm btn-outline-danger" 
                    @click="$emit('delete-user', user)">
              Удалить
            </button>
          </td>
        </tr>
        <tr v-if="users.length === 0">
          <td colspan="6" class="text-center">Пользователи не найдены</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'UserList',
  props: {
    users: {
      type: Array,
      required: true
    }
  }
};
</script>