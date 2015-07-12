package com.lazerycode.selenium.tests.anagram;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import section01.Anagram;


/**
 * @author alexandrubostan
 * @since 11.07.2015
 */
public class AnagramTest {
    Anagram anagram;

    @DataProvider(name = "anagrams")
    public Object[][] createData() {
        return new Object[][]{
                {"Eleven plus two", "Twelve plus one"},
                {"Punishment", "Nine Thumps"},
                {"Snooze alarms", "Alas! No more Zs"},
                {"Halley's Comet", "Shall yet come"},
                {"One good turn deserves another.", "Do rogues endorse that? No, never!"}
        };
    }


    @BeforeMethod
    public void before() {
        anagram = new Anagram();
    }


    @Test(dataProvider = "anagrams")
    public void testAnagrams(String s1, String s2) throws Exception {

        MatcherAssert.assertThat(anagram.isAnagram(s1, s2), Is.is(true));
    }
}
