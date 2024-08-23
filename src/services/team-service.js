import { store } from '../main';
import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:8080/api'
});

export default {

    getAllTeams(){
        return http.get('/teams');
    },

    getTeamById(teamId){
        return http.get(`/teams/${teamId}`);
    },

    getTeamByName(teamName){
        return http.get(`/teams/name/${teamName}`);
    },

    getTeamBySport(sportName){
        return http.get(`/teams/sport/${sportName}`);
    },

    createTeam(team){
        return http.post('/teams', team);
    },

    updateTeam(teamId, team){
        return http.put(`/teams/${teamId}`, team);
    },

    deleteTeam(teamId){
        return http.delete(`/teams/${teamId}`);
    }
}