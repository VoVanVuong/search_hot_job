package edu.vku.searchjob.controller;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Categories;
import edu.vku.searchjob.repository.IAccountRepository;
import edu.vku.searchjob.service.ICategoriesService;
import edu.vku.searchjob.validation.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

//@RequestMapping("/admin")
@Controller
public class categoryController {
    @Autowired
    private ICategoriesService iCategoriesService;
    @Autowired
     private CategoryValidator categoryValidator;
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    public categoryController(ICategoriesService iCategoriesService) {
        this.iCategoriesService = iCategoriesService;
    }
//@PreAuthorize("hasAuthority('Role_admin')")
    @GetMapping("/admin/ListCategory")
    public String listcategory(Principal p, Model model){

        List<Categories> categoriesList = iCategoriesService.findByDeleteFlagFalse();
        String email=p.getName();
        Account acc=iAccountRepository.findByEmail(email);
        model.addAttribute("categoriesList", categoriesList);
        model.addAttribute("acc",acc);
        return "Admin/categoryTable";
    }
    @GetMapping("/admin/ListDeleteCategory")
    public String listDeleteCategory(Model model){
        List<Categories> categoriesList = iCategoriesService.findByDeleteFlagTrue();

        model.addAttribute("categoriesList", categoriesList);
        return "Admin/categoryDeleteTable";
    }

//    @GetMapping("/markAsDeleted/{categoryId}")
//    public String markAsDeleted(@PathVariable int categoryId) {
//        iCategoriesService.markAsDeleted(categoryId);
//        return "Trạng thái đã được chuyển thành 1 thành công.";
//    }
@GetMapping("/admin/category/deleteCategory/{id}")
public String deleteCategory(@PathVariable("id") int categoryID, RedirectAttributes redirectAttributes) {
    iCategoriesService.deleteVaccine(categoryID);
    redirectAttributes.addFlashAttribute("messages", "đã chuyển  thành công vào thùng rác!");
    return "redirect:/admin/ListCategory";
}
    @GetMapping("/admin/category/unDeleteCategory/{id}")
    public String unDeleteCategory(@PathVariable("id") int categoryID, RedirectAttributes redirectAttributes) {
        iCategoriesService.unDeleteVaccine(categoryID);
        redirectAttributes.addFlashAttribute("messages", "đã chuyển  thành công vào thùng rác!");
        return "redirect:/admin/ListCategory";
    }
    @GetMapping("/admin/addCategory")
    public String addCategory(Model model){
        model.addAttribute("category", new Categories());
        return "Admin/addCategory";
    }

    @PostMapping("/admin/addCategory")
    public String addCategory(@Validated @ModelAttribute(("category")) Categories category, BindingResult result,Model model) {

        categoryValidator.validate(category, result);
        if (result.hasErrors()) {

            return "/admin/addCategory";
        }
        iCategoriesService.addCategory(category);
        model.addAttribute("sussues","cập nhật thành công category");
        return "redirect:/admin/ListCategory";
    }
    @GetMapping("/admin/editCategory/{id}")
    public String showEditCategoryForm(@PathVariable int id, Model model) {
        Categories category = iCategoriesService.getCategoryById(id);
        model.addAttribute("category", category);
        return "admin/editCategory";
    }

    @PostMapping("/admin/updateCategory/{id}")
    public String updateCategory(@PathVariable int id, @RequestParam("name") String name) {
        iCategoriesService.updateCategoryName(id, name);
        return "redirect:/admin/ListCategory";
    }
    //Employer
    @GetMapping("/employer/ListCategory")
    public String listCategory(){
        return "employer/categoryTable";
    }

}
