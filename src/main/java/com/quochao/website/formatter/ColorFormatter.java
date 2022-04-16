package com.quochao.website.formatter;

import com.quochao.website.entity.Color;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ColorFormatter implements Formatter<Color> {
    @Override
    public Color parse(String text, Locale locale) throws ParseException {
        Color color = new Color();
        color.setId(Long.parseLong(text));
        return color;
    }

    @Override
    public String print(Color object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
