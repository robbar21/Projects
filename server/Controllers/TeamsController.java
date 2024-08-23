package server.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.Daos.TeamsDao;
import server.Models.Teams;

import java.util.List;

@RestController
@RequestMapping("api/teams")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class TeamsController {

    private final TeamsDao teamsDao;

    public TeamsController(TeamsDao teamsDao) {
        this.teamsDao = teamsDao;
    }

    @GetMapping("")
    public List<Teams> getAllTeams() {
        return teamsDao.getAllTeams();
    }

    @GetMapping("/{teamId}")
    public Teams getTeamById(@PathVariable int teamId) {
        return teamsDao.getTeamById(teamId);
    }

    @GetMapping("/name/{teamName}")
    public Teams getTeamByName(@PathVariable String teamName) {
        return teamsDao.getTeamByName(teamName);
    }

    @GetMapping("/sport/{sportName}")
    public List<Teams> getTeamBySport(@PathVariable String sportName) {
        return teamsDao.getTeamBySport(sportName);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Teams createTeam(@RequestBody Teams team) {
        return teamsDao.createTeam(team);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{teamId}")
    public Teams updateTeam(@PathVariable int teamId, @RequestBody Teams team) {
        team.setTeamId(teamId);
        return teamsDao.updateTeam(team);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{teamId}")
    public void deleteTeamById(@PathVariable int teamId) {
        teamsDao.deleteTeam(teamId);
    }
}
