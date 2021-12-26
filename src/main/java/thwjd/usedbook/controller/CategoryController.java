package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import thwjd.usedbook.annotation.Login;
import thwjd.usedbook.entity.Member;

@Slf4j
@Controller
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("/{param}")
    public String category(@PathVariable String param){

        return "category/"+param;
    }
}
