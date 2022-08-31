package com.github.revilated.restaurantvoting.util.validation;

import org.jsoup.*;
import org.jsoup.safety.*;

import javax.validation.*;

public class NoHtmlValidator implements ConstraintValidator<NoHtml, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        return value == null || Jsoup.isValid(value, Safelist.none());
    }
}
