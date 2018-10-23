package com.wasp.landlordcommunication.utils.mappers;

import com.wasp.landlordcommunication.models.user.User;
import com.wasp.landlordcommunication.models.user.UserDTO;
import com.wasp.landlordcommunication.utils.mappers.base.UserMapper;

import java.util.Objects;

public class UserMapperImpl implements UserMapper {
    @Override
    public UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        if (!Objects.equals(userDTO, null)) {

            userDTO = new UserDTO(
                    user.getUserId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUserType(),
                    user.getUserPicture());
        }
        return userDTO;
    }
}
