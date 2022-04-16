package com.quochao.website.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
public class FileStorage {
    private String subFolder;
    private Cloudinary cloudinary;


    public FileStorage(Cloudinary cloudinary, String subFolder) {
        this.subFolder = subFolder;
        this.cloudinary = cloudinary;
    }

    public String saveFile(MultipartFile file, String name) {
        try {
            return this.cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "auto",
                            // public_id : folder/sub_folder/..../filename => image is a filename
                            "public_id", "H3/" + subFolder + "/" + name,
                            "overwrite", true
                    )).get("url").toString();
        } catch (IOException e) {
            return "no-image";
        }
    }

    public void removeFile(String name) throws IOException {
        cloudinary.uploader().destroy(subFolder + "/" + name, ObjectUtils.asMap());
    }
}
