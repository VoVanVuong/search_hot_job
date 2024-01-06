package edu.vku.searchjob.service.impl;
//import edu.vku.searchjob.entity.AccountRole;
//import edu.vku.searchjob.repository.IAccountRoleRepository;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import edu.vku.searchjob.entity.Account;
//import edu.vku.searchjob.entity.AppRole;
import edu.vku.searchjob.repository.IAccountRepository;
import edu.vku.searchjob.service.IAccountService;
//import edu.vku.searchjob.service.IAppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import com.cloudinary.Cloudinary;
@Service
public class AccountService implements IAccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private Cloudinary cloudinary;
//    @Autowired
//    private IAccountRoleRepository iAccountRoleRepository;
//    @Autowired
//    private IAppRoleService iAppRoleService;
    @Override
    public List<Account> findByDeleteFlagFalse() {
        return iAccountRepository.findByIsEnabledFalse();
    }

    @Override
    public List<Account> findByDeleteFlagTrue() {
        return iAccountRepository.findByIsEnabledTrue();
    }

    @Override
    public void deleteAccount(int accountID) {
        Optional<Account> accountOptional = iAccountRepository.findById(accountID);
        if ( accountOptional.isPresent()) {
           Account account =  accountOptional.get();
            account.setEnabled(true);
            iAccountRepository.save(account );
        }
    }

    @Override
    public void undeleteAccount(int accountID) {
        Optional<Account> accountOptional = iAccountRepository.findById(accountID);
        if ( accountOptional.isPresent()) {
            Account account =  accountOptional.get();
            account.setEnabled(false);
            iAccountRepository.save(account );
        }
    }
    @Override
    public Optional<Account> findAccountByUserName(String username) {
        return iAccountRepository.findAccountByUserName(username);
    }
    @Override
    public boolean isEmailExists(String email) {
        return iAccountRepository.existsByEmail(email);
    }
    @Override
    public Account registerAccount(Account account) {
        // Mã hóa mật khẩu và lưu vào tài khoản

//        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setEnabled(false);
        account.setRole("ROLE_USER");
        // Lưu tài khoản vào cơ sở dữ liệu
        Account savedAccount = iAccountRepository.save(account);
        // Gán vai trò mặc định cho tài khoản
//        AppRole defaultRole = iAppRoleService.getDefaultRole();
//        AccountRole accountRole = new AccountRole(savedAccount, defaultRole);
//        iAccountRoleRepository.save(accountRole);
        return savedAccount;
    }
    @Override
    public Account registerEmployerAccount(Account account) {
        // Mã hóa mật khẩu và lưu vào tài khoản
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setEnabled(true);
        account.setRole("ROLE_EMPLOYER");
        // Lưu tài khoản vào cơ sở dữ liệu
        Account savedAccount = iAccountRepository.save(account);
        return savedAccount;
    }
    @Override
    public String generateRandomNumber() {
        // Khởi tạo một đối tượng Random
        Random random = new Random();

        // Tạo một số ngẫu nhiên từ 100000 đến 999999 (có 6 chữ số)
        int randomNumber = random.nextInt(900000) + 100000;

        // Chuyển số nguyên thành chuỗi
        String randomString = String.valueOf(randomNumber);

        return randomString;
    }
    @Override
    public void updatePassword(String email, String newPassword) {
        // Truy vấn người dùng theo email
        Account account = iAccountRepository.findByEmail(email);

        if (account != null) {
            // Thay đổi mật khẩu
            account.setPassword(newPassword);
            // Lưu thay đổi vào cơ sở dữ liệu
            iAccountRepository.save(account);
        }
    }
//    @Override
//    @Transactional
//    public void updatePrivacyField(int accountId) {
//        Account account = iAccountRepository.findById(accountId).orElse(null);
//        if (account != null) {
//            account.setPrivacy("1234"); // Cập nhật trường privacy thành "1234"
//             // Lưu lại bản ghi cập nhật
//        }
//    }
//    @Override
//@Transactional
//@Modifying
//@Query("UPDATE Account a SET a.privacy = '1235' WHERE a.email = :email")
//public void updatePrivacyByEmail(String email) {
//    iAccountRepository.updatePrivacy(email);
//}
    @Override
public boolean isPrivacyValid(String enteredPrivacy) {
    // Thực hiện kiểm tra mã privacy với dữ liệu trong cơ sở dữ liệu
    // Trả về true nếu hợp lệ, false nếu không hợp lệ
    Account account = iAccountRepository.findByPrivacy(enteredPrivacy);
    return account != null;
}
@Override
public Account finByEmail(String email){
  return   iAccountRepository.findByEmail(email);
}

    @Override
    public void changePassword(Account account, String newPassword) {
        account.setPassword(passwordEncoder.encode(newPassword)); // Đảm bảo mã hóa mật khẩu trước khi lưu
        iAccountRepository.save(account);
    }
    @Override
    public void changeNowPassword(String email, String currentPassword, String newPassword) {
        Account account = iAccountRepository.findByEmail(email);

        if (account != null && passwordEncoder.matches(currentPassword, account.getPassword())) {
            account.setPassword(passwordEncoder.encode(newPassword));
            iAccountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Current password is incorrect");
        }
    }
    @Override
    public void save(Account account) {
        iAccountRepository.save(account);
    }
//    @Override
//    public Integer getUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.getPrincipal() instanceof CustomUser) {
//            CustomUser customUser = (CustomUser) authentication.getPrincipal();
//            return customUser.getId();
//        }
//        // Nếu không thể lấy ID từ UserDetails, bạn có thể thử các cách khác tùy thuộc vào cách bạn cấu hình Spring Security.
//        return null;
//    }

    @Override
    public Account updateAvatar(int id, MultipartFile avatar){
//        Cadidates existingCadidates=iCadidatesRepository.findById(id).orElse(null);
        Account accountAvatar=iAccountRepository.findById(id).orElse(null);
        if(accountAvatar !=null){
            accountAvatar.setId(id);
            try {
                Map uploadResult = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());

                String imageUrl = (String) uploadResult.get("url");
                accountAvatar.setAvatar(imageUrl);
//                        setCandidateCV(imageUrl);
            }catch (IOException e) {
         e.printStackTrace();

     }
            return iAccountRepository.save(accountAvatar);
//                    iCadidatesRepository.save(existingCadidates);
        }
        return null;
    }
}
//try {
//         // Upload file ảnh lên Cloudinary
//         Map uploadResult = cloudinary.uploader().upload(candidateCv.getBytes(), ObjectUtils.emptyMap());
//
//         String imageUrl = (String) uploadResult.get("url");
//         existingCadidates.setCandidateCV(imageUrl);
//        // iAccountService.save(account);
//         // newCandidateJobs.setImageUrl(imageUrl);
//         //            byte[] avatarData = avatarFile.getBytes();
////            account.setAvatarData(avatarData);
////            iAccountService.save(account);
//      //   model.addAttribute("success", "Avatar added successfully!");
//     } catch (IOException e) {
//         e.printStackTrace();
//   //      model.addAttribute("error", "Failed to add avatar");
//     }
//
//     return iCadidatesRepository.save(existingCadidates);
// }
// return null;
//    }
