package server.Daos;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import server.Models.Teams;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class TeamsDao {
    private final JdbcTemplate jdbcTemplate;

    public TeamsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Teams> getAllTeams() {
        return jdbcTemplate.query(
                "SELECT * FROM teams",
                this::mapRowToTeam
        );
    }

    public Teams getTeamById(int team_id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM teams WHERE team_id = ?",
                    this::mapRowToTeam,
                    team_id
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // No team found with this ID
        }
    }

    public Teams getTeamByName(String name) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM teams WHERE name = ?",
                    this::mapRowToTeam,
                    name
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // No team found with this name
        }
    }

    public List<Teams> getTeamBySport(String sport) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM teams WHERE sport = ?",
                    this::mapRowToTeam,
                    sport
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // No team found with this sport
        }
    }

    public Teams createTeam(Teams team) {
        try {
            Integer team_id = jdbcTemplate.queryForObject(
                    "INSERT INTO teams (name, sport, league) VALUES (?, ?, ?) RETURNING team_id",
                    Integer.class,
                    team.getName(),
                    team.getSport(),
                    team.getLeague()
            );

            if (team_id != null) {
                return getTeamById(team_id);
            } else {
                return null; // Insert failed
            }
        } catch (Exception e) {
            // Log the exception
            return null;
        }
    }

    public Teams updateTeam(Teams team) {
        try {
            int affectedRows = jdbcTemplate.update(
                    "UPDATE teams SET name = ?, sport = ?, league = ? WHERE team_id = ?",
                    team.getName(),
                    team.getSport(),
                    team.getLeague(),
                    team.getTeamId()
            );

            if (affectedRows > 0) {
                return getTeamById(team.getTeamId());
            } else {
                return null; // Update failed or team not found
            }
        } catch (Exception e) {
            // Log the exception
            return null;
        }
    }

    public int deleteTeam(int team_id) {
        try {
            return jdbcTemplate.update(
                    "DELETE FROM teams WHERE team_id = ?",
                    team_id
            );
        } catch (Exception e) {
            // Log the exception
            return 0;
        }
    }

    private Teams mapRowToTeam(ResultSet row, int rowNumber) throws SQLException {
        Teams team = new Teams();
        team.setTeamId(row.getInt("team_id"));
        team.setName(row.getString("name"));
        team.setSport(row.getString("sport"));
        team.setLeague(row.getString("league"));
        return team;
    }
}
