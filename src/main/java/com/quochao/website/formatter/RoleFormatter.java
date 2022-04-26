package com.quochao.website.formatter;

import com.quochao.website.entity.Role;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoleFormatter implements Formatter<Role> {
    @Override
    public Role parse(String text, Locale locale) throws ParseException {
        Role role = new Role();
        role.setId(Long.parseLong(text));
        return role;
    }

    @Override
    public String print(Role object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
