<template>
  <div class="input-group">
    <input type="text" class="form-control" placeholder="Поиск пользователей..."
           v-model="searchQuery" @input="handleInput">
    <button class="btn btn-outline-secondary" type="button" @click="handleSearch">
      Поиск
    </button>
  </div>
</template>

<script>
export default {
  name: 'SearchBar',
  data() {
    return {
      searchQuery: ''
    };
  },
  methods: {
    handleInput() {
      // Можно добавить debounce для оптимизации
      if (this.searchQuery.trim() === '') {
        this.$emit('search', '');
      }
    },
    handleSearch() {
      this.$emit('search', this.searchQuery);
    }
  }
};
</script>