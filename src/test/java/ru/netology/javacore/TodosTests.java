package ru.netology.javacore;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TodosTests {
    Todos testTodos;

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() { System.out.println("Test completed"); }

    @AfterAll
    public static void finishedAll() { System.out.println("Tests completed"); }

    @BeforeEach
    void setUp() { testTodos = new Todos(); }

    @Test
    public void testAddTask() {
        //arrange
        int x = 1;
        String testTask = "Meeting";

        //act
        testTodos.addTask(testTask);

        //assert
        assertNotNull(testTodos);
        assertEquals(x, testTodos.getTaskList().size());
    }

    @Test
    public void testRemoveTask() {
        //arrange
        String testTask = "Lecture";
        testTodos.addTask(testTask);

        //act
        testTodos.removeTask(testTask);

        //assert
        assertTrue(testTodos.getTaskList().isEmpty());
    }

    @Test
    public void testGetAllTask() {
        //arrange
        testTodos.addTask("Studies");
        testTodos.addTask("Meeting");
        testTodos.addTask("Yoga");
        testTodos.addTask("Lecture");
        testTodos.addTask("Jogging");

        //act
        String actualMsg = testTodos.getAllTasks();

        //assert
        String expectedMsg = "Jogging Lecture Meeting Studies Yoga";
        assertEquals(expectedMsg, actualMsg);
    }

}



