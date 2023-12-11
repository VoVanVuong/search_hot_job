package edu.vku.searchjob.validation;

import edu.vku.searchjob.entity.Categories;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public  void validate(Object target, Errors errors) {
        Categories category = (Categories) target;
        String name = "name";
        if (category.getName() == null ||  category.getName() == "") {
            errors.rejectValue(name, "category.null", "Category name cannot be empty");
         } else if (category.getName().length() > 300) {
        errors.rejectValue(name, "name.length", "Name must be less than 300 characters.");
    }
    }
}
