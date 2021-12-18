package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import thwjd.usedbook.annotation.Login;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.entity.SessionConstants;

@Slf4j
@Controller
public class HomeController {
    @GetMapping("/")
    public String index(@Login Member loginMember, Model model){

        //세션에 회원데이터가 없으면 홈으로 이동
        if (loginMember == null) {
            log.info("loginMember={}", loginMember);
            return "index";
        }

        // 세션이 유지되면 로그인 홈으로 이동
        model.addAttribute("loginMember", loginMember);
        log.info("loginMember={}", loginMember);
        return "index";
    }
    /*
    HttpSession은 세션 생성 시 임의의 토큰 값을 생성하여 해당 토큰 값을 JSESSIONID 쿠키로 클라이언트에 전달한다.
    http://localhost:8080/;jsessionid=FF5914CE9797A5F3185B2EFF0E4E472F

     */

}
