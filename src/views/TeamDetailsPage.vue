<script>
//Import services needed from '@/services/service needed'
import teamService from '@/services/team-service';

  export default {
    data(){
      return {
        team: {}
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
        response => { this.team = response.data})
      },
      goToPostPage(){
        this.$router.push({name: 'PostPage', params: { teamId: this.team.teamId}});
      }
    }
  }
</script>

<template>
  <h1>{{ team.name }} Details</h1>

  <div class="Team-Info">
    <h2>League: {{ team.league }}</h2>
    <h2>Sport: {{  team.sport }}</h2>
  </div>

  <button @click="goToPostPage">Create a Post</button>
  
</template>

<style scoped>
h1{
  display: flex;
  justify-content: center;
  background-color: gold;
  border-radius: 10px;
  color:black;
}
h2{
  display: flex;
  justify-content: center;
  padding: 15px;
  color: black;
}
button{
  display: block;
  margin: 15px auto;
  padding: 10px, 20px;
  font-size: 24px;
  color: gold;
  border-radius: 5px;
}
button:hover{
  cursor: pointer;
  background-color: lightblue;
}
.Team-Info{
  display: block;
  background-color: gold;
  border: 5px solid black;
  border-radius: 10px;
}
@media (max-width: 1024px){
       
}
</style>