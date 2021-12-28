package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thwjd.usedbook.entity.BookPost;
import thwjd.usedbook.entity.Pagination;
import thwjd.usedbook.repository.BookPostRepositoryMapper;
import thwjd.usedbook.service.BookPostService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired BookPostRepositoryMapper bookPostMapper;
    @Autowired BookPostService bookPostService;

    @GetMapping("/{categoryName}")
    public String category(@PathVariable String categoryName, @ModelAttribute Pagination pagination, Model model){

        String redirectUrl = bookPostService.pageInit(categoryName, pagination);
        if(redirectUrl != null){
            return "redirect:/"+redirectUrl;
        }

        List<BookPost> lists = bookPostMapper.findByPagination(pagination);
        model.addAttribute("lists", lists);
        model.addAttribute("preview", pagination.getPage()-1);
        model.addAttribute("next", pagination.getPage()+1);

        return "bookPost/list";
    }

    @PostMapping("/listOrder")
    @ResponseBody
    public List listOrder(@ModelAttribute Pagination pagination){
        List<BookPost> lists = bookPostMapper.findByPagination(pagination);
        return null;
    }
}
