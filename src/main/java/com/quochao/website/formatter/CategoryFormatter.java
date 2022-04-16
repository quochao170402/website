package com.quochao.website.formatter;

import com.quochao.website.entity.Category;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormatter implements Formatter<Category> {
    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        Category category = new Category();
        category.setId(Long.parseLong(text));
        return category;
    }

    @Override
    public String print(Category object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
