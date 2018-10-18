package com.wasp.landlordcommunication.controllers;

import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.services.base.UsersService;
import com.wasp.landlordcommunication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.USER_ROOT_MAPPING)
public class UsersApiController {

    private final UsersService usersService;

    @Autowired
    public UsersApiController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(method = RequestMethod.POST)
    User createUser(@RequestBody User userToCreate) {

        return usersService.createUser(userToCreate);

    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    User getUserByUserName(@PathVariable String userName) {

        return usersService.getUserByUserName(userName);
    }

}
