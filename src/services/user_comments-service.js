import { store } from '../main';
import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:8080/api'
});

export default {

    getAllUserComments(){
        //maybe user_comments
        return http.get('/users_comments');
    },

    getCommentsByPostId(postId){
        return http.get(`/users_comments/posts/${postId}`);
    },

    getCommentsByUsername(username){
        return http.get(`/users_comments/user/${username}`);
    },

    getCommentById(commentId){
        return http.get(`/users_comments/comment/${commentId}`)
    },

    createComment(comment){
        //maybe user_comments
        return http.post('/users_comments', comment);
    },

    updateComment(commentId, comment){
        return http.put(`/users_comments/${commentId}`, comment);
    },

    deleteComment(commentId){
        return http.delete(`/users_comments/${commentId}`);
    }

}