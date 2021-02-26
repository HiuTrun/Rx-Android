package hiutrun.example.rxjavacourse.utils;

import java.util.ArrayList;
import java.util.List;

import hiutrun.example.rxjavacourse.models.Task;

public class DataSource {

    public static List<Task> createTaskList(){
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Take out the trash", true, 3));
        tasks.add(new Task("Walk the dog", false, 2));
        tasks.add(new Task("Make my bed", true,1));
        tasks.add(new Task("Make dinner", true,5));
        return tasks;

    }
}
