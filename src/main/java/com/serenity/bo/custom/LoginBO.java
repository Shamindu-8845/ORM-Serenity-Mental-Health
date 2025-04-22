package com.serenity.bo.custom;

/*import org.example.bo.SuperBO;*/
import com.serenity.bo.SuperBO;
import com.serenity.dto.UsersDTO;
/*import org.example.dto.UsersDTO;*/


import java.io.IOException;
import java.util.List;

public interface LoginBO extends SuperBO {
    List<UsersDTO> checkUser(UsersDTO usersDTO) throws IOException;
}
