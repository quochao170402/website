package com.quochao.website.formatter;

import com.quochao.website.entity.Size;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class SizeFormatter implements Formatter<Size> {
    @Override
    public Size parse(String text, Locale locale) throws ParseException {
        Size size = new Size();
        size.setId(Long.parseLong(text));
        return size;
    }

    @Override
    public String print(Size object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
