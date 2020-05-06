package repository;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public List<User> getAllUsers() {

        String sql = "select * from users";
        return jdbcOperations.query(sql, (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String website = rs.getString("website");
            String affiliation = rs.getString("affiliation");
            String password = rs.getString("password");

            User user = new User( name, email,username,website,affiliation,password);
            user.setId(id);
            return user;
        });
    }
}

