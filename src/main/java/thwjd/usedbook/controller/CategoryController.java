package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import thwjd.usedbook.annotation.Login;
import thwjd.usedbook.entity.BookCategory;
import thwjd.usedbook.entity.BookPost;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.repository.BookPostRepositoryMapper;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired BookPostRepositoryMapper bookPostMapper;

    @GetMapping("/{param}")
    public String category(@PathVariable String param, Model model){

        List<BookPost> categoryAll = bookPostMapper.findByCategory(param);
        model.addAttribute("lists", categoryAll);
        model.addAttribute("categoryName", param);
        String categoryNameKor = BookCategory.valueOf(param.toUpperCase()).getName();
        model.addAttribute("categoryNameKor", categoryNameKor);

        return "bookPost/list";
    }
}
