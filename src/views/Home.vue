<script>
//Import services needed from '@/services/service needed'
import teamService from '@/services/team-service';

  export default {
    data(){
      return{
        teams: []
      }
    },
    created(){
      //call teams to show all teams and create them as cards where I can place an image of their logo
      this.loadTeams();
    },
    methods: {

      loadTeams(){
        teamService.getAllTeams().then(response => {
          this.teams = response.data;
        }).catch(error => {
          this.error = error.response;
        })
      }
    }
  };
</script>

<template>
  <h1>ALL PITTSBURGH SPORTS TALK</h1>
    <div class = "all-team-cards">
        <div v-for="team in teams" :key="team.teamId" class="team-card">
          <h2>{{ team.name }}</h2>
            <div class = "link-to-TeamPage">
              <router-link :to="{name: 'TeamDetails', params: {teamId: team.teamId}}" class="route">
              {{ team.name }} Home Page
              </router-link>
            </div>
        </div>
    </div>
</template>

<style scoped>
.route{
  color: gray;
}

h1{
  display: flex;
  justify-content: center;
  color: gold;
}

.link-to-TeamPage{
  font-size: 16px;
}
.link-to-TeamPage:hover{
  background-color: lightskyblue;
}
.all-team-cards{
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
  justify-content: center;
  align-items: center;
  color: black;
}

.team-card{
  background-color: gold;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 2px 5px grey;
  width: 200px;
  text-align: center;
}

.team-info{
  padding: 10px;
}

.team-info h2{
  margin: 0;
}

.team-info p {
  margin: 5px 0 0;
  color: black;
}

@media (max-width: 1024px){
       h1{
          font-size: large;
       }
}
</style>