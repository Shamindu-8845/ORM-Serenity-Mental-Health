package com.serenity.bo.custom;

/*import org.example.bo.SuperBO;
import org.example.dto.UsersDTO;*/
import com.serenity.bo.SuperBO;
import com.serenity.dto.UsersDTO;


import java.io.IOException;

public interface ChangeCredentialsBO extends SuperBO {
    boolean saveCredentials(UsersDTO usersDTO) throws IOException;
}
