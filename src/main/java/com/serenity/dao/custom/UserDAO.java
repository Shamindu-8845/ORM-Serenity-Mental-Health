package com.serenity.dao.custom;

/*import org.example.dao.CrudDAO;
import org.example.entity.Users;*/

import com.serenity.dao.CrudDAO;
import com.serenity.entity.Users;

import java.io.IOException;
import java.util.List;

public interface UserDAO extends CrudDAO<Users> {
    List<Users> checkUser(Users users) throws IOException;

}
