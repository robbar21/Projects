package server.Models;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Min(value = 5, message = "Password must be at least 5 characters")
    private String role;
    @Min(value = 3, message = "Username must be at least 3 characters")
    private String username;
}
