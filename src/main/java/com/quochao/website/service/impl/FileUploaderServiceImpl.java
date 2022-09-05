package com.quochao.website.service.impl;

import com.quochao.website.service.FileUploaderService;
import com.quochao.website.util.FileUploaderConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class FileUploaderServiceImpl implements FileUploaderService {
    private final Path rootDirUploadBrandLogo = Paths.get(FileUploaderConstant.DIR_UPLOAD_BRAND_LOGO);
    private final Path rootDirUploadProductImage = Paths.get(FileUploaderConstant.DIR_UPLOAD_PRODUCT_IMAGE);
    private final Path rootDirUploadUserImage = Paths.get(FileUploaderConstant.DIR_UPLOAD_USER_IMAGE);

    @Override
    public void init() {
        try {
            Files.createDirectories(Path.of(FileUploaderConstant.DIR_UPLOAD_BRAND_LOGO));
            Files.createDirectories(Path.of(FileUploaderConstant.DIR_UPLOAD_USER_IMAGE));
            Files.createDirectories(Path.of(FileUploaderConstant.DIR_UPLOAD_PRODUCT_IMAGE));
        } catch (Exception e) {
            log.error("Error while creating directories, {}", e.getMessage());
        }
    }

    private void rename(MultipartFile file, String dir, String filename) {
        Path root = getRootPath(dir);
        try {
            Files.copy(file.getInputStream(), root.resolve(filename));
        } catch (IOException e) {
            log.error("Error while renaming file");
        }
    }


    @Override
    public String saveFile(MultipartFile file, String dir, String username) {
        Path root = getRootPath(dir);

        if (root == null) {
            log.error("Could save file {}, because root path is null", file.getOriginalFilename());
            return null;
        }

        String extension = file.getOriginalFilename().split("\\.")[1];
        String filename = String.format("%s.%s", username, extension);
        System.out.println(filename);
        Path path = root.resolve(filename);
        if (Files.exists(path)) {
            log.warn("File {} already existed", path);
            return filename;
        }

        try {
            Files.copy(file.getInputStream(), path);
            log.info("Save file {} successful.", filename);
            return filename;
        } catch (Exception e) {
            log.error("Error while saving file. Error {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Resource loadFile(String filename, String dir) {
        Path root = getRootPath(dir);
        if (root == null) {
            log.error("Could save file {}, because root path is null", filename);
            return null;
        }
        try {
            Path path = root.resolve(filename);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                log.info("Load file {} successful", filename);
                return resource;
            } else {
                log.error("Not found file {}", filename);
                return null;
            }
        } catch (MalformedURLException e) {
            log.error("Error while reading file {}", filename);
            return null;
        }
    }

    private Path getRootPath(String dir) {
        switch (dir) {
            case FileUploaderConstant.DIR_UPLOAD_BRAND_LOGO -> {
                return rootDirUploadBrandLogo;
            }
            case FileUploaderConstant.DIR_UPLOAD_PRODUCT_IMAGE -> {
                return rootDirUploadProductImage;
            }
            case FileUploaderConstant.DIR_UPLOAD_USER_IMAGE -> {
                return rootDirUploadUserImage;
            }
        }
        return null;
    }
}
