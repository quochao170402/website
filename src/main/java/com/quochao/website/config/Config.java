package com.quochao.website.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dwqwccnkg",
                "api_key", "756699172569444",
                "api_secret", "x72HUrZLIj_hKhImB0K0F-0twr0",
                "secure", true));
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
