package edu.vku.searchjob.controller;

import edu.vku.searchjob.entity.*;
import edu.vku.searchjob.service.IAccountService;
import edu.vku.searchjob.service.IEmployersSearchCandidatesService;
import edu.vku.searchjob.service.IEmployersService;
import edu.vku.searchjob.service.IJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
//@RequestMapping("/admin")
public class employerController {
    @Autowired
    private IEmployersService iEmployersService;
    @Autowired
    private IJobsService iJobsService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IEmployersSearchCandidatesService iEmployersSearchCandidatesService;
    @Autowired
    public employerController(IEmployersService iEmployersService) {
        this.iEmployersService = iEmployersService;
    }
    @GetMapping("/admin/ListEmployer")
    public String listEmployer(Model model){
        List<Employers> listEmployer =iEmployersService.findByDeleteFlagFalse();
        model.addAttribute("listEmployer",listEmployer);
        return "admin/employerTable";
    }
    @GetMapping("/admin/ListDeleteEmployer")
    public String listDeleteEmployer(Model model){
        List<Employers> listEmployer =iEmployersService.findByDeleteFlagTrue();
        model.addAttribute("listEmployer",listEmployer);
        return "admin/employerDeleteTable";
    }
    @GetMapping("/admin/employer/deleteEmployer/{id}")
    public String deleteCategory(@PathVariable("id") int employerID, RedirectAttributes redirectAttributes) {
        iEmployersService.deleteEmployers(employerID);
        redirectAttributes.addFlashAttribute("messages", "Vaccine đã được chuyển vào thùng rác!");
        return "redirect:/admin/ListEmployer";
    }
    @GetMapping("/admin/employer/undeleteEmployer/{id}")
    public String unDeleteCategory(@PathVariable("id") int employerID, RedirectAttributes redirectAttributes) {
        iEmployersService.unDeleteEmployers(employerID);

        redirectAttributes.addFlashAttribute("messages", "Vaccine đã được chuyển vào thùng rác!");

        return "redirect:/admin/ListDeleteEmployer";
    }
    @GetMapping("/employer/searchResumes")
    public String  searchResumes(){
        return "employer/searchResumes";
    }
    @GetMapping("/user/companyInfo/{id}")
    public String companyInfo(@PathVariable("id") int id,Model model){
        Employers employer=iEmployersService.getJobById(id);
        List<Jobs> jobsList=iJobsService.findLatestThreeJobs();
        model.addAttribute("jobsList",jobsList);
        model.addAttribute("employer", employer);

        List<Employers> employersList = iEmployersService.finAll();
        model.addAttribute("employersList",employersList);

       List<Jobs> jobsListAcc=iJobsService.findByDeleteFlagFalse();
       model.addAttribute("jobsListAcc",jobsListAcc);
//       <th:block th:with="filteredJobs=${#lists.filter(jobsListAcc, job -> job.accountId == employer.getAccount().getId())}">
//    <!-- Display the names of filtered jobs -->
//    <th:each var="job" in="${filteredJobs}">
//        <p th:text="${job.name}"></p>
//    </th:each>
//</th:block>
   //     System.err.println("id của acc"+ employer.getAccount().getId() );
        return "user/companyInfo";
    }
    @GetMapping("/employer/invitedCandidate")
    public String invitedCandidate(Principal p,Model model){
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        model.addAttribute("acc",acc.getName());
        Employers employers=iEmployersService.findEmployersByEmail(acc);
//        Cadidates cadidatesAccount=iCadidatesService.candidatesaccount(acc);
//        List<EmployersSearchCandidates> employersSearchCandidatesListFalse=iEmployersSearchCandidatesService.listStatusTrue(employers);
        List<EmployersSearchCandidates> employersSearchCandidatesListFalse=iEmployersSearchCandidatesService.listStatusAndEmployer(employers);
        model.addAttribute("employersSearchCandidatesListFalse",employersSearchCandidatesListFalse);

        List<EmployersSearchCandidates> employersSearchCandidatesListTrue=iEmployersSearchCandidatesService.listStatusAndEmployerTrue(employers);
        model.addAttribute("employersSearchCandidatesListTrue",employersSearchCandidatesListTrue);
        return "employer/invitedCandidate";
    }
//    @GetMapping("/employer/receivedApplication")
//    public String  receivedApplication(){
//        return "employer/receivedApplication";
//    }
//    @GetMapping("/employer/employerIndexWhenLogin")
//    public String employerIndexWhenLogin(){
//        return "employer/employerIndexWhenLogin";
//    }


}
