package edu.vku.searchjob.controller;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Cadidates;
import edu.vku.searchjob.entity.Categories;
import edu.vku.searchjob.service.ICadidatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class candidateController {
    @Autowired
    private ICadidatesService iCadidatesService;

//    @PostMapping("/user/updateCandidate")
//    public String updateCandidate(@ModelAttribute(("cadidates")) Cadidates cadidates, BindingResult result,Model model) {
////
////        categoryValidator.validate(category, result);
////        if (result.hasErrors()) {
////
////            return "/admin/addCategory";
////        }
//        iCadidatesService.updateCandidate(cadidates);
//        model.addAttribute("sussues","cập nhật thành công category");
//        return "redirect:/user/myAccount";
//    }
//    @PostMapping("/user/updateCandidate")
//    public String updateCandidate(@ModelAttribute(("cadidates")) Cadidates cadidates, BindingResult result, Model model){
////        iCategoriesService.addCategory(category);
//        iCadidatesService.updateCandidate(cadidates);
//        model.addAttribute("sussues","cập nhật thành công category");
//        return "user/myAccount";
//    }
@PostMapping("/user/updateCandidate/{id}")
    public String updateCandidate(@PathVariable int id, @RequestParam("skill") String skill, @RequestParam("dateOfBirth") String dateOfBirth, @RequestParam("phoneNumber") int phoneNumber, @RequestParam("address") String address, @RequestParam("gender") String gender, Model model, Principal p){
        model.addAttribute("email",p.getName());
       // public Cadidates updateCandidate(int id,String skill, String dateOfBirth, int phoneNumber, String address, String gender)
        iCadidatesService.updateCandidate(id,skill,dateOfBirth,phoneNumber,address,gender);
        return "redirect:/user/myAccount";
    }

//    Categories category, BindingResult result,Model model
//    @GetMapping("/user/myAccountt/{id}")
//    public String showUpdateForm(@PathVariable int id, Model model) {
//        String userEmail = principal.getName();
//        Account account = iAccountService.finByEmail(userEmail);
//        Cadidates candidate = iCadidatesService.getCandidateUserById(id);
//        model.addAttribute("candidate", candidate);
//        return "update-candidate";
//    }
}
