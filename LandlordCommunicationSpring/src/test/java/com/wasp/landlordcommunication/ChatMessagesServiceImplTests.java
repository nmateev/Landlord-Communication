package com.wasp.landlordcommunication;

import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.repositories.base.ChatMessagesRepository;
import com.wasp.landlordcommunication.services.ChatMessagesServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ChatMessagesServiceImplTests {
    @Mock
    ChatMessagesRepository mockRepository;

    @InjectMocks
    ChatMessagesServiceImpl service;

    List<ChatMessage> defaultTestInput = Arrays.asList(
            new ChatMessage(
                    1,
                    1,
                    1,
                    1,
                    new Date(),
                    "Text1",
            "Image1",
            true,
        true),
            new ChatMessage(
                    2,
                    2,
                    2,
                    2,
                    new Date(),
                    "Text2",
                    "Image2",
                    true,
                    true),
            new ChatMessage(
                    3,
                    3,
                    3,
                    3,
                    new Date(),
                    "Text3",
                    "Image3",
                    false,
                    false
            ));

    @Test
    public void getMessagesByChatSessionId_Should_ReturnMatchingChatMessages_WhenMatchExist(){
        // Arrange
        Mockito.when(mockRepository.getMessagesByChatSessionId(1))
                .thenReturn(defaultTestInput);

        // Act
        List<ChatMessage> result = service.getMessagesByChatSessionId(1);

        // Assert
        Assert.assertEquals(result, defaultTestInput);
    }

    @Test
    public void getTenantsUndeliveredMessagesByChatSessionId_Should_ReturnMatchingChatMessages_WhenMatchExist(){
        // Arrange
        Mockito.when(mockRepository.getTenantsUndeliveredMessagesByChatSessionId(1))
                .thenReturn(defaultTestInput);

        // Act
        List<ChatMessage> result = service.getTenantsUndeliveredMessagesByChatSessionId(1);

        // Assert
        Assert.assertEquals(result, defaultTestInput);
    }

    @Test
    public void getLandlordsUndeliveredMessagesByChatSessionId_Should_ReturnMatchingChatMessages_WhenMatchExist(){
        // Arrange
        Mockito.when(mockRepository.getLandlordsUndeliveredMessagesByChatSessionId(1))
                .thenReturn(defaultTestInput);

        // Act
        List<ChatMessage> result = service.getLandlordsUndeliveredMessagesByChatSessionId(1);

        // Assert
        Assert.assertEquals(result, defaultTestInput);
    }

    @Test
    public void postChatMessage_Should_ReturnNewChatMessage(){
        // Arrange
        ChatMessage newMessage=new ChatMessage();
        Mockito.when(mockRepository.postChatMessage(defaultTestInput.get(0)))
                .thenReturn(newMessage);

        // Act
        ChatMessage result = service.postChatMessage(defaultTestInput.get(0));

        // Assert
        Assert.assertEquals(result, newMessage);
    }
    @Test
    public void updateChatMessage_Should_ReturnUpdatedChatMessage(){
        // Arrange
        ChatMessage updatedMessage=new ChatMessage();
        Mockito.when(mockRepository.updateChatMessage(defaultTestInput.get(0)))
                .thenReturn(updatedMessage);

        // Act
        ChatMessage result = service.updateChatMessage(defaultTestInput.get(0));

        // Assert
        Assert.assertEquals(result,updatedMessage);
    }
}
