package server.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.Daos.User_CommentsDao;
import server.Models.User_Comments;

import java.util.List;

@RestController
@RequestMapping("api/users_comments")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class Users_CommentsController {
    private final User_CommentsDao userCommentsDao;

    public Users_CommentsController(User_CommentsDao userCommentsDao) {
        this.userCommentsDao = userCommentsDao;
    }

    @GetMapping("")
    public List<User_Comments> getAllComments() {
        return userCommentsDao.getAllUserComments();
    }

    @GetMapping("/post/{postId}")
    public List<User_Comments> getCommentsByPostId(@PathVariable int postId) {
        return userCommentsDao.getUserCommentsByPostId(postId);
    }

    @GetMapping("/user/{username}")
    public List<User_Comments> getCommentsByUsername(@PathVariable String username) {
        return userCommentsDao.getUserCommentsByUsername(username);
    }

    @GetMapping("/comment/{commentId}")
    public User_Comments getCommentsById(@PathVariable int commentId) {
        return userCommentsDao.getUserCommentsById(commentId);
    }
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public User_Comments createComment(@RequestBody User_Comments comments) {
        return userCommentsDao.createUserComment(comments);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{commentId}")
    public User_Comments updateComment(@PathVariable int commentId, @RequestBody User_Comments comments) {
        return userCommentsDao.updateUserComment(comments);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable int commentId) {
        userCommentsDao.deleteUserComments(commentId);
    }
}
