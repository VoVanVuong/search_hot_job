package edu.vku.searchjob.controller;

import com.cloudinary.utils.ObjectUtils;
import edu.vku.searchjob.entity.*;
import edu.vku.searchjob.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class saveJobController {
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IEmployersService iEmployersService;
    @Autowired
    private IJobsService iJobsService;
    @Autowired
    private ICadidatesService iCadidatesService;
    @Autowired
    private ISaveJobService iSaveJobService;
    @GetMapping ("/user/saveJob/{id}")
    public String applyJob(@PathVariable("id") int id, Principal p,Model model){

//            String email = p.getName();
//            Account acc = iAccountService.finByEmail(email);
//            List<Jobs> latestJobs = iJobsService.getLatestJobs();
//            Cadidates canCadidates=iCadidatesService.finByAccount(acc);
//            Jobs jobs=iJobsService.getJobById(id);
//            //    System.err.println(canCadidates.getId()+"dsdj"+jobs.getId());
////            CandidateJobs newCandidateJobs = new CandidateJobs();
//            SaveJob newSaveJob=new SaveJob();
//             newSaveJob.setJob(jobs);
//             newSaveJob.setCandidate(canCadidates);
//             newSaveJob.setDeleteFlag(false);
//
//       iSaveJobService.saveJob(newSaveJob);
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        List<Jobs> latestJobs = iJobsService.getLatestJobs();
        Cadidates canCadidates = iCadidatesService.finByAccount(acc);
        Jobs jobs = iJobsService.getJobById(id);

        // Check if the SaveJob already exists
        SaveJob existingSaveJob = iSaveJobService.findExistingSaveJob(canCadidates, jobs);

        if (existingSaveJob == null) {
            // Save a new SaveJob only if it doesn't exist
            SaveJob newSaveJob = new SaveJob();
            newSaveJob.setJob(jobs);
            newSaveJob.setCandidate(canCadidates);
            newSaveJob.setDeleteFlag(false);
//            iSaveJobService.saveJob(newSaveJob);
            try {
                iSaveJobService.saveJob(newSaveJob);
                model.addAttribute("successMessage", "Lưu thành công");
            } catch (Exception e) {
                model.addAttribute("errorMessage", "Lưu thất bại");
            }
        } else {
            // SaveJob already exists
            model.addAttribute("errorMessage", "Lưu thất bại. SaveJob đã tồn tại");
        }
            return "redirect:/user/saveJob";

    }
    @GetMapping("/user/saveJob")
    public String saveJob(Model model){
        List<SaveJob> listSaveJob=iSaveJobService.findByDeleteFlagFalse();
        model.addAttribute("listSaveJob",listSaveJob);
        List<Employers> employersList = iEmployersService.finAll();
        model.addAttribute("employersList",employersList);
        List<Jobs> jobsListThree=iJobsService.findLatestThreeJobs();
        model.addAttribute("jobsListThree",jobsListThree);
//        List<Jobs> listJob=iJobsService.listJobUser();
//        List<Employers> employersList = iEmployersService.finAll();
//        model.addAttribute("employersList",employersList);
//        List<Jobs> jobsListThree=iJobsService.findLatestThreeJobs();
//        model.addAttribute("jobsListThree",jobsListThree);
//        model.addAttribute("listJob",listJob);
        return "user/saveJob";
    }
    @GetMapping("/user/deleteSaveJob/{id}")
    public String deleteJob(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
      //  iJobsService.deleteJob(id);
        iSaveJobService.deleteSaveJob(id);
        redirectAttributes.addFlashAttribute("messages", "Unsaved work!");
        return "redirect:/user/saveJob";
    }
}
