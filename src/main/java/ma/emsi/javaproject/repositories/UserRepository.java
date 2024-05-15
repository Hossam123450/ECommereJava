package ma.emsi.javaproject.repositories;

import ma.emsi.javaproject.entities.User;
import org.springframework.stereotype.Repository;
@Repository
public class UserRepository
{
    public User findUserByEmail(String email)
    {
        return new User(email,"123456","FirstName","LastName");
    }
}
