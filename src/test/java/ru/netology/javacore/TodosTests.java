package ru.netology.javacore;

import org.junit.jupiter.api.*;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class TodosTests {

    Todos testTodos;
    Todos expectedTodos;

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @BeforeEach
    void setUp() {
        testTodos = new Todos ();
        expectedTodos = new Todos ();
    }

    @Test
    public void testAddTask() {

        //arrange
        List<String> testList = new ArrayList<>();
        testList.add("Jogging");
        testList.add("Yoga");
        testList.add("Studies");
        testList.add ("Lecture");
        testTodos.setTaskList (testList);

        String testTask = "Meeting";

        List<String> expectedList = new ArrayList<> ();
        expectedList.add ("Jogging");
        expectedList.add ("Yoga");
        expectedList.add ("Studies");
        expectedList.add ("Lecture");
        expectedList.add ("Meeting");
        expectedTodos.setTaskList(expectedList);

        //act
        testTodos.addTask ( testTask );

        //assert
        Assertions.assertEquals ( expectedTodos, testTodos );
    }

    @Test
    public void testRemoveTask() {

        //arrange
        List<String> testList = new ArrayList<>();
        testList.add("Jogging");
        testList.add("Yoga");
        testList.add("Studies");
        testList.add ("Lecture");
        testTodos.setTaskList (testList);

        String testTask = "Lecture";

        List<String> expectedList = new ArrayList<> ();
        expectedList.add ("Jogging");
        expectedList.add ("Yoga");
        expectedList.add ("Studies");
        expectedTodos.setTaskList(expectedList);

        //act
        testTodos.removeTask ( testTask );

        //assert
        Assertions.assertEquals ( expectedTodos, testTodos );

    }

    @Test
    public void testGetAllTasks() {
        //arrange
        List<String> testList = new ArrayList<>();
        testList.add("Jogging");
        testList.add("Yoga");
        testList.add ("Lecture");
        testList.add("Studies");
        testTodos.setTaskList (testList);

        String expectedMsg = "Jogging Lecture Studies Yoga";

        //act
        String testMsg = testTodos.getAllTasks ();

        //assert
        Assertions.assertEquals ( expectedMsg, testMsg );

    }

}
