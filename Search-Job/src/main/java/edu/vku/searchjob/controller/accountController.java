package edu.vku.searchjob.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import edu.vku.searchjob.entity.*;
import edu.vku.searchjob.repository.IAccountRepository;
import edu.vku.searchjob.service.*;
import edu.vku.searchjob.validation.SignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class accountController {

    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private SignUpValidator signUpValidator;
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private ICadidatesService iCadidatesService;
    @Autowired
    private IEmployersService iEmployersService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IEmployersSearchCandidatesService iEmployersSearchCandidatesService;
    @Autowired
    private IEmailService iEmailService;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private IJobsService iJobsService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping("/signUp")
    public String signUp(Model model){
        model.addAttribute("account", new Account());
        model.addAttribute("candidate",new Cadidates());
        return "account/signUp";
    }

    @GetMapping("/signIn")
    public String signIn(){
        return "account/signIn";
    }
    @GetMapping("/admin/listAccount")
    public String listAccount(Model model){


//        List<Cadidates> listCadidates=iCadidatesService.finAll();
        List<Account> listAccount=iAccountService.findByDeleteFlagFalse();
        model.addAttribute("listAccount",listAccount);
        List<Employers> listEmployer=iEmployersService.finAll();
       model.addAttribute("listEmployer",listEmployer);

        return "admin/accountTable";
    }
//    @GetMapping("/admin/showInformationAccount/{id}")
//    public String showInformationAccount(Model model,@PathVariable("id") int id){
//        List<Account> listAccount=iAccountService.findByDeleteFlagFalse();
//        model.addAttribute("listAccount",listAccount);
//        return "admin/accountTable";
//    }
//      @GetMapping("/admin/account/deleteAccount/{id}")
//    public String deleteArticle(@PathVariable("id") int accountID, RedirectAttributes redirectAttributes) {
//        iAccountService.deleteAccount(accountID);
//        redirectAttributes.addFlashAttribute("messages", "Account đã được cập nhật!");
//        return "redirect:/admin/listAccount";
//    }
    @GetMapping("/logout")
    public String performLogout() {
        // Perform any additional actions or business logic here
        // For example, you might want to invalidate the session or clear user-related data

        return "redirect:/signIn"; // Redirect to a specific page after logout
    }
    @GetMapping("/admin/listDeleteAccount")
    public String listDeleteAccount(Model model){
        List<Account> listAccount=iAccountService.findByDeleteFlagTrue();
        model.addAttribute("listAccount",listAccount);
        return "admin/accountDeleteTable";
    }
    @GetMapping("/admin/account/deleteAccount/{id}")
    public String deleteArticle(@PathVariable("id") int accountID, RedirectAttributes redirectAttributes) {
        iAccountService.deleteAccount(accountID);
        redirectAttributes.addFlashAttribute("messages", "Account đã được cập nhật!");
        return "redirect:/admin/listAccount";
    }
    @GetMapping("/admin/account/unDeleteAccount/{id}")
    public String unDeleteArticle(@PathVariable("id") int accountID, RedirectAttributes redirectAttributes) {
        iAccountService.undeleteAccount(accountID);
        redirectAttributes.addFlashAttribute("messages", "Account đã được cập nhật!");
        return "redirect:/admin/listDeleteAccount";
    }
    @PostMapping("/signUp")
    public String registerAccount(@Validated @ModelAttribute("account") Account account, @Param("password") String password, @ModelAttribute("cadidates") Cadidates cadidates, Model model, BindingResult result) {
        String email = account.getEmail();
        if (iAccountService.isEmailExists(email)) {
            // Email đã tồn tại, hiển thị thông báo lỗi
            model.addAttribute("error", "Registration failed email already exists");
            return "account/signUp";
        }
        signUpValidator.validate(account, result);
        if (result.hasErrors()) {
            model.addAttribute("success", "validate error");
            return "account/signUp";
        }
        account.setPassword(passwordEncoder.encode(password));
//       account.setPassword();
        iAccountService.registerAccount(account);
        cadidates.setAccount(iAccountService.registerAccount(account));
        iCadidatesService.save(cadidates);
        model.addAttribute("success", "Registered " + account.getName() + " account successfully Registered successfully ");
        return "account/signUp";
    }
    // quên password
    @GetMapping("/signIn/emailAuthentication")
    public String configPassword(Model model){
        model.addAttribute("account", new Account());
        return "account/emailAuthentication";
    }

    @PostMapping("/signIn/emailAuthentication")
    public String configEmailPassword(@Validated @ModelAttribute("account") Account account,@RequestParam String email, Model model,  BindingResult result) {
        String emailAcc = account.getEmail();

        if (iAccountService.isEmailExists(emailAcc)) {
            Account existingAccount = iAccountRepository.findByEmail(emailAcc);
            if (existingAccount != null) {
                existingAccount.setPrivacy(iAccountService.generateRandomNumber());
                iAccountRepository.save(existingAccount); // Đảm bảo rằng bạn đang sử dụng save trên existingAccount

            iEmailService.sendEmail(email, "Send code private","Your private code is : "+existingAccount.getPrivacy()+" , please do not share this code with others!");
         //   System.err.println("email Acc"+emailAcc+existingAccount.getPassword()+"email nhập"+existingAccount.getPassword());
            model.addAttribute("success", "We have successfully sent the private to"+account.getEmail()+" please check your email!:");
               // model.addAttribute("email",account.getEmail());
            model.addAttribute("account", new Account());
           return "redirect:/forgotPass";
            }
        }
        model.addAttribute("error", "Email does not exist, please re-enter");
        return "account/emailAuthentication";
    }

    @PostMapping("/forgotPass")
    public String changePassword(@RequestParam String email, @RequestParam String privacyCode,
                                 @RequestParam String newPassword, Model model) {
        // Check if the email exists
        if (iAccountService.isEmailExists(email)) {
            Account existingAccount = iAccountRepository.findByEmail(email);
        System.err.println("email"+email);
            // Check if the privacy code matches
            if (existingAccount != null && privacyCode.equals(existingAccount.getPrivacy())) {
                // Valid privacy code, allow password change
                existingAccount.setPassword(passwordEncoder.encode(newPassword));
               // existingAccount.setPassword(newPassword);
                iAccountRepository.save(existingAccount);
                System.err.println("paaa"+newPassword);
                System.err.println("mã"+privacyCode);
                // Optional: You may want to invalidate the privacy code after use
                existingAccount.setPrivacy(null);
                iAccountRepository.save(existingAccount);

                model.addAttribute("success", "Password successfully changed!");
                return "account/signIn";
            } else {
                model.addAttribute("error", "Invalid privacy code. Please check your email for the correct code.");
                return "account/forgotPass";
            }
        } else {
            model.addAttribute("error", "Email does not exist. Please re-enter.");
            return "account/forgotPass";
        }
    }
//    @PostMapping("/employer/employerSettings")
//    public String changePassword(@RequestParam String currentPassword,
//                                 @RequestParam String newPassword,
//                                 Principal principal,
//                                 Model model) {
//
//        if (principal == null) {
//            // Xử lý khi không có người dùng đăng nhập
//            return "redirect:/login"; // Hoặc điều hướng đến trang đăng nhập
//        }
//
//        // Tiếp tục xử lý khi có người dùng đăng nhập
//        String userEmail = principal.getName();
//        Account account = iAccountService.finByEmail(userEmail);
//
//        if (account == null) {
//            // Xử lý khi không tìm thấy tài khoản
//            // Có thể log thông báo để theo dõi
//            return "redirect:/login"; // Hoặc điều hướng đến trang đăng nhập
//        }
//
//        // Kiểm tra mật khẩu hiện tại
//        if (!passwordEncoder.matches(currentPassword, account.getPassword())) {
//            model.addAttribute("error", "Current password is incorrect");
//            return "employer/employerSetting";
//        }
//
//        // Thực hiện thay đổi mật khẩu
//        iAccountService.changePassword(account, newPassword);
//        model.addAttribute("success", "Password changed successfully!");
//
//        return "employer/employerSetting";
//    }
    @GetMapping("/forgotPass")
    public String forgotpass(Model model){
        model.addAttribute("account",new Account());
        return "account/forgotPass";
    }
//    @PostMapping("/account/confirmNewPasswordsss")
//    public String confirmPrivacy(@RequestParam("privacy") String enteredPrivacy, Model model) {
//        // Thực hiện kiểm tra mã privacy
//        boolean isPrivacyValid = iAccountService.isPrivacyValid(enteredPrivacy);
//        if (isPrivacyValid) {
//            // Mã privacy hợp lệ, chuyển hướng đến trang đổi mật khẩu
//            return "redirect:/signIn";
//        } else {
//            // Mã privacy không hợp lệ, hiển thị thông báo lỗi
//            model.addAttribute("error", "Invalid privacy code");
//            return "redirect:/signUp";
//        }
//    }
//    public String configNewPassword(@Validated @ModelAttribute("account") Account account, @RequestParam("privacy") String privacy , Model model, BindingResult result) {
//        String enteredPrivacy = account.getPrivacy();
//     if (enteredPrivacy.equals(privacy)) {
//            // Privacy đúng, cập nhật mật khẩu (thay đổi Password ở đây)
//            String newPassword = "888456"; // Thay đổi thành mật khẩu mới
//            iAccountService.updatePassword(account.getEmail(), newPassword);
//            // Chuyển đến trang khác hoặc hiển thị thông báo cập nhật thành công
//            model.addAttribute("success", "Password updated successfully.");
//            return "account/signIn"; // Thay đổi thành trang bạn muốn hiển thị sau khi cập nhật mật khẩu
//        } else {
//            model.addAttribute("success", "Wrong privacy, please try again.");
//            return "account/fdfđf";
//        }
//    }

//    @GetMapping("/admin/addCategory")
//    public String addCategory(Model model){
//        model.addAttribute("category", new Categories());
//        return "Admin/addCategory";
//    }
@GetMapping("/admin/listUserDeleteAccount")
public String listUserDeleteAccount(Model model){
    List<Account> listAccount=iAccountService.findByDeleteFlagTrue();
    model.addAttribute("listAccount",listAccount);
    return "admin/accountUserDeleteTable";
}
    @GetMapping("/admin/listUserAccount")
    public String listUserAccount(Model model){
        List<Account> listAccount=iAccountService.findByDeleteFlagFalse();
        model.addAttribute("listAccount",listAccount);
        return "admin/accountUserTable";
    }
    @GetMapping("/admin/account/deleteUserAccount/{id}")
    public String deleteUserAccount(@PathVariable("id") int accountID, RedirectAttributes redirectAttributes) {
        iAccountService.deleteAccount(accountID);
        redirectAttributes.addFlashAttribute("messages", "Account đã được cập nhật!");
        return "redirect:/admin/listUserAccount";
    }
    @GetMapping("/admin/account/unDeleteUserAccount/{id}")
    public String unDeleteUserAccount(@PathVariable("id") int accountID, RedirectAttributes redirectAttributes) {
        iAccountService.undeleteAccount(accountID);
        redirectAttributes.addFlashAttribute("messages", "Account đã được cập nhật!");
        return "redirect:/admin/listUserDeleteAccount";
    }
    @GetMapping("/employRegister")
    public String employRegister(Model model){
        model.addAttribute("employer", new Employers());
        model.addAttribute("account", new Account());
        return "account/employRegister";
    }
    @PostMapping("/employRegister")
    public String  employRegisterAccount(@Validated @ModelAttribute("account") Account account,@Param("password") String password, @ModelAttribute("employer") Employers employer, Model model,  BindingResult result) {
        String email = account.getEmail();
        if (iAccountService.isEmailExists(email)) {
            // Email đã tồn tại, hiển thị thông báo lỗi
            model.addAttribute("error", "Registration failed email already exists");
            return "account/employRegister";
        }
        signUpValidator.validate(account, result);
        if (result.hasErrors()) {
            return "account/employRegister";
        }
//      Account imy=  iAccountService.registerEmployerAccount(account);
        account.setPassword(passwordEncoder.encode(password));
 iAccountService.registerAccount(account);
        employer.setAccount(iAccountService.registerAccount(account));
      employer.setDeleteFlag(true);
         iEmployersService.save(employer);
        model.addAttribute("success", "Registered " + account.getName() + " account successfully Registered successfully ");
        return "account/employRegister";
    }
    @GetMapping("/employer/employerSetting")
    public String employerSetting(Principal principal,Model model){
        model.addAttribute("email",principal.getName());
        //model.addAttribute("account",new Account());
        return "employer/employerSetting";
    }
//    @PostMapping("/employer/employerSettings")
//    public String changePassword(Authentication authentication,@RequestParam String currentPassword,@RequestParam String newPassword, Model model) {
//        String email = authentication.getName();
//        Account account = iAccountService.finByEmail(email);
//
//        if (account != null && account.getPassword().equals(currentPassword)) {
//            iAccountService.changePassword(account, newPassword);
//            model.addAttribute("success", "Password changed successfully!");
//        } else {
//            model.addAttribute("error", "Current password is incorrect"+authentication.getName()+iAccountService.finByEmail(email));
//        }
//
//        return "employer/employerSetting";
//    }
    @PostMapping("/employer/employerSettings")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 Principal principal,
                                 Model model) {

        if (principal == null) {
            // Xử lý khi không có người dùng đăng nhập
            return "redirect:/login"; // Hoặc điều hướng đến trang đăng nhập
        }

        // Tiếp tục xử lý khi có người dùng đăng nhập
        String userEmail = principal.getName();
        Account account = iAccountService.finByEmail(userEmail);

        if (account == null) {
            // Xử lý khi không tìm thấy tài khoản
            // Có thể log thông báo để theo dõi
            return "redirect:/login"; // Hoặc điều hướng đến trang đăng nhập
        }

        // Kiểm tra mật khẩu hiện tại
        if (!passwordEncoder.matches(currentPassword, account.getPassword())) {
            model.addAttribute("error", "Current password is incorrect");
            return "employer/employerSetting";
        }

        // Thực hiện thay đổi mật khẩu
        iAccountService.changePassword(account, newPassword);
        model.addAttribute("success", "Password changed successfully!");

        return "employer/employerSetting";
    }

    @GetMapping("/user/myAccount")
    public String myAccount(Model model, Principal p){
        model.addAttribute("email",p.getName());
        Account account = iAccountService.finByEmail(p.getName());
       Cadidates candidate = iCadidatesService.finByAccount(account);
       System.err.println("id"+account.getId()+"can"+candidate.getId()+"id accc"+candidate.getAccount().getId());
     //   System.err.println("account"+account.getId()+"candi"+candidate.+candidate.getPhoneNumber());
        model.addAttribute("candidate", candidate);
        model.addAttribute("account", account);
        return "user/myAccount";
    }

//    @GetMapping("/user/myAccountt/{id}")
//    public String showUpdateForm(@PathVariable int id, Model model) {
//        String userEmail = principal.getName();
//        Account account = iAccountService.finByEmail(userEmail);
//        Cadidates candidate = iCadidatesService.getCandidateUserById(id);
//        model.addAttribute("candidate", candidate);
//        return "update-candidate";
//    }
//    @PostMapping("/user/updateCandidate")
//    public String updateCandidate(@RequestParam("skill") String skill,@RequestParam("dateOfBirth") String dateOfBirth,@RequestParam("phoneNumber") int phoneNumber,@RequestParam("address") String address,@RequestParam("gender") String gender,Model model,Principal p){
//        model.addAttribute("email",p.getName());
//        iCadidatesService.updateCandidate(skill,dateOfBirth,phoneNumber,address,gender);
//     return "redirect:/user/myAccount";
//    }
//     @PostMapping("/admin/updateCategory/{id}")
//    public String updateCategory(@PathVariable int id, @RequestParam("name") String name) {
//        iCategoriesService.updateCategoryName(id, name);
//        return "redirect:/admin/ListCategory";
//    }
    @PostMapping("/user/changePasswordUser")
    public String changeAcount(Principal principal,@RequestParam String currentPassword, @RequestParam String newPassword,Model model,RedirectAttributes redirectAttributes){

        if (principal == null) {
            // Xử lý khi không có người dùng đăng nhập
            return "redirect:/login"; // Hoặc điều hướng đến trang đăng nhập
        }

        // Tiếp tục xử lý khi có người dùng đăng nhập
        String userEmail = principal.getName();
        Account account = iAccountService.finByEmail(userEmail);

        if (account == null) {
            // Xử lý khi không tìm thấy tài khoản
            // Có thể log thông báo để theo dõi
            return "redirect:/login"; // Hoặc điều hướng đến trang đăng nhập
        }
        if (!passwordEncoder.matches(currentPassword, account.getPassword())) {
//            model.addAttribute("error", "Current password is incorrect");
            redirectAttributes.addFlashAttribute("error", "Current password is incorrect!");

            return "redirect:/user/myAccount";
        }
        model.addAttribute("email",principal.getName());
        // Thực hiện thay đổi mật khẩu
        iAccountService.changePassword(account, newPassword);
//        model.addAttribute("success", "Password changed successfully!");
        redirectAttributes.addFlashAttribute("success", "Password changed successfully!");

        return "redirect:/user/myAccount";
    }
//    @PostMapping("/user/updateacc/{id}")
//@PostMapping("/user/updateCandidate/{id}")
//public String updateCandidate(@PathVariable int id, @RequestParam("skill") String skill, @RequestParam("dateOfBirth") String dateOfBirth, @RequestParam("phoneNumber") int phoneNumber, @RequestParam("address") String address, @RequestParam("gender") String gender,@RequestParam("cadidatedName") String cadidatedName,@RequestParam("work") String work,@RequestParam("candidateCv") MultipartFile candidateCv,@RequestParam("categotyRequired") String categotyRequired,@RequestParam("experience") String experience,@RequestParam("salaryRequired") String salaryRequired, Model model, Principal p,RedirectAttributes redirectAttributes){
//    redirectAttributes.addFlashAttribute("email",p.getName());
//    redirectAttributes.addFlashAttribute("success","Successfully updated information");
////            .addAttribute();
////        model.addAttribute("success","Successfully updated information");
//    // public Cadidates updateCandidate(int id,String skill, String dateOfBirth, int phoneNumber, String address, String gender)
//    iCadidatesService.updateCandidate(id,skill,dateOfBirth,phoneNumber,address,gender,candidateCv,cadidatedName,work,categotyRequired,experience,salaryRequired);
//    return "redirect:/user/myAccount";
//}
    @PostMapping("/user/updateAvatar/{id}")
    public String updateAvatar(@PathVariable int id,@RequestParam("avatar") MultipartFile avatar, Model model, Principal p,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("email",p.getName());
        redirectAttributes.addFlashAttribute("success","Successfully updated information");
//            .addAttribute();
//        model.addAttribute("success","Successfully updated information");
        // public Cadidates updateCandidate(int id,String skill, String dateOfBirth, int phoneNumber, String address, String gender)
        iAccountService.updateAvatar(id,avatar);
        return "redirect:/user/myAccount";
    }
    @GetMapping("/user/chatbot")
    public String chatbot(Model model,Principal p){
        String email=p.getName();
        Account account=iAccountService.finByEmail(email);
        model.addAttribute("account",account);
        return "user/chatBot";
    }

    @PostMapping("/employer/addAvatar")
    public String addAvatar(@RequestParam("avatar") MultipartFile avatar,
                            Principal principal,
                            Model model) {
        // Kiểm tra người dùng đăng nhập
        if (principal == null) {
            return "redirect:/login";
        }

        String userEmail = principal.getName();
        Account account = iAccountService.finByEmail(userEmail);

        if (account == null) {
            return "redirect:/login";
        }

        try {
            // Upload file ảnh lên Cloudinary
            Map uploadResult = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());

            String imageUrl = (String) uploadResult.get("url");
            account.setAvatar(imageUrl);
            iAccountService.save(account);
           // newCandidateJobs.setImageUrl(imageUrl);
            //            byte[] avatarData = avatarFile.getBytes();
//            account.setAvatarData(avatarData);
//            iAccountService.save(account);
            model.addAttribute("success", "Avatar added successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to add avatar");
        }

        return "employer/employerSetting";
    }
//    @GetMapping("admin/index")
//public String index(){
//        return "admin/index";
//}
    @GetMapping("employer/employerStatistics")
    public String employerStatistics(Model model,Principal p){
//        String userEmail = p.getName();
//        Account account = iAccountService.finByEmail(userEmail);
//        model.addAttribute("account",account);
        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);

        Employers employers=iEmployersService.findEmployersByEmail(acc);

        List<Jobs> jobsList = iJobsService.findByDeleteFlagFalse();
        List<Jobs> findAllId = iJobsService.findByAllAccountId(acc.getId());
        List<Jobs> filteredJobsList = jobsList.stream()
                .filter(findAllId::contains)
                .collect(Collectors.toList());
        model.addAttribute("findAllId", filteredJobsList);

        List<EmployersSearchCandidates> employersSearchCandidatesList=iEmployersSearchCandidatesService.listStatusAndEmployer(employers);
        model.addAttribute("employersSearchCandidatesList",employersSearchCandidatesList);

        List<EmployersSearchCandidates> employersSearchCandidatesListTrue=iEmployersSearchCandidatesService.listStatusAndEmployerTrue(employers);
        model.addAttribute("employersSearchCandidatesListTrue",employersSearchCandidatesListTrue);

        List<Jobs> jobsListTrue = iJobsService.findByDeleteFlagTrue();
        List<Jobs> findAllIdTrue = iJobsService.findByAllAccountId(acc.getId());
        List<Jobs> filteredJobsListTrue = jobsListTrue.stream()
                .filter(findAllIdTrue::contains)
                .collect(Collectors.toList());
        model.addAttribute("findAllIdTrue", filteredJobsListTrue);

        model.addAttribute("account", acc);
        return "employer/employerStatistics";
    }
    @GetMapping("admin/adminStatistics")
    public String adminStatistics(Model model,Principal p){
//        String userEmail = p.getName();
//        Account account = iAccountService.finByEmail(userEmail);
//        model.addAttribute("account",account);
//
//        Employers employers=iEmployersService.findByAccountId(account);

        String email = p.getName();
        Account acc = iAccountService.finByEmail(email);
        List<Jobs> jobsList = iJobsService.findByDeleteFlagFalse();
        List<Jobs> findAllId = iJobsService.findByAllAccountId(acc.getId());
        List<Jobs> filteredJobsList = jobsList.stream()
                .filter(findAllId::contains)
                .collect(Collectors.toList());
        model.addAttribute("findAllId", filteredJobsList);
        model.addAttribute("account", acc);
        return "admin/adminStatistics";
    }


}

