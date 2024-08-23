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
public class User_Profiles {

    private String username;
    private String full_name;
    private String profile_details;
    private String profile_picture_url;
    private String favorite_team;
    private Date created_at;

}
