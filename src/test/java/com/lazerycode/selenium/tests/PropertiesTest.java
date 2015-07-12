package com.lazerycode.selenium.tests;

import com.google.common.collect.Ordering;
import com.lazerycode.selenium.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.SearchPage;
import pageobjects.SearchResultsPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PropertiesTest extends DriverFactory {
   WebDriver driver;

   @BeforeMethod
   public void before() throws Exception {
       driver = getDriver();
       driver.get("http://t.hostelworld.com");
   }

    @Test
    public void sortPropertiesByName() throws Exception {
        SearchPage searchPage = new SearchPage(driver);

        SearchResultsPage searchResultsPage= searchPage.search("Dublin, Ireland");
        SearchResultsPage sortedPage = searchResultsPage.sortByName();
        List<String> sortedResults = sortedPage.getSearchResults();

        assertThat(Ordering.natural().isOrdered(sortedResults), is(true));
    }

    @Test
    public void sortPropertiesByPrice() throws Exception {
        SearchPage searchPage = new SearchPage(driver);

        SearchResultsPage searchResultsPage = searchPage.search("Dublin, Ireland");
        SearchResultsPage sortedPage = searchResultsPage.sortByPrice();
        List<Double> sortedResults = sortedPage.getSearchResultsByPrice();

        assertThat(Ordering.natural().isOrdered(sortedResults), is(true));
    }

}