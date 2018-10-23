package com.wasp.landlordcommunication.diconfig;

import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.repositories.base.Repository;
import com.wasp.landlordcommunication.services.HttpUsersService;
import com.wasp.landlordcommunication.services.base.UsersService;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {
    @Provides
    public UsersService usersService(Repository<User> usersRepository) {
        return new HttpUsersService(usersRepository);
    }
}
