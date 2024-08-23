package server.Daos;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import server.Models.User_Comments;

import javax.sql.DataSource;
import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class User_CommentsDao {
    private final JdbcTemplate jdbcTemplate;

    public User_CommentsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User_Comments> getAllUserComments() {
        return jdbcTemplate.query(
                "SELECT * FROM user_comments",
                this::mapRowToUserComments
        );
    }

    public User_Comments getUserCommentsById(int comment_id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM user_comments WHERE comment_id = ?",
                    this::mapRowToUserComments,
                    comment_id
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // No comment found with this ID
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
            return null;
        }
    }

    public List<User_Comments> getUserCommentsByPostId(int post_id) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM user_comments WHERE post_id = ?",
                    this::mapRowToUserComments,
                    post_id
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // No comments found for this post ID
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
            return null;
        }
    }

    public List<User_Comments> getUserCommentsByUsername(String username) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM user_comments WHERE username = ?",
                    this::mapRowToUserComments,
                    username
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // No comments found for this username
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
            return null;
        }
    }

    public User_Comments createUserComment(User_Comments userComment) {
        try {
            Integer comment_id = jdbcTemplate.queryForObject(
                    "INSERT INTO user_comments (post_id, username, user_comments) VALUES (?, ?, ?) RETURNING comment_id",
                    Integer.class,
                    userComment.getPostId(),
                    userComment.getUsername(),
                    userComment.getUserComments()
            );

            if (comment_id != null) {
                return getUserCommentsById(comment_id);
            } else {
                return null; // Insert failed
            }
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
            return null;
        }
    }

    public User_Comments updateUserComment(User_Comments userComment) {
        try {
            int affectedRows = jdbcTemplate.update(
                    "UPDATE user_comments SET post_id = ?, username = ?, user_comments = ? WHERE comment_id = ?",
                    userComment.getPostId(),
                    userComment.getUsername(),
                    userComment.getUserComments(),
                    userComment.getCommentId()
            );

            if (affectedRows > 0) {
                return getUserCommentsById(userComment.getCommentId());
            } else {
                return null; // Update failed or comment not found
            }
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
            return null;
        }
    }

    public void deleteUserComments(int commentId) {
        try {
            jdbcTemplate.update(
                    "DELETE FROM user_comments WHERE comment_id = ?",
                    commentId
            );
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
        }
    }

    private User_Comments mapRowToUserComments(ResultSet row, int rowNumber) throws SQLException {
        User_Comments userComments = new User_Comments();
        userComments.setCommentId(row.getInt("comment_id"));
        userComments.setPostId(row.getInt("post_id"));
        userComments.setUsername(row.getString("username"));
        userComments.setUserComments(row.getString("user_comments"));

        return userComments;
    }
}
