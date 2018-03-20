package com.mohamed.rest.vt.springboot2restservicevachetaureaux.model;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    @Test
    public void should_return_0_vahces_4_teaureaux() throws Exception {
        Game game = new Game();
        String correction = game.calculateVacheTaureaux(1234, 1234);
        String expectedValue = "0 Vache(s) 4 Taureau(x)";
        Assert.assertEquals(expectedValue, correction);
    }

    @Test
    public void should_return_4_vache_0_teaureaux() throws Exception {
        Game game = new Game();
        String correction = game.calculateVacheTaureaux(1678, 1234);
        String expectedValue = "0 Vache(s) 1 Taureau(x)";
        Assert.assertEquals(expectedValue, correction);
    }

    @Test
    public void should_return_0_vache_0_teaureaux() throws Exception {
        Game game = new Game();
        String correction = game.calculateVacheTaureaux(5678, 1234);
        String expectedValue = "0 Vache(s) 0 Taureau(x)";
        Assert.assertEquals(expectedValue, correction);
    }

    @Test
    public void should_return_1_vache_0_teaureaux() throws Exception {
        Game game = new Game();
        String correction = game.calculateVacheTaureaux(2678, 1234);
        String expectedValue = "1 Vache(s) 0 Taureau(x)";
        Assert.assertEquals(expectedValue, correction);
    }

    @Test
    public void should_return_0_vache_1_teaureaux() throws Exception {
        Game game = new Game();
        String correction = game.calculateVacheTaureaux(1678, 1234);
        String expectedValue = "0 Vache(s) 1 Taureau(x)";
        Assert.assertEquals(expectedValue, correction);
    }

    @Test
    public void should_return_2_vache_2_teaureaux() throws Exception {
        Game game = new Game();
        String correction = game.calculateVacheTaureaux(1243, 1234);
        String expectedValue = "2 Vache(s) 2 Taureau(x)";
        Assert.assertEquals(expectedValue, correction);
    }
}