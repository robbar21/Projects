import { store } from '../main';
import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:8080/api'
});

function getAuthHeaders(){
    const token = store.state.token;
    return {
        headers: { Authorization: `Bearer ${token}`}
    };
}

export default {

    getAllPosts(){
        return http.get('/posts', getAuthHeaders());
    },

    getPostsByUsername(username){
        return http.get(`/posts/users/${username}`, getAuthHeaders());
    },

    getPostById(postId){
        return http.get(`/posts/${postId}`, getAuthHeaders());
    },

    createPost(post){
        return http.post('/posts', post, getAuthHeaders());
    },

    updatePost(postId, post){
        return http.put(`/posts/${postId}`, post, getAuthHeaders());
    },

    deletePostById(postId){
        return http.delete(`/posts/${postId}`, getAuthHeaders());
    }

}