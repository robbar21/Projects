package server.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
    private int postId;
    private String username;
    private String title;
    private String userPosts;
    private Date createdAt;
}
