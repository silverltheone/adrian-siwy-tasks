package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {

    private static final String SUBJECT = "Task: New Trello Card";

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "test_lis", false));

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("test_Board", "1", trelloListDtos));

        Mockito.when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);
        //When
        List<TrelloBoardDto> testedList = trelloService.fetchTrelloBoards();
        //Then
        Assert.assertNotNull(testedList);
        Assert.assertEquals(1, testedList.size());
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("testName", "testDescription", "testPos", "testId");

        Mail mail = new Mail(adminConfig.getAdminMail(), SUBJECT, trelloCardDto.getName());

        Mockito.when(trelloClient.createNewCard(trelloCardDto)).thenReturn(new CreatedTrelloCardDto("testId", "testName", "tesURL"));
        //When
        CreatedTrelloCardDto testedCard = trelloService.createTrelloCard(trelloCardDto);
        //Then
        Assert.assertNotNull(testedCard);
        Assert.assertEquals("testId", testedCard.getId());
    }
}