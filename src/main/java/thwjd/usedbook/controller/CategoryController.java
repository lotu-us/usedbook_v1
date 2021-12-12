package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class CategoryController {
    @GetMapping("/{category}")
    public String category(@PathVariable String category){
        return "category/"+category;
    }
}
