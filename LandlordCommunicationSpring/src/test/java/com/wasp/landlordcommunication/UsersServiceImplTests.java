package com.wasp.landlordcommunication;

import com.wasp.landlordcommunication.models.user.User;
import com.wasp.landlordcommunication.repositories.base.UsersRepository;
import com.wasp.landlordcommunication.services.UsersServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UsersServiceImplTests {
    @Mock
    UsersRepository mockRepository;

    @InjectMocks
    UsersServiceImpl service;

    List<User> defaultTestInput = Arrays.asList(
            new User(
                    "User1",
                    "User1",
                    "FirstNameUser1",
                    "LastNameUser1",
                    "tenant",
                    "Picture1"),
            new User(
                    "User2",
                    "User2",
                    "FirstNameUser2",
                    "LastNameUser2",
                    "tenant",
                    "Picture2"),
            new User(
                    "User3",
                    "User3",
                    "FirstNameUser3",
                    "LastNameUser3",
                    "tenant",
                    "Picture3"
            ));


    @Test
    public void createUser_Should_ReturnNewUser(){
        // Arrange
        User newUser = new User();
        Mockito.when(mockRepository.createUser(newUser))
                .thenReturn(newUser);

        // Act
        User user = service.createUser(newUser);

        // Assert
        Assert.assertEquals(user, newUser);
    }

    @Test
    public void getUserByUsername_Should_ReturnMatchingUser_WhenMatchExist(){
        // Arrange
        Mockito.when(mockRepository.getUserByUserName(defaultTestInput.get(0).getUserName()))
                .thenReturn(defaultTestInput.get(0));

        // Act
        User result = service.getUserByUserName(defaultTestInput.get(0).getUserName());

        // Assert
        Assert.assertEquals(result, defaultTestInput.get(0));
    }

    @Test
    public void getUserById_Should_ReturnMatchingUser_WhenMatchExist(){
        // Arrange
        Mockito.when(mockRepository.getUserById(1))
                .thenReturn(defaultTestInput.get(0));

        // Act
        User result = service.getUserById(1);

        // Assert
        Assert.assertEquals(result,defaultTestInput.get(0));
    }

    @Test
    public void isUsernameAvailable_Should_ReturnTrue_WhenUsernameIsAvailable(){
        // Arrange
        Mockito.when(mockRepository.isUserNameAvailable("AvailableUserName"))
                .thenReturn(true);

        // Act
        boolean isUsernameAvailable= service.isUserNameAvailable("AvailableUserName");

        // Assert
        Assert.assertTrue(isUsernameAvailable);
    }

    @Test
    public void updateUser_Should_ReturnUpdatedUser(){
        // Arrange
        User userToUpdate = new User();
        Mockito.when(mockRepository.updateUser(userToUpdate,1))
                .thenReturn(defaultTestInput.get(0));

        // Act
        User updatedUser = service.updateUser(userToUpdate,1);

        // Assert
        Assert.assertEquals(updatedUser,defaultTestInput.get(0));
    }

    @Test
    public void getUsersByType_Should_ReturnMatchingUsers_WhenMatchExist(){
        // Arrange
        Mockito.when(mockRepository.getUsersByType("tenant"))
                .thenReturn(defaultTestInput);

        // Act
        List<User> result = service.getUsersByType("tenant");

        // Assert
        Assert.assertEquals(result, defaultTestInput);
    }

}
