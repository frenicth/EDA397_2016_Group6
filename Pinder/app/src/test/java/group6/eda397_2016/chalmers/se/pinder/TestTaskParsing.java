package group6.eda397_2016.chalmers.se.pinder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import group6.eda397_2016.chalmers.se.pinder.model.Profile;
import group6.eda397_2016.chalmers.se.pinder.model.Task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestTaskParsing {
    private Task task;
    private List<TaskTest> tasks = new ArrayList<>();

    private class TaskTest {
        String nameAndPoints;
        String desc;
        String id;

        public TaskTest(String id, String nameAndPoints, String desc) {
            this.nameAndPoints = nameAndPoints;
            this.desc = desc;
            this.id = id;
        }
        Task createTask(){
            return new Task(id,nameAndPoints,desc);
        }
    }

    {
        tasks.add(new TaskTest("0","(1) Task1","Required Skills: Javascript, SQL"));
        tasks.add(new TaskTest("1","(3) Task2","Required Skills: Javascript"));
        tasks.add(new TaskTest("2","(2) Task3","Required Skills: "));
    }

    @Test
    public void testTask1() throws Exception {
        Task task = tasks.get(0).createTask();
        assertEquals(1,task.getStoryPoints());
        assertTrue(task.getRequiredSkills().contains("Javascript"));
        assertTrue(task.getRequiredSkills().contains("SQL"));

        task = tasks.get(1).createTask();
        assertEquals(3,task.getStoryPoints());
        assertTrue(task.getRequiredSkills().contains("Javascript"));

        task = tasks.get(2).createTask();
        assertEquals(2,task.getStoryPoints());
        assertEquals(0,task.getRequiredSkills().size());
    }
}