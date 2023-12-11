package edu.vku.searchjob.validation;

import edu.vku.searchjob.entity.Jobs;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class JobValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Jobs jobs=(Jobs) target;
        String name = "name";
        String address = "address";
        String salary = "salary";
        String categories = "categories";
        String benefit="benefit";
        String desription="desription";

        if (jobs.getName() == null ||  jobs.getName() == "") {
            errors.rejectValue(name, "jobs.null", "jobs name cannot be empty");
        } else if (jobs.getName().length() > 300) {
            errors.rejectValue(name, "name.length", "Name must be less than 300 characters.");
        }
        if (jobs.getAddress() == null || jobs.getAddress().isEmpty()) {
            errors.rejectValue(address, "jobs.null", "Job address cannot be empty");
        } else if (jobs.getAddress().length() > 500) {
            errors.rejectValue(address, "address.length", "Address must be less than 500 characters.");
        }
        if (jobs.getAddress() == null || jobs.getAddress().isEmpty()) {
            errors.rejectValue("address", "jobs.address.empty", "Job address cannot be empty");
        } else if (jobs.getAddress().length() > 500) {
            errors.rejectValue("address", "jobs.address.length", "Address must be less than 500 characters.");
        }

        // Validate totalCandidate
        if (jobs.getTotalCandidate() < 0 || jobs.getTotalCandidate() > 10000) {
            errors.rejectValue("totalCandidate", "jobs.totalCandidate.range", "Total candidate must be between 0 and 10000");
        }
        if (jobs.getTotalCandidate() == 0) {
            errors.rejectValue("totalCandidate", "jobs.totalCandidate.empty", "Total candidate cannot be empty");
        }

        // Validate totalCandidate is a number
        try {
            Integer.parseInt(String.valueOf(jobs.getTotalCandidate()));
        } catch (NumberFormatException e) {
            errors.rejectValue("totalCandidate", "jobs.totalCandidate.invalid", "Total candidate must be a number");
        }
        // Validate salary
        if (jobs.getSalary() == null || jobs.getSalary().isEmpty()) {
            errors.rejectValue(salary, "salary.empty", "Salary cannot be empty");
        } else {
            try {
                int parsedSalary = Integer.parseInt(jobs.getSalary());
                if (parsedSalary < 0) {
                    errors.rejectValue(salary, "jobs.salary.negative", "Salary cannot be less than 0");
                }
            } catch (NumberFormatException e) {
                errors.rejectValue(salary, "jobs.salary.invalid", "Invalid salary format");
            }
        }
        if (jobs.getCategories() == null || jobs.getCategories().equals("")) {
            errors.rejectValue(categories, "jobs.categories.empty", "Please select a category");
        } else if (jobs.getCategories().equals("defaultOptionValue")) {  // Replace "defaultOptionValue" with the actual value of the default option
            errors.rejectValue(categories, "jobs.categories.defaultOption", "Please select a valid category");
        }

        if (jobs.getBenefit() == null ||  jobs.getBenefit() == "") {
            errors.rejectValue(benefit, "jobs.null", "jobs name cannot be empty");
        } else if (jobs.getBenefit().length() > 3000000) {
            errors.rejectValue(benefit, "benefit.length", "benefit must be less than 3000000 characters.");
        }


        if (jobs.getDesription()== null ||  jobs.getDesription() == "") {
            errors.rejectValue(desription, "jobs.null", "jobs name cannot be empty");
        } else if (jobs.getDesription().length() > 300) {
            errors.rejectValue(desription, "desription.length", "Desription must be less than 300 characters.");
        }
    }
}
