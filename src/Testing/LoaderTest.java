/*
package Testing;

import Domain.KuVidGame;
import Domain.SaveLoad.Loader;
import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class LoaderTest {
    // MODIFIES: loader.KuVidGame.stats
    KuVidGame kuVidGame = new KuVidGame(100, 100);
    Loader loader = new Loader(kuVidGame);

    // GlassBox
    @Test
    public void decodeLineIntoStatsTest1() {
        String line = "TimeLeft 302";
        loader.decodeLineIntoStats(line);
        int result = kuVidGame.getStatisticsWindow().getTimeLeft();
        assertEquals(result, 302);
    }

    // GlassBox
    @Test
    public void decodeLineIntoStatsTest2() {
        String line = "Health 80";
        loader.decodeLineIntoStats(line);
        int result = kuVidGame.getStatisticsWindow().getHealth();
        assertEquals(result, 80);
    }

    // GlassBox
    @Test
    public void decodeLineIntoStatsTest3() {
        String line = "Score 125";
        loader.decodeLineIntoStats(line);
        int result = kuVidGame.getStatisticsWindow().getScore();
        assertEquals(result, 125);
    }

    // GlassBox
    @Test
    public void decodeLineIntoStatsTest4() {
        String line = "AtomCounts 1 5 2 3";
        loader.decodeLineIntoStats(line);
        int[] result = kuVidGame.getStatisticsWindow().getAtomCounts();
        assertTrue(1 == result[0] && 5 == result[1] && 2 == result[2] && 3 == result[3]);
    }

    // GlassBox
    @Test
    public void decodeLineIntoStatsTest5() {
        String line = "PowerUpCounts 3 4 2 5";
        loader.decodeLineIntoStats(line);
        int[] result = kuVidGame.getStatisticsWindow().getPowerUpCounts();
		assertTrue(3 == result[0] && 4 == result[1] && 2 == result[2] && 5 == result[3]);
    }

}
*/
