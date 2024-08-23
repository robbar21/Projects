package server.Daos;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import server.Models.Posts;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PostsDao {

    private final JdbcTemplate jdbcTemplate;

    public PostsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Posts> getAllPosts() {
        return jdbcTemplate.query(
                "SELECT * FROM posts",
                this::mapRowToPost
        );
    }

    public List<Posts> getPostByUsername(String username) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM posts WHERE username = ?",
                    this::mapRowToPost,
                    username
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // No post found with this username
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
            return null;
        }
    }

    public Posts getPostById(int postId) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM posts WHERE post_id = ?",
                    this::mapRowToPost,
                    postId
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // No post found with this ID
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
            return null;
        }
    }

    public Posts createPost(Posts post) {
        try {
            Integer postId = jdbcTemplate.queryForObject(
                    "INSERT INTO posts (username, title, user_posts, created_at) VALUES (?, ?, ?, ?) RETURNING post_id",
                    Integer.class,
                    post.getUsername(),
                    post.getTitle(),
                    post.getUserPosts(),
                    post.getCreatedAt()
            );

            if (postId != null) {
                return getPostById(postId);
            } else {
                return null; // Insert failed
            }
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
            return null;
        }
    }

    public Posts updatePost(Posts post) {
        try {
            int affectedRows = jdbcTemplate.update(
                    "UPDATE posts SET username = ?, title = ?, user_posts = ? WHERE post_id = ?",
                    post.getUsername(),
                    post.getTitle(),
                    post.getUserPosts(),
                    post.getPostId()
            );

            if (affectedRows > 0) {
                return getPostById(post.getPostId());
            } else {
                return null; // Update failed or post not found
            }
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
            return null;
        }
    }

    public void deletePost(int postId) {
        try {
            jdbcTemplate.update(
                    "DELETE FROM posts WHERE post_id = ?",
                    postId
            );
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
        }
    }

    private Posts mapRowToPost(ResultSet row, int rowNumber) throws SQLException {
        Posts post = new Posts();
        post.setPostId(row.getInt("post_id"));
        post.setUsername(row.getString("username"));
        post.setTitle(row.getString("title"));
        post.setUserPosts(row.getString("user_posts"));
        post.setCreatedAt(row.getDate("created_at"));

        return post;
    }
}
