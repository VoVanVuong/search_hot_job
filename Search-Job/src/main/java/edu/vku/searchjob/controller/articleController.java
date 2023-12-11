package edu.vku.searchjob.controller;

import edu.vku.searchjob.entity.Articles;
import edu.vku.searchjob.repository.IArticlesRepository;
import edu.vku.searchjob.service.IArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
//@RequestMapping("")
public class articleController {
    @Autowired
    private IArticlesService iArticlesService ;
    @GetMapping("/admin/listArticle")
    public String listArticle(Model model){
        List<Articles> listArticles = iArticlesService.findByDeleteFlagFalse();
        model.addAttribute("listArticles",listArticles);
        return "admin/articleTable";
    }
    @GetMapping("/admin/listDeleteArticle")
    public String listDeleteArticle(Model model){
        List<Articles> listArticles = iArticlesService.findByDeleteFlagTrue();
        model.addAttribute("listArticles",listArticles);
        return "admin/articleDeleteTable";
    }
    @GetMapping("/admin/article/deleteArticle/{id}")
    public String deleteArticle(@PathVariable("id") int articleID, RedirectAttributes redirectAttributes) {
        iArticlesService.deleteArticles(articleID);
        redirectAttributes.addFlashAttribute("messages", "Vaccine đã được chuyển vào thùng rác!");
        return "redirect:/admin/listArticle";
    }
    @GetMapping("/admin/article/unDeleteArticle/{id}")
    public String unDeleteArticle(@PathVariable("id") int articleID, RedirectAttributes redirectAttributes) {
        iArticlesService.undeleteArticles(articleID);
        redirectAttributes.addFlashAttribute("messages", "article đã được chuyển vào thùng rác!");
        return "redirect:/admin/listDeleteArticle";
    }

}
