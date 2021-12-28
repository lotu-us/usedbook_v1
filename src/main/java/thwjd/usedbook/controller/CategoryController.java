package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thwjd.usedbook.annotation.Login;
import thwjd.usedbook.entity.BookCategory;
import thwjd.usedbook.entity.BookPost;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.entity.Pagination;
import thwjd.usedbook.repository.BookPostRepositoryMapper;
import thwjd.usedbook.service.BookPostService;

import java.awt.print.Book;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired BookPostRepositoryMapper bookPostMapper;
    @Autowired BookPostService bookPostService;

    @GetMapping("/{categoryName}")
    public String category(@PathVariable String categoryName, @ModelAttribute Pagination pagination, Model model){

        pagination.setCategory(categoryName);
        pagination.setMaxPageNum(bookPostMapper.findByCategoryCount(pagination.getCategory()));

        if(pagination.getPage() < 1){
            pagination.setPage(1);
        }
        if(pagination.getPage() > pagination.getMaxPageNum()){
            pagination.setPage(pagination.getMaxPageNum());
        }

        pagination.setOffset(pagination.getPage());
        //log.info("pagination={}", pagination);

        List<BookPost> lists = bookPostMapper.findByPagination(pagination);
        model.addAttribute("lists", lists);
        model.addAttribute("preview", pagination.getPage()-1);
        model.addAttribute("next", pagination.getPage()+1);

        return "bookPost/list";
    }
}
