package com.wasp.landlordcommunication.utils.mappers.base;

import com.wasp.landlordcommunication.models.user.User;
import com.wasp.landlordcommunication.models.user.UserDTO;

public interface UserMapper {

    UserDTO mapUserToDTO(User user);
}
