package server.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.Daos.PostsDao;
import server.Models.Posts;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/posts")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class PostsController {

    private final PostsDao postDao;

    public PostsController(PostsDao postDao) {
        this.postDao = postDao;
    }

    @GetMapping("")
    public List<Posts> getAllPosts() {
        return postDao.getAllPosts();
    }

    @GetMapping("/users/{username}")
    public List<Posts> getPostByUser(@PathVariable String username) {
        return postDao.getPostByUsername(username);
    }

    @GetMapping("/{postId}")
    public Posts getPostById(@PathVariable int postId) {
        return postDao.getPostById(postId);
    }
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Posts createPost(@RequestBody Posts post, Principal principal) {
        String username = principal.getName();
        post.setUsername(username);
        Date createdAt = new Date();
        post.setCreatedAt(createdAt);
        return postDao.createPost(post);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{postId}")
    public Posts updatePost(@PathVariable int postId, @RequestBody Posts post, Principal principal) {
        String username = principal.getName();
        post.setUsername(username);
        post.setPostId(postId);
        return postDao.updatePost(post);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{postId}")
    public void deletePostById(@PathVariable int postId) {
        postDao.deletePost(postId);
    }
}
