package edu.vku.searchjob.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Cadidates;
import edu.vku.searchjob.entity.Categories;
import edu.vku.searchjob.entity.Employers;
import edu.vku.searchjob.repository.ICadidatesRepository;
import edu.vku.searchjob.service.IAccountService;
import edu.vku.searchjob.service.ICadidatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Service
public class CadidatesService implements ICadidatesService {
    @Autowired
    private ICadidatesRepository iCadidatesRepository;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private Cloudinary cloudinary;
    @Override
    public List<Cadidates> finAll(){
        return iCadidatesRepository.findAll();
    }
//    @Override
//    public Cadidates finByAcId(int id){
//        return iCadidatesRepository.findBy(id);
//    }
@Override
public Cadidates finByAccount(Account id){
    return iCadidatesRepository.findByAccount(id);
}
    @Override
    public Cadidates save(Cadidates cadidates) {
        return iCadidatesRepository.save(cadidates);
    }
    @Override
    public Cadidates getCandidateUserById(int id) {
        return iCadidatesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + id));
    }
//    @Override
//    public Cadidates updateCandidate(Cadidates cadidates){
//        return iCadidatesRepository.save(cadidates);
//    }
    @Override
    public Cadidates updateCandidate(int id, String skill, String dateOfBirth, int phoneNumber, String address, String gender,MultipartFile candidateCv,String cadidatedName,String work,String categotyRequired,String experience,String salaryRequired){
//        String userEmail = p.getName();
       // Account account = iAccountService.finByEmail(userEmail);
     //  List<Cadidates> cadidatesList=iCadidatesRepository.findAll();
//        for (Cadidates cadidate : cadidatesList) {
//            if (cadidate.getAccount().equals(account..)) {
//                Long cadidateId = cadidate.getId();
//                System.out.println("Matching Cadidate ID: " + cadidateId);
//                // Thực hiện các hành động khác nếu cần
//            }
 Cadidates existingCadidates=iCadidatesRepository.findById(id).orElse(null);
 if(existingCadidates !=null){
     existingCadidates.setId(id);
     existingCadidates.setSkill(skill);
     existingCadidates.setDateOfBirth(dateOfBirth);
     existingCadidates.setPhoneNumber(phoneNumber);
     existingCadidates.setAddress(address);
     existingCadidates.setGender(gender);
//     ,String categotyRequired,String experience,String salaryRequired
     existingCadidates.setCategotyRequired(categotyRequired);
     existingCadidates.setExperience(experience);
     existingCadidates.setSalaryRequired(salaryRequired);
     existingCadidates.setCadidatedName(cadidatedName);
     existingCadidates.setWork(work);
     try {
         // Upload file ảnh lên Cloudinary
         Map uploadResult = cloudinary.uploader().upload(candidateCv.getBytes(), ObjectUtils.emptyMap());

         String imageUrl = (String) uploadResult.get("url");
         existingCadidates.setCandidateCV(imageUrl);
        // iAccountService.save(account);
         // newCandidateJobs.setImageUrl(imageUrl);
         //            byte[] avatarData = avatarFile.getBytes();
//            account.setAvatarData(avatarData);
//            iAccountService.save(account);
      //   model.addAttribute("success", "Avatar added successfully!");
     } catch (IOException e) {
         e.printStackTrace();
   //      model.addAttribute("error", "Failed to add avatar");
     }

     return iCadidatesRepository.save(existingCadidates);
 }
 return null;
    }
    @Override
    public List<Cadidates> search(String work, String experience, String categoryRequired, String salaryRequired) {

        return iCadidatesRepository.findByAttributes(work, experience, categoryRequired, salaryRequired);
    }
    @Override
    public Cadidates candidatesaccount(Account account){
        return iCadidatesRepository.findByCandidateAccount(account);
    }
//

//      Categories existingCategory = iCategoryRepository.findById(id).orElse(null);
//            if (existingCategory != null) {
//                existingCategory.setName(name);
//                return iCategoryRepository.save(existingCategory);
//            }
////        }
//        return null;
}
