package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;


    @Test
    public void testGelAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "testTitle", "testContent"));
        Mockito.when(repository.findAll()).thenReturn(taskList);
        //When
        List<Task> testedList = dbService.gelAllTasks();
        //Then
        Assert.assertEquals(1, testedList.size());
        Assert.assertEquals("testContent", testedList.get(0).getContent());
    }

    @Test
    public void testGetTask() {
        //Given
        Task task1 = new Task(1l, "testTitle1", "testContent1");
        Task task2 = new Task(2l, "testTitle2", "testContent2");
        Mockito.when(repository.findById(1l)).thenReturn(java.util.Optional.of(task1));
        Mockito.when(repository.findById(2l)).thenReturn(java.util.Optional.of(task2));
        //When
        Optional<Task> testedTask1 = dbService.getTask(1L);
        Optional<Task> testedTask2 = dbService.getTask(2L);
        //Then
        Assert.assertNotNull(testedTask1);
        Assert.assertNotNull(testedTask2);
        Assert.assertEquals("testContent1", testedTask1.get().getContent());
        Assert.assertEquals("testContent2", testedTask2.get().getContent());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L,"testTitle", "testContent");

        Mockito.when(repository.save(task)).thenReturn(new Task(task.getId(), task.getTitle(), task.getContent()));
        //When
        Task testedTask = dbService.saveTask(task);
        //Then
        Mockito.verify(repository, Mockito.times(1)).save(task);
    }

    @Test
    public void testDeleteTask() {
        //Given
        Task task = new Task(1L,"testTitle", "testContent");
        //When
        dbService.deleteTask(1L);
        //Then
        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }
}