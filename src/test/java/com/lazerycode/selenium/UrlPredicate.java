package com.lazerycode.selenium;

import com.google.common.base.Predicate;
import org.openqa.selenium.WebDriver;

/**
 * @author alexandrubostan
 * @since 10.07.2015
 */
public class UrlPredicate implements Predicate<WebDriver> {
    private Predicate<String> predicate;

    public UrlPredicate(Predicate<String> predicate) {
        this.predicate = predicate;
    }

    public static Predicate<WebDriver> currentUrl(Predicate<String> urlPredicate) {
        return new UrlPredicate(urlPredicate);
    }

    @Override
    public boolean apply(WebDriver input) {
        return input != null && predicate.apply(input.getCurrentUrl());
    }

    @Override
    public String toString() {
        return "current url " + predicate;
    }
}
