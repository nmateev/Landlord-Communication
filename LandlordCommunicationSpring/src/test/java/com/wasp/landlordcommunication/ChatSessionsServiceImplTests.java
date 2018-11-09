package com.wasp.landlordcommunication;

import com.wasp.landlordcommunication.models.chatsession.ChatSession;
import com.wasp.landlordcommunication.models.chatsession.ChatSessionDTO;
import com.wasp.landlordcommunication.repositories.base.ChatSessionsRepository;
import com.wasp.landlordcommunication.services.ChatSessionsServiceImpl;
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
public class ChatSessionsServiceImplTests {
    @Mock
    ChatSessionsRepository mockRepository;
    @InjectMocks
    ChatSessionsServiceImpl service;

    List<ChatSession> defaultTestInput = Arrays.asList(
            new ChatSession(
                    1,
                    1),
            new ChatSession(
                    2,
                    2),
            new ChatSession(
                    3,
                    3
            ));

    @Test
    public void createChatSession_Should_ReturnNewChatSession(){
        // Arrange
        ChatSession newChatSession=new ChatSession();
        Mockito.when(mockRepository.createChatSession(newChatSession))
                .thenReturn(newChatSession);

        // Act
        ChatSession result = service.createChatSession(newChatSession);

        // Assert
        Assert.assertEquals(result, newChatSession);
    }

    @Test
    public void getChatSessionByTenantAndLandlord_Should_ReturnMatchingChatSession_WhenMatchExist(){
        // Arrange
        ChatSessionDTO chatSessionDTO=new ChatSessionDTO();
        Mockito.when(mockRepository.getChatSessionByTenantAndLandlord(chatSessionDTO))
                .thenReturn(defaultTestInput.get(1));

        // Act
        ChatSession result = service.getChatSessionByTenantAndLandlord(chatSessionDTO);

        // Assert
        Assert.assertEquals(result, defaultTestInput.get(1));
    }

    @Test
    public void isChatSessionCreated_Should_ReturnTrue_WhenChatSessionIsCreated(){
        // Arrange
        ChatSessionDTO chatSessionDTO=new ChatSessionDTO();
        Mockito.when(mockRepository.isChatSessionCreated(chatSessionDTO))
                .thenReturn(true);

        // Act
        boolean isChatSessionCreated = service.isChatSessionCreated(chatSessionDTO);

        // Assert
        Assert.assertTrue(isChatSessionCreated);
    }

    @Test
    public void getAllChatSessionsByTenant_Should_ReturnAllMatchingChatSessionsByTenant_WhenMatchExist(){
        // Arrange
        Mockito.when(mockRepository.getAllChatSessionsByTenant(1))
                .thenReturn(defaultTestInput);

        // Act
        List<ChatSession> result = service.getAllChatSessionsByTenant(1);

        // Assert
        Assert.assertEquals(result,defaultTestInput);
    }

    @Test
    public void getAllChatSessionsByLandlord_Should_ReturnAllMatchingChatSessionsByLandlord_WhenMatchExist(){
        // Arrange
        Mockito.when(mockRepository.getAllChatSessionsByLandlord(1))
                .thenReturn(defaultTestInput);

        // Act
        List<ChatSession> result = service.getAllChatSessionsByLandlord(1);

        // Assert
        Assert.assertEquals(result,defaultTestInput);
    }
}
