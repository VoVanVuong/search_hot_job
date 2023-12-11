package edu.vku.searchjob.controller;

import edu.vku.searchjob.entity.*;
import edu.vku.searchjob.repository.IJobsRepository;
import edu.vku.searchjob.security.CustomUser;
import edu.vku.searchjob.security.CustomUserDetailService;
import edu.vku.searchjob.service.IAccountService;
import edu.vku.searchjob.service.ICategoriesService;
import edu.vku.searchjob.service.IEmployersService;
import edu.vku.searchjob.service.IJobsService;
import edu.vku.searchjob.validation.JobValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Controller
//@RequestMapping("/admin")
public class jobController {
    @Autowired
    private IJobsService iJobsService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private JobValidator jobValidator;
    @Autowired
    private ICategoriesService iCategoriesService;
    @Autowired
    private IJobsRepository iJobsRepository;
    @Autowired
    IEmployersService iEmployersService;


    //    @Autowired
//    CustomUserDetailService customUserDetailService;
    @Autowired
    public jobController(IJobsService iJobsService) {
        this.iJobsService = iJobsService;
    }

    @GetMapping("/admin/ListJob")
    public String listJob(Model model) {
        List<Jobs> jobList = iJobsService.findByDeleteFlagFalse();
        List<Jobs> jobsList = iJobsService.findByDeleteFlagFalse();
        model.addAttribute("jobList", jobList);
        return "Admin/jobTable";
    }

    @GetMapping("/admin/ListDeleteJob")
    public String listdeleteJob(Model model) {
        List<Jobs> jobList = iJobsService.findByDeleteFlagTrue();
        model.addAttribute("jobList", jobList);
        return "Admin/jobDeleteTable";
    }

    @GetMapping("/admin/jobs/deleteJobs/{id}")
    public String deleteJob(@PathVariable("id") int jobsID, RedirectAttributes redirectAttributes) {
        iJobsService.deleteJob(jobsID);
        redirectAttributes.addFlashAttribute("messages", "Job đã được chuyển vào thùng rác!");
        return "redirect:/admin/ListJob";
    }

    @GetMapping("/admin/jobs/undeleteJobs/{id}")
    public String undeleteJob(@PathVariable("id") int jobsID, RedirectAttributes redirectAttributes) {
        iJobsService.undeleteJob(jobsID);
        return "redirect:/admin/ListDeleteJob";
    }

    //    employer
    @GetMapping("/employer/listJob")
    public String listJobEmployer(Principal p, Model model) {
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        List<Jobs> jobsList = iJobsService.findByDeleteFlagFalse();
        List<Jobs> findAllId = iJobsService.findByAllAccountId(acc.getId());
        List<Jobs> filteredJobsList = jobsList.stream()
                .filter(findAllId::contains)
                .collect(Collectors.toList());
        model.addAttribute("findAllId", filteredJobsList);
        model.addAttribute("acc", acc);
        return "employer/jobsTable";
    }

    @GetMapping("/employer/job/deleteJob/{id}")
    public String deleteJobEmployer(@PathVariable("id") int jobsID, RedirectAttributes redirectAttributes) {
        iJobsService.deleteJob(jobsID);
        redirectAttributes.addFlashAttribute("messages", "Job đã được chuyển vào thùng rác!");
        return "redirect:/employer/listJob";
    }

    @GetMapping("/employer/addJob")
    public String addJob(Principal p, Model model, ModelMap modelMap) {

        List<Categories> categoriesList = iCategoriesService.findByDeleteFlagFalse();
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        model.addAttribute("acc", acc);
        model.addAttribute("job", new Jobs());
        modelMap.addAttribute("categoriesList", categoriesList);
        return "employer/addJobsTable";
    }

    @PostMapping("employer/addJob")
    public String addCategory(@Validated @ModelAttribute("jobs") Jobs jobs, BindingResult result, RedirectAttributes redirectAttributes, Principal p, Model model) {
     //   model.addAttribute("acc", "xin chao");
//        jobValidator.validate(jobs, result);
//        if (result.hasErrors()) {
////            redirectAttributes.addFlashAttribute("messages", "Jobs fails!");
//            return "/employer/addJobsTable";
//        }
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        //   jobs.setTotalCandidate(acc.getId());
        jobs.setDeleteFlag(false);
        jobs.setAccountId(acc.getId());
        redirectAttributes.addFlashAttribute("submitSuccess", true);
        iJobsRepository.save(jobs);
        //   iJobsService.saveJobs(jobs);
        return "redirect:/employer/listJob";

//        if (result.hasErrors()) {
//            return "employer/addJobsTable";
//        }
//        try {
//            iJobsService.saveJobs(jobs);
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("messages", "Jobs fails!");
//            return "redirect:/employer/addJob";
//        }
//
//        redirectAttributes.addFlashAttribute("messages", "Job is more successful!");
//
//        return "redirect:/employer/listJob";
    }

    @GetMapping("/employer/job/updateJob/{id}")
    public String updateEmJob(@PathVariable("id") int id, Model model) {
        List<Categories> categoriesList = iCategoriesService.findByDeleteFlagFalse();

        model.addAttribute("categoriesList", categoriesList);
        model.addAttribute("jobs", iJobsService.getJobsById(id));
        return "employer/updateJob";
    }

//    @PostMapping("/employer/job/updateJob/{id}")
//    public String updateCategory(@PathVariable int id, @RequestParam("name") String name,@RequestParam("address") String address,@RequestParam("totalCandidate") int totalCandidate,@RequestParam("salary") String salary,@RequestParam("deadline") String deadline,@RequestParam("benefit") String benefit,@RequestParam("desription") String desription) {
//     iJobsService.updateJob(id,name,address,totalCandidate,salary,deadline,benefit,desription);
//
//        return "redirect:/admin/ListCategory";
//    }
//    //lỗi
//@PostMapping("/employer/job/updateJob/{id}")
//public String updateCategory(@PathVariable int id,
//                             @RequestParam("name") String name,
//                             @RequestParam("address") String address,
//                             @RequestParam("totalCandidate") int totalCandidate,
//                             @RequestParam("salary") String salary,
//                             @RequestParam("deadline") String deadline,
//                             @RequestParam("categories") Categories categories,
//                             @RequestParam("benefit") String benefit,
//                             @RequestParam("desription") String desription) {
//    iJobsService.updateJob(id, name, address, totalCandidate, salary, deadline,categories, benefit, desription);
//    return "redirect:/employer/listJob"; // Assuming this is the correct URL for listing jobs
//}
    @GetMapping("/user/home")
    public String home(Model model, Principal p){

        List<Jobs> latestJobs = iJobsService.getLatestJobs();
        model.addAttribute("latestJobs", latestJobs);
        List<Employers> employersList = iEmployersService.finAll();
      model.addAttribute("employersList",employersList);
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        model.addAttribute("acc", acc);

        List<String> topCategories = iCategoriesService.getTop8Categories();
        model.addAttribute("topCategories", topCategories);
        return "user/home";
    }
    @GetMapping("/user/listJob")
    public String listJobUser(Model model){
        List<Jobs> listJob=iJobsService.listJobUser();
        List<Employers> employersList = iEmployersService.finAll();
        model.addAttribute("employersList",employersList);
        List<Jobs> jobsListThree=iJobsService.findLatestThreeJobs();
        model.addAttribute("jobsListThree",jobsListThree);
        model.addAttribute("listJob",listJob);
        return "user/listJob";
    }
    @GetMapping("/user/jobDetail/{id}")
    public String jobDetailUser(@PathVariable("id") int id,Model model){
        Jobs job = iJobsService.getJobById(id);
        model.addAttribute("job", job);
        List<Employers> employersList = iEmployersService.finAll();
       model.addAttribute("employersList",employersList);

       model.addAttribute("candidateJob",new CandidateJobs());
//        Employers employer = iEmployersService.getEmployerByAccountId(job.getAccountId());
      //  List<Employers> employersList = iEmployersService.findByDeleteFlagFalse();


//        List<Employers> employersList=iEmployersService.findByDeleteFlagFalse();
//      job.getAccountId()
//        List<Jobs> listJob=iJobsService.listJobUser();
//        model.addAttribute("listJob",listJob);
        return "user/jobDetail";
    }
    @PostMapping ("/employer/job/updateJob")
    public String updateJob(@RequestParam("id") int id, @RequestParam("name") String name,@RequestParam("address") String address,@RequestParam("totalCandidate") int totalCandidate,@RequestParam("salary") String salary,@RequestParam("deadline") String deadline,@RequestParam("benefit") String benefit,@RequestParam("desription")String desription) {

        //public Jobs updateJob(int id, String name, String address,int totalCandidate,String salary,String deadline,String benefit,String desription)

        iJobsService.updateJobs(id,name,address,totalCandidate,salary,deadline,benefit,desription);
        return "employer/jobsTable";
    }

//  @GetMapping("/home")
//    public String guestHome(Model model){
//      List<Jobs> latestJobs = iJobsService.getLatestJobs();
//      model.addAttribute("latestJobs", latestJobs);
//      List<Employers> employersList = iEmployersService.finAll();
//      model.addAttribute("employersList",employersList);
//      List<String> topCategories = iCategoriesService.getTop8Categories();
//      model.addAttribute("topCategories", topCategories);
//      return "user/home";
//  }
}
//    @PostMapping("/employer/job/edit/{id}")
//    public String updateJob(@PathVariable int id, @ModelAttribute Jobs updatedJob) {
//        // Lấy công việc hiện tại từ cơ sở dữ liệu
//        Optional<Jobs> existingJob = iJobsService.getJobsById(id);
//        List<Categories> categoriesList = iCategoriesService.findByDeleteFlagFalse(); // Lấy danh sách categories
//
//        if (existingJob.isPresent()) {
//            // Cập nhật thông tin công việc
//            Jobs jobToUpdate = existingJob.get();
//            jobToUpdate.setName(updatedJob.getName());
//            jobToUpdate.setAddress(updatedJob.getAddress());
//            jobToUpdate.setTotalCandidate(updatedJob.getTotalCandidate());
//            jobToUpdate.setSalary(updatedJob.getSalary());
//            jobToUpdate.setDeadline(updatedJob.getDeadline());
//            Optional<Categories> selectedCategory = categoriesList.stream()
//                    .filter(category -> category.getId() == updatedJob.getCategories().getId())
//                    .findFirst();
//
//            selectedCategory.ifPresent(jobToUpdate::setCategories);
//
//            jobToUpdate.setBenefit(updatedJob.getBenefit());
//            jobToUpdate.setDesription(updatedJob.getDesription());
//            // Lưu công việc đã cập nhật vào cơ sở dữ liệu
//            iJobsService.saveJob(jobToUpdate);
//            return "redirect:/employer/listJob"; // Chuyển hướng về trang danh sách công việc sau khi cập nhật
//        } else {
//            // Xử lý nếu công việc không tồn tại
//            return "error";
//        }
//    }
//}

//@GetMapping("/admin/editCategory/{id}")
//public String showEditCategoryForm(@PathVariable int id, Model model) {
//    Categories category = iCategoriesService.getCategoryById(id);
//    model.addAttribute("category", category);
//    return "admin/editCategory";
//}
//
////    @PostMapping("/admin/updateCategory/{id}")
////    public String updateCategory(@PathVariable int id, @RequestParam("name") String name) {
////        iCategoriesService.updateCategoryName(id, name);
////        return "redirect:/admin/ListCategory";
////    }

