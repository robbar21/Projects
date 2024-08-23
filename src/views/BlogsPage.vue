<script>
//Import services needed from '@/services/service needed'
import usercommentsService from '@/services/user_comments-service';
import postService from '@/services/post-service';
import teamService from '@/services/team-service';

  export default {
    data(){
        return{
            teams: [],
            posts: []
        }
    },
    created(){
      this.loadPosts();
      this.loadTeams();
    },
    methods: {
        loadPosts() {
            postService.getAllPosts().then(response => {
                this.posts = response.data;
            }).catch(error => {
                console.error("error loading posts: ", error.response);
            })
        },
        loadTeams(){
            teamService.getAllTeams().then(response => {
            this.teams = response.data;
            }).catch(error => {
            this.error = error.response;
            })
      },
      deletePostById(postId){
        postService.deletePostById(postId).then(() => {
            this.posts = this.posts.filter(post => post.id !== postId);
            this.loadPosts();
        }).catch(error => {
            console.error("Error deleting post: ", error.response)
        })
      }
    }
  }
</script>

<template>
    <header>
        <h1>Blogs</h1>
    </header>
    <div class="blogs">
        <div v-if="posts.length">
            <div v-for="post in posts" :key="post.id" class="post-card">
                <h2>{{ post.title }}</h2>
                <p> {{ post.userPosts }}</p>
                <p><strong>Posted by: </strong> {{ post.username }}</p>
                <p><strong>Date: </strong> {{ post.createdAt }}</p>
                <button @click="deletePostById(post.postId)" class="delete-button">Delete Post</button>
            </div>
        </div>
        <div v-else>
            <p>Be The First To Post!</p>
        </div>
    </div>
</template>

<style scoped>
    h1{
        display: flex;
        justify-content: center;
    }
    h2{
        display: flex;
        justify-content: center;
        text-decoration: underline;
    }
    .blogs{
        display: flex;
        justify-content: center;
        flex-wrap: wrap;
    }
    .post-card{
        padding: 15px;
        margin-bottom: 15px;
        border-radius: 8px;
        background-color: gold;
        color: black;
        width: 100%;
        max-width: 600px;
    }
    .delete-button{
        display: flex;
        justify-content: center;
        background-color: red;
        color: white;
        padding: 5px 20px;
        border-radius: 5px;
        margin: auto;
    }
    .delete-button:hover{
        cursor: pointer;
        background-color: darkred;
    }

    @media (max-width: 1024px){
        .post-card{
            padding: 10px;
            margin-bottom: 10px;
            width: 90%;
        }
        .delete-button{
            padding: 5px 15px;
            width: 100%;
        }
    }
</style>