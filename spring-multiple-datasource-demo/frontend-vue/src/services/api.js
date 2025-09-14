import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

export default {
    // Получить всех пользователей
    getUsers() {
        return api.get('/users');
    },

    // Получить пользователя по ID
    getUser(id) {
        return api.get(`/users/${id}`);
    },

    // Создать пользователя
    createUser(user) {
        return api.post('/users', user);
    },

    // Обновить пользователя
    updateUser(id, user) {
        return api.put(`/users/${id}`, user);
    },

    // Удалить пользователя
    deleteUser(id) {
        return api.delete(`/users/${id}`);
    },

    // Поиск пользователей
    searchUsers(query) {
        return api.get(`/users/search?query=${encodeURIComponent(query)}`);
    }
};