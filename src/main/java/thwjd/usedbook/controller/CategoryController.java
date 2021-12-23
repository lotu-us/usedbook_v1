package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import thwjd.usedbook.annotation.Login;
import thwjd.usedbook.entity.Member;

@Slf4j
@Controller
public class CategoryController {

    @GetMapping("/category/{number}")
    public String category(@PathVariable String number, @Login Member loginMember, Model model){
        log.info("{} enter", number);

        // 세션이 유지되면 로그인 홈으로 이동
        model.addAttribute("loginMember", loginMember);
        return "category/category"+number;
    }
}
