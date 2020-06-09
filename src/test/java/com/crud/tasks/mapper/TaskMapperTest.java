package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"TestTitle","TestContent");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals("TestTitle", task.getTitle());
        Assert.assertEquals("TestContent", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "TestTitle", "TestContent");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals("TestTitle", taskDto.getTitle());
        Assert.assertEquals("TestContent", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task1 = new Task(1l, "TestTitle1", "TestContent1");
        Task task2 = new Task(2l, "TestTitle2", "TestContent2");
        Task task3 = new Task(3l, "TestTitle3", "TestContent3");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals(3, taskDtos.size());
        Assert.assertEquals("TestTitle2", taskDtos.get(1).getTitle());
    }
}