package com.example.buildingservice.validator.util;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class ValidUtil {
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png");
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    private static boolean isExtensionValid(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
            return ALLOWED_EXTENSIONS.contains(fileExtension);
        }
        return false;
    }
    private static boolean isSizeValid(MultipartFile file) {
        return file.getSize() <= MAX_FILE_SIZE;
    }
    public static void validImage(MultipartFile image, Errors errors, String field) {
        if(image!=null){
            if (!ValidUtil.isExtensionValid(image)) {
                errors.rejectValue(field, "", "Format should be 'jpg', 'jpeg' or 'png'");
            }
            if (!ValidUtil.isSizeValid(image)) {
                errors.rejectValue(field, "", "Size should be less then 10MB");
            }
        }
    }
}