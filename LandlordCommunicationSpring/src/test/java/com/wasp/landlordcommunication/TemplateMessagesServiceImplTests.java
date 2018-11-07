package com.wasp.landlordcommunication;

import com.wasp.landlordcommunication.models.templatemessage.TemplateMessage;
import com.wasp.landlordcommunication.repositories.base.TemplateMessagesRepository;
import com.wasp.landlordcommunication.services.TemplateMessagesServiceImpl;
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
public class TemplateMessagesServiceImplTests {
    @Mock
    TemplateMessagesRepository mockRepository;

    @InjectMocks
    TemplateMessagesServiceImpl service;

    List<TemplateMessage> defaultTestInput = Arrays.asList(
            new TemplateMessage(
                    "Text1",
                    "Casual"),
            new TemplateMessage(
                    "Text2",
                    "Casual"),
            new TemplateMessage(
                    "Text3",
                    "Casual"));

    @Test
    public void getTemplateMessageByTemplateType_Should_ReturnMatchingTemplateMessageType_WhenMatchExist() {
        // Arrange
        Mockito.when(mockRepository.getByTemplateType("Casual"))
                .thenReturn(defaultTestInput);

        // Act
        List<TemplateMessage> result = service.getByTemplateType("Casual");

        // Assert
        Assert.assertEquals(result, defaultTestInput);
    }
}
