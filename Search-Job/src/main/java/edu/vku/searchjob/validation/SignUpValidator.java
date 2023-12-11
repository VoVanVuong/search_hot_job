package edu.vku.searchjob.validation;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Categories;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignUpValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public  void validate(Object target, Errors errors) {
        Account account=(Account)  target;
        String name = "name";
        String email="email";
        String password="password";
        if (account.getName() == null || account.getName().isEmpty()) {
            errors.rejectValue(name, "category.null", "Category name cannot be empty");
        } else if (account.getName().length() > 100) {
            errors.rejectValue(name, "name.length", "Name must be less than 100 characters.");
        } else if (!account.getName().matches("^[a-zA-Z\\s]*$")) {
            errors.rejectValue(name, "name.invalid", "Name should only contain letters and spaces.");
        }

        if (account.getEmail() == null || account.getEmail().isEmpty()) {
            errors.rejectValue(email, "email.null", "Email cannot be empty");
        } else if (!account.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.rejectValue(email, "email.invalid", "Invalid email format");
        }
        // Kiểm tra mật khẩu
        if (account.getPassword()== null || account.getPassword().isEmpty()) {
            errors.rejectValue("password", "password.empty", "Password cannot be empty");
        } else if (account.getPassword().length() < 6) {
            errors.rejectValue("password", "password.length", "Password must be at least 6 characters long.");
        } else if (!account.getPassword().matches(".*[a-zA-Z].*") || !account.getPassword().matches(".*\\d.*")) {
            errors.rejectValue("password", "password.invalid", "Password must contain both letters and digits.");
        }
//        if (account.getName() == null ||  account.getName() == "") {
//            errors.rejectValue(name, "category.null", "Category name cannot be empty");
//        } else if (account.getName().length() > 100) {
//            errors.rejectValue(name, "name.length", "Name must be less than 100 characters.");
//        }
    }
}
