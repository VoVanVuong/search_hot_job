package edu.vku.searchjob.controller;

import edu.vku.searchjob.entity.*;
import edu.vku.searchjob.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
public class candidateController {
    @Autowired
    private ICadidatesService iCadidatesService;
    @Autowired
    private IEmployersService iEmployersService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
   private IEmployersSearchCandidatesService iEmployersSearchCandidatesService;
    @Autowired
    private IJobsService iJobsService;

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
    public String updateCandidate(@PathVariable int id, @RequestParam("skill") String skill, @RequestParam("dateOfBirth") String dateOfBirth, @RequestParam("phoneNumber") int phoneNumber, @RequestParam("address") String address, @RequestParam("gender") String gender,@RequestParam("cadidatedName") String cadidatedName,@RequestParam("work") String work,@RequestParam("candidateCv") MultipartFile candidateCv,@RequestParam("categotyRequired") String categotyRequired,@RequestParam("experience") String experience,@RequestParam("salaryRequired") String salaryRequired, Model model, Principal p){
        model.addAttribute("email",p.getName());
        model.addAttribute("success","Successfully updated information");
       // public Cadidates updateCandidate(int id,String skill, String dateOfBirth, int phoneNumber, String address, String gender)
        iCadidatesService.updateCandidate(id,skill,dateOfBirth,phoneNumber,address,gender,candidateCv,cadidatedName,work,categotyRequired,experience,salaryRequired);
        return "redirect:/user/myAccount";
    }
    @GetMapping("/employer/employerSearchCandidate")
    public String lemployerSearchCandidate( @RequestParam(required = false) String work,
                                            @RequestParam(required = false) String experience,
                                            @RequestParam(required = false) String categoryRequired,
                                            @RequestParam(required = false) String salaryRequired,Principal p,Model model){
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        model.addAttribute("acc", acc);
//        List<Integer> employers=iEmployersService.findAllIds();
//                for (Integer employerId : employers) {
//            if (Objects.equals(acc.getId(), employerId)) {
//                // Tìm thấy sự khớp, bạn có thể sử dụng employerId ở đây
//                System.err.println("Tìm thấy sự khớp với ID: " + employerId);
////                employersSearchCandidates.setEmployers(employerId.intValue());
//                model.addAttribute("employerId",employerId);
//                break; // Nếu chỉ quan tâm đến sự khớp đầu tiên
//            }
//        }
        List<Cadidates> results = iCadidatesService.search(work, experience, categoryRequired, salaryRequired);
        model.addAttribute("results", results);
        model.addAttribute("work", work);
        model.addAttribute("experience", experience);
        model.addAttribute("categoryRequired", categoryRequired);
        model.addAttribute("salaryRequired", salaryRequired);

        model.addAttribute("employersSearchCandidates", new EmployersSearchCandidates());

        return "employer/employerSearchCandidate";
    }
    @PostMapping ("/employer/addEmployersSearchCandidates")
    public String addEmployersSearchCandidates( @ModelAttribute(("employersSearchCandidates")) EmployersSearchCandidates employersSearchCandidates,@RequestParam("candidateID") Cadidates cadidates, Model model,Principal p){
//        categoryValidator.validate(category, result);
//        if (result.hasErrors()) {
//
//            return "/admin/addCategory";
//        }
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        Employers employers=iEmployersService.findEmployersByEmail(acc);
        System.err.println("dsdshj"+employers.getId()+"acc"+employers.getAccount().getId());
      //  Employers employers=iEmployersService.findByAccountId(acc);


//        List<Integer> employers=iEmployersService.findAllIds();
         System.err.println("acc"+acc.getId());
       // System.err.println("Emz"+employers);

        // Kiểm tra sự khớp
//        for (Integer employerId : employers) {
//            if (Objects.equals(acc.getId(), employerId)) {
//                // Tìm thấy sự khớp, bạn có thể sử dụng employerId ở đây
//                System.out.println("Tìm thấy sự khớp với ID: " + employerId);
////                employersSearchCandidates.setEmployers(employerId.intValue());
//                model.addAttribute("employerId",employerId);
//                break; // Nếu chỉ quan tâm đến sự khớp đầu tiên
//            }
//        }

//        System.err.println(employers.);
//        Employers employers=i
        //iCategoriesService.addCategory(category);
        employersSearchCandidates.setDeleteFlag(false);
        employersSearchCandidates.setStatus(false);
        employersSearchCandidates.setStatusInterview(false);
        employersSearchCandidates.setEmployers(employers);
//employersSearchCandidates.setEmployers(employerID);
        employersSearchCandidates.setCadidates(cadidates);
        //employersSearchCandidates.setEmployers();
      //  employersSearchCandidates.setCadidates();
        iEmployersSearchCandidatesService.addEmployersSearchCandidates(employersSearchCandidates);
        model.addAttribute("sussues","cập nhật thành công category");
        return "redirect:/employer/employerSearchCandidate";

//        return "employer/employerSearchCandidate";
    }

    @GetMapping("/employer/informationCandidate")
    public String informationCandidate(Model model,Principal p){
    return "employer/informationCandidate";
    }
    @GetMapping("/user/jobFeedback")
    public String jobFeedback(Model model,Principal p){
        return "user/jobFeedback";
    }
    @GetMapping("/user/interview")
    public String interview(Model model,Principal p){
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        Cadidates cadidatesAccount=iCadidatesService.candidatesaccount(acc);
       List<EmployersSearchCandidates> employersSearchCandidatesListFalse=iEmployersSearchCandidatesService.listStatusFalse(cadidatesAccount);
        model.addAttribute("email",acc.getName());
        model.addAttribute("employersSearchCandidatesListFalse",employersSearchCandidatesListFalse);

        List<Employers> employersList = iEmployersService.finAll();
        model.addAttribute("employersList",employersList);
        List<Jobs> jobsListThree=iJobsService.findLatestThreeJobs();
        model.addAttribute("jobsListThree",jobsListThree);
//      redirectAttributes.addFlashAttribute("messages", "Successful application");
        return "user/interview";
    }
    @GetMapping("/user/statusInterview")
    public String statusInterview(Model model,Principal p){
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        Cadidates cadidatesAccount=iCadidatesService.candidatesaccount(acc);
        List<EmployersSearchCandidates> employersSearchCandidatesListFalse=iEmployersSearchCandidatesService.listStatusTrue(cadidatesAccount);
        model.addAttribute("email",acc.getName());
        model.addAttribute("employersSearchCandidatesListFalse",employersSearchCandidatesListFalse);

        List<Employers> employersList = iEmployersService.finAll();
        model.addAttribute("employersList",employersList);
        List<Jobs> jobsListThree=iJobsService.findLatestThreeJobs();
        model.addAttribute("jobsListThree",jobsListThree);
//      redirectAttributes.addFlashAttribute("messages", "Successful application");
        return "user/statusInterview";
    }
    @GetMapping("/user/interviewStatus/{id}")
    public String deleteArticle(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        iEmployersSearchCandidatesService.statusTrue(id);
        redirectAttributes.addFlashAttribute("messages", "Successful application");
        return "redirect:/user/interview";
    }
    @GetMapping("/user/interviewStatusFalse/{id}")
    public String deleteArticleFalse(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        iEmployersSearchCandidatesService.statusfalse(id);
        redirectAttributes.addFlashAttribute("messages", "Successful application");
        return "redirect:/user/interview";
    }
//    @GetMapping("/job-search")
//    public String jobSearch(
//            @RequestParam(required = false) String work,
//            @RequestParam(required = false) String experience,
//            @RequestParam(required = false) String categoryRequired,
//            @RequestParam(required = false) String salaryRequired,
//            Model model
//    ) {
//        List<Job> results = jobService.search(work, experience, categoryRequired, salaryRequired);
//        model.addAttribute("results", results);
//        model.addAttribute("work", work);
//        model.addAttribute("experience", experience);
//        model.addAttribute("categoryRequired", categoryRequired);
//        model.addAttribute("salaryRequired", salaryRequired);
//
//        return "job-search";
//    }

//    Categories category, BindingResult result,Model model
//    @GetMapping("/user/myAccountt/{id}")
//    public String showUpdateForm(@PathVariable int id, Model model) {
//        String userEmail = principal.getName();
//        Account account = iAccountService.finByEmail(userEmail);
//        Cadidates candidate = iCadidatesService.getCandidateUserById(id);
//        model.addAttribute("candidate", candidate);
//        return "update-candidate";
//    }
//    }
}
