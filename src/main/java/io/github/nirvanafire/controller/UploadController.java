package io.github.nirvanafire.controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>@author: NirvanaFire(nirvanafire@gmail.com)
 * <p>@description: UploadController
 * <p>@since: v1.1
 **/
@RestController
public class UploadController {

    @PostMapping("/upload_commons")
    public String uploadCommons(@RequestPart("file") MultipartFile file) {

        System.out.println("---Run Upload Commons---");
        storeFile(file);
        return "success";
    }

    @PostMapping("/upload_servlet")
    public String uploadServlet(@RequestPart("file") MultipartFile file) {

        System.out.println("---Run Upload Servlet3.0---");
        storeFile(file);
        return "success";
    }

    private void storeFile(MultipartFile file) {
        try {

            String originalFilename = file.getOriginalFilename();
            System.out.println("----File Name: " + originalFilename + "----");

            File tmpFolder = new File("tmp");
            if (!tmpFolder.exists()) {
                tmpFolder.mkdir();
            }

            File tmp = new File("tmp/" + originalFilename);
            FileOutputStream fos = new FileOutputStream(tmp);
            FileCopyUtils.copy(file.getInputStream(), fos);
        } catch (IOException e) {
            System.out.println("Upload File Error: " + e.getMessage());
        }
    }
}
