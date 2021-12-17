package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.repository.MemberRepository;
import thwjd.usedbook.service.LoginService;
import thwjd.usedbook.service.RegisterService;

import java.util.List;

@Slf4j
@Controller
public class LoginController {

    @Autowired private LoginService loginService;
    @Autowired private RegisterService registerService;
    @Autowired private MemberRepository memberRepository;


    public void init(){
        memberRepository.save(new Member("11@11", "11", "11"));
    }



    @GetMapping("/login")
    public String loginForm(@ModelAttribute Member member){
        //타임리프에서 이상하게 자꾸 오류가 생기면 member를 전달해주었는지를 확인하자
        init();
        log.info("All={}", memberRepository.findAll());
        return "member/login";
    }

    @PostMapping("/login")
    public String loginOk(@Validated @ModelAttribute Member member, BindingResult bindingResult){

        BindingResult newBindingResult = loginService.validCheck(member, bindingResult);
        if(newBindingResult!=null && newBindingResult.hasErrors()){
            return "member/login";
        }else{
            return "redirect:/";
        }
    }



    @GetMapping("/register")
    public String registerForm(@ModelAttribute Member member){
        init();
        return "member/register";
    }

    @PostMapping("/register")
    public String registerSave(@Validated @ModelAttribute Member member, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            //view에서 타임리프로 th:value="${member.email}" 하면 기존 입력값 출력됨
            return "member/register";
        }else {
            memberRepository.save(member);
            //log.info("All={}", memberRepository.findAll());
            return "redirect:/member/register_ok";
        }
    }

    //ajax
    @PostMapping("/register/check")
    @ResponseBody
    public List registerCheck(@Validated @RequestBody Member member, BindingResult bindingResult) {
        //@ModelAttribute는 파라미터 값으로 DTO객체에 바인딩을 하는 방식이기 때문에 바인딩하려는 DTO객체에 Setter메소드가 반드시 있어야 하고,
        //@RequestBody는 요청 본문의 body에 json이나 xml값으로 요청을 하여 HttpMessageConveter를 반드시 거쳐 DTO객체에 맞는 타입으로 바꿔서 바인딩을 시켜준다.
        return registerService.validCheck(member, bindingResult);
    }

    @GetMapping("/member/register_ok")
    public String register_ok(){
        return "member/register_ok";
    }




}
