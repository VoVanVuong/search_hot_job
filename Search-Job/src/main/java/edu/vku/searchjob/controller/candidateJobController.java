package edu.vku.searchjob.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import edu.vku.searchjob.entity.*;
import edu.vku.searchjob.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class candidateJobController {
    @Autowired
    private IJobsService iJobsService;
    @Autowired
    private ICandidateJobsService iCandidateJobsService;
    @Autowired
    private ICategoriesService iCategoriesService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private ICadidatesService iCadidatesService;
    @Autowired
    private IEmployersService iEmployersService;
    @Autowired
    private Cloudinary cloudinary;
    @GetMapping("/employer/jobDetails/{id}")
    public String getJobDetails(@PathVariable int id, Model model,Principal p) {
//        Optional<Jobs> job = iJobsService.getJobsById(id); // You need to implement this method in JobService
        List<CandidateJobs> candidateJobs = iCandidateJobsService.getCandidateJobsForJob(id);
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        model.addAttribute("acc", acc);
//        model.addAttribute("job", job);
        model.addAttribute("candidateJobs", candidateJobs);

        return "employer/receivedApplication"; // Thymeleaf template name without the extension
    }
    @GetMapping("/employer/jobDetails/applyEmployer/{id}")
    public String applyEmployer(@PathVariable("id") int id, RedirectAttributes redirectAttributes,Principal p,Model model) {
  //      iEmployersService.deleteEmployers(id);
       iCandidateJobsService.applyCandidateJobs(id);
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        model.addAttribute("acc", acc);
//        iCandidateJobsService.findByJobsId(id);
//        Optional<CandidateJobs> canJob=iCandidateJobsService.findById(id);
        CandidateJobs canJob=iCandidateJobsService.finByIdCanJob(id);
       // System.err.println("id"+ canJob.getId() +"idJob"+canJob.getJob().getId());
        redirectAttributes.addFlashAttribute("messages", "Have applied successfully!");
        return "redirect:/employer/jobDetails/"+ (canJob.getJob().getId());
    }
    @PostMapping("/user/applyJob/{id}")
    public String applyJob(@PathVariable("id") int id, @RequestParam("emailUserApply") String emailUserApply,  @RequestParam("file") MultipartFile file, @RequestParam("note") String note, @RequestParam("phone") String phone, Principal p){
        try {
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        List<Jobs> latestJobs = iJobsService.getLatestJobs();
        Cadidates canCadidates=iCadidatesService.finByAccount(acc);
        Jobs jobs=iJobsService.getJobById(id);
    //    System.err.println(canCadidates.getId()+"dsdj"+jobs.getId());
        CandidateJobs newCandidateJobs = new CandidateJobs();

        newCandidateJobs.setJob(jobs);
        newCandidateJobs.setEmailUserApply(emailUserApply);
        newCandidateJobs.setNote(note);
        newCandidateJobs.setPhone(phone);
        newCandidateJobs.setNameCandidate(canCadidates.getAccount().getName());
        newCandidateJobs.setCandidate(canCadidates);
        newCandidateJobs.setStatus(false);

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("url");
        newCandidateJobs.setCv(imageUrl);

        iCandidateJobsService.save(newCandidateJobs);
        return "redirect:/user/appliedJob";
    } catch (IOException e) {
        e.printStackTrace();
        // Xử lý lỗi tại đây nếu cần
        return "error";
    }
    }

//    @GetMapping("/user/saveJob/{id}")
//    public String saveJobUser(@PathVariable("id") int id,  CandidateJobs candidateJobs, Principal p, Model model){
//        String email = p.getName();
//        Account acc = iAccountService.finByEmail(email);
//
//        // candidateJobs.setCandidate();
//        List<Jobs> latestJobs = iJobsService.getLatestJobs();
//
//        Jobs jobs=iJobsService.getJobById(id);
//        model.addAttribute("latestJobs", latestJobs);
//
//        List<String> topCategories = iCategoriesService.getTop8Categories();
//    //    candidateJobs.setJob(latestJobs.get(id));
//    //    System.err.println(latestJobs.get(id));
//
//        Cadidates canCadidates=iCadidatesService.finByAccount(acc);
//        model.addAttribute("topCategories", topCategories);
//       candidateJobs.setCandidate(canCadidates);
//       candidateJobs.setJob(jobs);
//       candidateJobs.setStatus(false);
//       iCandidateJobsService.save(candidateJobs);
//        return "redirect:/user/home";
//    }
    @GetMapping("/user/appliedJob")
    public String appliedJob(Model model,Principal p){
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        Cadidates canCadidates=iCadidatesService.finByAccount(acc);
       // List<CandidateJobs> candidateJobsList=iCandidateJobsService.finAll();


        List<Jobs> jobsListThree=iJobsService.findLatestThreeJobs();
        model.addAttribute("jobsListThree",jobsListThree);
        List<CandidateJobs> candidateJobsList=iCandidateJobsService.candidateList(canCadidates);
        model.addAttribute("candidateJobsList",candidateJobsList);
        List<Employers> employersList = iEmployersService.finAll();
        model.addAttribute("employersList",employersList);
     // System.err.println("id"+acc.getId()+"ca"+canCadidates.getId() +"joca"+candidateJobsList);
        return "user/appliedJob";
    }

}
