<template>
  <div class="modal" tabindex="-1" style="display: block; background: rgba(0,0,0,0.5)">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">
            {{ isEditing ? 'Редактирование пользователя' : 'Добавление пользователя' }}
          </h5>
          <button type="button" class="btn-close" @click="$emit('cancel')"></button>
        </div>
        <div class="modal-body">
          <form @submit.prevent="handleSubmit">
            <div class="mb-3">
              <label for="firstName" class="form-label">Имя</label>
              <input type="text" class="form-control" id="firstName"
                     v-model="formData.firstName" required>
            </div>

            <div class="mb-3">
              <label for="lastName" class="form-label">Фамилия</label>
              <input type="text" class="form-control" id="lastName"
                     v-model="formData.lastName" required>
            </div>

            <div class="mb-3">
              <label for="email" class="form-label">Email</label>
              <input type="email" class="form-control" id="email"
                     v-model="formData.email" required>
            </div>

            <div class="mb-3">
              <label for="phone" class="form-label">Телефон</label>
              <input type="tel" class="form-control" id="phone"
                     v-model="formData.phone">
            </div>

            <div class="text-end">
              <button type="button" class="btn btn-secondary me-2"
                      @click="$emit('cancel')">
                Отмена
              </button>
              <button type="submit" class="btn btn-primary">
                Сохранить
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserForm',
  props: {
    user: {
      type: Object,
      required: true
    },
    isEditing: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      formData: { ...this.user }
    };
  },
  methods: {
    handleSubmit() {
      this.$emit('save-user', this.formData);
    }
  }
};
</script>