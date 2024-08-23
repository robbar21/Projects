<script>
import loginService from '../services/login-service';
export default {
    data() {
        return {
            username: '',
            password: '',
            rememberMe: false,
            errorMessage: null
        }
    },
    methods: {
        login() {
            loginService.login(this.username, this.password, this.rememberMe).then(loginResult => {
                if (!loginResult) {
                    this.errorMessage = 'Invalid username or password';
                } else {
                    this.$router.push('/');
                }
            });
        }
    }
}
</script>

<template>
    <form @submit.prevent="login">
        <h1>Login</h1>
        <p class="error" v-if="errorMessage">{{ errorMessage }}</p>

        <fieldset>
            <label>
                <span>Username </span>
                <input type="text" v-model="username" required>
            </label>
            <label>
                <span>Password </span>
                <input type="password" v-model="password" required>
            </label>
            <label>
                <input type="checkbox" v-model="rememberMe">
                <span>Remember me</span>
            </label>
            <button type="submit">Login</button>
        </fieldset>
        <RouterLink to="/register" class="register-button">Register a new account</RouterLink>
    </form>
</template>

<style scoped>
    .register-button{
        color:white;
    }
    .register-button:hover{
        background-color: lightskyblue;
    }
    form {
        width: 500px;
        margin: 50px auto 0 auto;
        padding: 50px;
        border: 1px solid gold;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 1rem;
        box-shadow: var(--shadow-3);
    }

    fieldset {
        border: none;
        display: grid;
        gap: 0.5rem;
        grid-template-columns: auto auto;
    }

    label {
        width: 80%;
        grid-column: span 2;
        display: grid;
        grid-template-columns: subgrid;
        gap: 1rem;
    }

    label:has(input[type="checkbox"]) {
        display: flex;
        width: 100%;
        justify-content: center;
    }

    button {
        grid-column: 2;
        width: 100%;
    }
    
    button:hover{
        cursor: pointer;
    }
</style>