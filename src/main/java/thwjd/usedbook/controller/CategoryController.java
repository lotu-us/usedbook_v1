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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired BookPostRepositoryMapper bookPostMapper;
    @Autowired BookPostService bookPostService;

    @GetMapping("/{categoryName}")
    public String category(@PathVariable String categoryName, @ModelAttribute Pagination pagination, Model model){

        //log.info("order={}", pagination.getOrder());
        String redirectUrl = bookPostService.pageProcess(categoryName, pagination);
        if(redirectUrl != null){
            return "redirect:/"+redirectUrl;
        }

        List<BookPost> lists = bookPostMapper.findByPaginationAndSearch(pagination);
        model.addAttribute("lists", lists);

        return "bookPost/list";
    }

    @GetMapping("/listOrder")
    //@ResponseBody
    public String listOrder(@RequestParam String categoryName, @ModelAttribute Pagination pagination, Model model){

        //log.info("order={}", pagination.getOrder());
        bookPostService.pageProcess(categoryName, pagination);

        List<BookPost> lists = bookPostMapper.findByPaginationAndSearch(pagination);
        model.addAttribute("lists", lists);

        return "bookPost/list :: #listTable";
    }

}
