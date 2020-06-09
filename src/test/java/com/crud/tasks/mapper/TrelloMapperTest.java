package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("TestId1", "TestName1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("TestId2", "TestName2", false);

        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("Testname1", "TestId1", trelloListDtos);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("Testname2", "TestId2", trelloListDtos);
        TrelloBoardDto trelloBoardDto3 = new TrelloBoardDto("Testname3", "TestId3", trelloListDtos);

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto1);
        trelloBoardDtos.add(trelloBoardDto2);
        trelloBoardDtos.add(trelloBoardDto3);
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        Assert.assertEquals(3, trelloBoards.size());
        Assert.assertFalse(trelloBoards.get(1).getLists().get(1).isClosed());
    }

    @Test
    public void testMapToBoardsDto() {
        TrelloList trelloList1 = new TrelloList("TestId1", "TestName1", true);
        TrelloList trelloList2 = new TrelloList("TestId2", "TestName2", false);

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);

        TrelloBoard trelloBoard1 = new TrelloBoard("Testname1", "TestId1", trelloLists);
        TrelloBoard trelloBoard2 = new TrelloBoard("Testname2", "TestId2", trelloLists);
        TrelloBoard trelloBoard3 = new TrelloBoard("Testname3", "TestId3", trelloLists);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        trelloBoards.add(trelloBoard3);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        Assert.assertEquals(3, trelloBoardDtos.size());
        Assert.assertFalse(trelloBoardDtos.get(0).getLists().get(1).isClosed());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("testId1", "TestName1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("testId2", "TestName2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("testId3", "TestName3", true);

        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);
        trelloListDtos.add(trelloListDto3);
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        //Then
        Assert.assertEquals(3, trelloLists.size());
        Assert.assertFalse(trelloLists.get(1).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("testId1", "TestName1", true);
        TrelloList trelloList2 = new TrelloList("testId2", "TestName2", true);
        TrelloList trelloList3 = new TrelloList("testId3", "TestName3", false);

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists.add(trelloList3);
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        Assert.assertEquals(3, trelloListDtos.size());
        Assert.assertFalse(trelloListDtos.get(2).isClosed());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("TestName","testDesc", "testPos", "TesListId" );
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals("TestName", trelloCard.getName());
        Assert.assertEquals("testDesc", trelloCard.getDescription());
        Assert.assertEquals("testPos", trelloCard.getPos());
        Assert.assertEquals("TesListId", trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("TestName","testDesc", "testPos", "TesListId" );
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals("TestName", trelloCardDto.getName());
        Assert.assertEquals("testDesc", trelloCardDto.getDescription());
        Assert.assertEquals("testPos", trelloCardDto.getPos());
        Assert.assertEquals("TesListId", trelloCardDto.getListId());
    }
}