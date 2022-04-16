package com.quochao.website.formatter;

import com.quochao.website.entity.Brand;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class BrandFormatter implements Formatter<Brand> {
    @Override
    public Brand parse(String text, Locale locale) throws ParseException {
        Brand brand = new Brand();
        brand.setId(Long.parseLong(text));
        return brand;
    }

    @Override
    public String print(Brand object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
