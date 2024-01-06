package edu.vku.searchjob.controller;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Helps;
import edu.vku.searchjob.service.IAccountService;
import edu.vku.searchjob.service.IEmailService;
import edu.vku.searchjob.service.IHelpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class helpController {
    @Autowired
    private IHelpsService iHelpsService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private  IEmailService iEmailService;
@GetMapping("user/help")
public String helpUser(Model model, Principal p){
    String email=p.getName();
    Account account=iAccountService.finByEmail(email);
    model.addAttribute("acc",account);
    model.addAttribute("help" ,new Helps());
    List<Helps> helpsList=iHelpsService.findByAccount(account);
    model.addAttribute("helpsList",helpsList);
    return "user/helpsTable";
}
@PostMapping("user/help")
    public String addHelp(@ModelAttribute("help") Helps helps,Model model,Principal p){
    String email=p.getName();
    Account account=iAccountService.finByEmail(email);
    iEmailService.sendEmail(email, "Report","We have received your help, we will respond as soon as possible!");


    iHelpsService.addHelp(helps,account);
    return "redirect:/user/help";
}
//
@GetMapping("employer/help")
public String helpEmployer(Model model, Principal p){
    String email=p.getName();
    Account account=iAccountService.finByEmail(email);
    model.addAttribute("acc",account);
    model.addAttribute("help" ,new Helps());
    List<Helps> helpsList=iHelpsService.findByAccount(account);
    model.addAttribute("helpsList",helpsList);
    return "employer/helpsEmployTable";
}
    @PostMapping("employer/help")
    public String addHelpEmployer(@ModelAttribute("help") Helps helps,Model model,Principal p){
        String email=p.getName();
        Account account=iAccountService.finByEmail(email);
        iEmailService.sendEmail(email, "Report","We have received your help, we will respond as soon as possible!");


        iHelpsService.addHelp(helps,account);
        return "redirect:/employer/help";
    }
//    @PostMapping("admin/help")
//    public String addHelpAdmin(@ModelAttribute("help") Helps helps,Model model,Principal p){
//        String email=p.getName();
//        Account account=iAccountService.finByEmail(email);
//        iEmailService.sendEmail(email, "Report","We have received your help, we will respond as soon as possible!");
//
//
//        iHelpsService.addHelp(helps,account);
//        return "redirect:/admin/help";
//    }
@PostMapping("admin/help")
public String addHelpAdmin(@RequestParam("id") int id,@RequestParam("reply") String reply,Model model,Principal p){
    String email=p.getName();
    Account account=iAccountService.finByEmail(email);
    //iEmailService.sendEmail(email, "Report","We have received your help, we will respond as soon as possible!");
iHelpsService.updateHelp(id,reply);

//    iHelpsService.addHelp(helps,account);
    return "redirect:/admin/help";
}
//    @PostMapping ("/employer/job/updateJob")
//    public String updateJob(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("address") String address, @RequestParam("totalCandidate") int totalCandidate, @RequestParam("salary") String salary, @RequestParam("deadline") String deadline, @RequestParam("benefit") String benefit, @RequestParam("desription")String desription) {
//
//        //public Jobs updateJob(int id, String name, String address,int totalCandidate,String salary,String deadline,String benefit,String desription)
//
//        iJobsService.updateJobs(id,name,address,totalCandidate,salary,deadline,benefit,desription);
//        return "employer/jobsTable";
//    }

    @GetMapping("admin/help")
    public String helpAdmin(Model model,Principal p){
        model.addAttribute("helpss" ,new Helps());
    String email=p.getName();
    Account account=iAccountService.finByEmail(email);
    model.addAttribute("acc",account);
    List<Helps> listHelps=iHelpsService.findByAll();
    model.addAttribute("listHelps",listHelps);
    return "admin/helpTable";
    }
//@GetMapping("employer/help")
//    @GetMapping("/admin/addCategory")
//    public String addCategory(Model model){
//        model.addAttribute("category", new Categories());
//        return "Admin/addCategory";
//    }
//
//    @PostMapping("/admin/addCategory")
//    public String addCategory(@Validated @ModelAttribute(("category")) Categories category, BindingResult result,Model model) {
//
//        categoryValidator.validate(category, result);
//        if (result.hasErrors()) {
//
//            return "/admin/addCategory";
//        }
//        iCategoriesService.addCategory(category);
//        model.addAttribute("sussues","cập nhật thành công category");
//        return "redirect:/admin/ListCategory";
//    }
}
