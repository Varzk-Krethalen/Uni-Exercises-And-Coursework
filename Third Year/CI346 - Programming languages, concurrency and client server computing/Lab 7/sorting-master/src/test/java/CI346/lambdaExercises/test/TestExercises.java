package CI346.lambdaExercises.test;

import static org.junit.Assert.*;
import CI346.lambdaExercises.Exercises;

/**
 * Created by jb259 on 20/11/16.
 */
public class TestExercises {
    Exercises e;
    final String ex0 = "[[Allen Ginsberg], [Gary Snyder], [Gregory Corso], [Hubert Huncke], " +
            "[Jack Kerouac], [Neal Cassady]]";
    final String ex1 = ex0;
    final String ex2 = "[[Gary Snyder], [Neal Cassady], [Jack Kerouac], [Hubert Huncke], " +
            "[Gregory Corso], [Allen Ginsberg]]";
    final String ex3 = "[[Gregory Corso], [Jack Kerouac], [Hubert Huncke], [Gary Snyder], " +
            "[Neal Cassady], [Allen Ginsberg]]";
    final String ex4 = "[[Neal Cassady], [Allen Ginsberg], [Hubert Huncke], [Gary Snyder], " +
            "[Gregory Corso], [Jack Kerouac]]";
    final String ex5 = "[[Gregory Corso], [Hubert Huncke], [Gary Snyder], [Neal Cassady], " +
            "[Jack Kerouac], [Allen Ginsberg]]";
    final String ex6 = "[[Gregory Corso], [Hubert Huncke], [Allen Ginsberg], [Gary Snyder], " +
            "[Neal Cassady], [Jack Kerouac]]";

    @org.junit.Before
    public void setUp() throws Exception {
        e = new Exercises();
    }

    @org.junit.Test
    public void exerciseZero() throws Exception {
        assertEquals(ex0, e.exerciseZero());
    }

    @org.junit.Test
    public void exerciseOne() throws Exception {
        assertEquals(ex1, e.exerciseOne());
    }

    @org.junit.Test
    public void exerciseTwo() throws Exception {
        assertEquals(ex2, e.exerciseTwo());
    }

    @org.junit.Test
    public void exerciseThree() throws Exception {
        assertEquals(ex3, e.exerciseThree());
    }

    @org.junit.Test
    public void exerciseFour() throws Exception {
        assertEquals(ex4, e.exerciseFour());
    }

    @org.junit.Test
    public void exerciseFive() throws Exception {
        assertEquals(ex5, e.exerciseFive());
    }

    @org.junit.Test
    public void exerciseSix() throws Exception {
        assertEquals(ex6, e.exerciseSix());
    }

}