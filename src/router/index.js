import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import About from '../views/About.vue';
import BlogsPage from '../views/BlogsPage.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import TeamDetailsPage from '../views/TeamDetailsPage.vue';
import PostPage from '../views/PostPage.vue';
import { store } from '../main';

export function createAppRouter() {
    const router = createRouter({
        history: createWebHistory(),
        routes: [
            {
                path: '/',
                name: 'Home',
                component: Home,
                meta: {
                    requireAuth: true,
                }
            },
            {
                path: '/about',
                name: 'About',
                component: About,
                meta: {
                    requireAuth: true,
                }
            },
            {
                path: '/login',
                name: 'Login',
                component: Login,
                meta: {
                    requireAuth: false,
                }
            },
            {
                path: '/register',
                name: 'Register',
                component: Register,
                meta: {
                    requireAuth: false,
                }
            },
            {
                path: '/teams/:teamId',
                name: 'TeamDetails',
                component: TeamDetailsPage,
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/teams/:teamId/create-post',
                name: 'PostPage',
                component: PostPage,
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/posts',
                name: 'BlogsPage',
                component: BlogsPage,
                meta: {
                    requireAuth: false
                }
            }
        ]
    });

    router.beforeEach((to, from, next) => {
        if (to.meta.requireAuth  && store.state.token === null) {
            next('/login');
        } else {
            next();
        }
    });
    
    return router;
}