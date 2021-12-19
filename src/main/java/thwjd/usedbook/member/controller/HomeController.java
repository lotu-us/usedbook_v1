package thwjd.usedbook.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thwjd.usedbook.member.annotation.Login;
import thwjd.usedbook.member.entity.Member;

@Slf4j
@Controller
public class HomeController {
    @GetMapping("/")
    public String index(@Login Member loginMember, Model model){

        //세션에 회원데이터가 없으면 홈으로 이동
        if (loginMember == null) {
            return "index";
        }

        // 세션이 유지되면 로그인 홈으로 이동
        model.addAttribute("loginMember", loginMember);
        return "index";
    }
    /*
    HttpSession은 세션 생성 시 임의의 토큰 값을 생성하여 해당 토큰 값을 JSESSIONID 쿠키로 클라이언트에 전달한다.
    http://localhost:8080/;jsessionid=FF5914CE9797A5F3185B2EFF0E4E472F
    요청이 @PostMapping("/login")에서 넘어오면서 다시 return "index"하기때문에 위에처럼 주소에 jsession 노출되지않는다
     */

}
