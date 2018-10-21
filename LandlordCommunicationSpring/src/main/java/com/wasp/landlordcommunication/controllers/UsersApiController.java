package com.wasp.landlordcommunication.controllers;

import com.wasp.landlordcommunication.models.user.User;
import com.wasp.landlordcommunication.models.user.UserDTO;
import com.wasp.landlordcommunication.services.base.UsersService;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.mappers.base.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Constants.USER_ROOT_MAPPING)
public class UsersApiController {

    private final UsersService usersService;
    private final UserMapper userMapper;

    @Autowired
    public UsersApiController(UsersService usersService, UserMapper userMapper) {
        this.usersService = usersService;
        this.userMapper = userMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody @Valid User userToCreate) {

        return usersService.createUser(userToCreate);

    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public User getUserByUserName(@PathVariable String userName) {

        return usersService.getUserByUserName(userName);
    }

    @RequestMapping(value = "/id/{userId}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int userId) {
        return usersService.getUserById(userId);

    }

    @RequestMapping(method = RequestMethod.GET)
    public boolean isUserNameAvailable(@RequestParam String name) {

        return usersService.isUserNameAvailable(name);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody @Valid User userToUpdate, @PathVariable int userId) {

        return usersService.updateUser(userToUpdate, userId);

    }

    @RequestMapping(value = "/type/{userType}", method = RequestMethod.GET)
    public List<UserDTO> getUsersByType(@PathVariable String userType) {

        return usersService
                .getUsersByType(userType)
                .stream()
                .map(userMapper::mapUserToDTO)
                .collect(Collectors.toList());
    }
}
