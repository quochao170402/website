package com.quochao.website.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploaderService {
    void init();

    String saveFile(MultipartFile file, String dir, String username);

    Resource loadFile(String filename, String dir);
}
