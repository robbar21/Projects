<script>
//Import services needed from '@/services/service needed'
import teamService from '@/services/team-service';
import postService from '@/services/post-service';

  export default {
    data(){
      return {
        team: {},
        post: {
          title: '',
          userPosts: '',
          teamId: this.$route.params.teamId
        }
      };
    },
    created(){
      //call team
      this.getTeam();
    },
    methods: {
      getTeam(){
        const teamId = this.$route.params.teamId;
        teamService.getTeamById(teamId).then(
        response => { 
          this.team = response.data})
      },
      createPost(){
        const teamId = this.$route.params.teamId;
        const newPost = {
          title: this.post.title,
          userPosts: this.post.userPosts,
          teamId: teamId
        };
        postService.createPost(newPost).then(() => {
          this.post.title = '';
          this.post.userPosts = '';
          this.$router.push({name: 'BlogsPage' })
          alert('Post submitted successfully!');
        }).catch(error => {
          console.error("Error creating post: ", error);
        })
      }
    }
  }
</script>

<template>
    <div>
      <header>
        <h1>Create a Post About The Pittsburgh {{  team.name }}</h1>
      </header>
      <body>
        <form @submit.prevent="createPost" class = "post">
        <div class="post-title">
          <label for="title">Title:</label>
          <input v-model="post.title" id="title" required />
        </div>
        <div class = "post-content">
          <label for="content">Content:</label>
          <textarea v-model="post.userPosts" id="content" required></textarea>
        </div>
        <button type="submit">Submit Post</button>
      </form>
      </body>
    </div>
</template>

<style scoped>
h1{
  display: flex;
  justify-content: center;
  color: gold;
}

.post{
  color: gold;
  padding: 10px;
}

.post-title{
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  margin: 5px;
}

#title{
  margin: 5px;
  padding: 3px;
}

.post-content{
  display: flex;
  justify-content: center;
  align-items: center;
}

#content{
  margin: 5px;
  width: 600px;
  height: 175px;
 
}

button{
  display: block;
  margin: 15px auto;
  padding: 10px, 20px;
  font-size: 24px;
  background-color: black;
  color: gold;
  border-radius: 5px;
}

button:hover{
  cursor: pointer;
  background-color: lightblue;
}

@media (max-width: 1024px){
  h1{
    font-size: larger;
  }
}
</style>