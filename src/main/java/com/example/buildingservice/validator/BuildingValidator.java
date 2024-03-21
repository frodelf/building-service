package com.example.buildingservice.validator;

import com.example.buildingservice.dto.BuildingDtoForAdd;
import com.example.buildingservice.validator.util.ValidUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class BuildingValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BuildingDtoForAdd.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BuildingDtoForAdd buildingDtoForAdd = (BuildingDtoForAdd) target;
        if(buildingDtoForAdd.getId()!=null){
            if(buildingDtoForAdd.getImages()==null || buildingDtoForAdd.getImages().get(0)==null){
                errors.rejectValue("images", "", "Can't br null");
            }else {
                for (MultipartFile image : buildingDtoForAdd.getImages()) {
                    ValidUtil.validImage(image, errors, "images");
                }
                for (MultipartFile document : buildingDtoForAdd.getDocuments()) {
                    ValidUtil.validImage(document, errors, "documents");
                }
            }
        }else {
            if(buildingDtoForAdd.getImages()!=null && buildingDtoForAdd.getImages().get(0)!=null){
                for (MultipartFile image : buildingDtoForAdd.getImages()) {
                    ValidUtil.validImage(image, errors, "images");
                }
            }
            if(buildingDtoForAdd.getDocuments()!=null && buildingDtoForAdd.getDocuments().get(0)!=null){
                for (MultipartFile document : buildingDtoForAdd.getDocuments()) {
                    ValidUtil.validImage(document, errors, "documents");
                }
            }
        }
    }
}