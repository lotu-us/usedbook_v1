package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thwjd.usedbook.entity.LoginMember;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.entity.RegisterCheckResponse;
import thwjd.usedbook.repository.MemberRepository;
import thwjd.usedbook.service.LoginService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MemberRepository memberRepository;


    public void init(){
        memberRepository.save(new Member("11@11", "11", "11"));
    }



    @GetMapping("/login")
    public String loginForm(){
        return "member/login";
    }
    @PostMapping("/login")
    public String loginOk(@Validated @ModelAttribute LoginMember loginMember, BindingResult bindingResult){
        Member login = loginService.login(loginMember.getEmail(), loginMember.getPassword());
        if(login == null){
            bindingResult.reject("loginFail", "아이디와 비밀번호를 확인해주세요");
        }

        return "index";
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
            log.info("All={}", memberRepository.findAll());
            return "redirect:/member/register_ok";
        }
    }
    //ajax
    @PostMapping("/register/check")
    @ResponseBody
    public List registerEmail(@Validated @RequestBody Member member, BindingResult bindingResult) {
        //@ModelAttribute는 파라미터 값으로 DTO객체에 바인딩을 하는 방식이기 때문에 바인딩하려는 DTO객체에 Setter메소드가 반드시 있어야 하고,
        //@RequestBody는 요청 본문의 body에 json이나 xml값으로 요청을 하여 HttpMessageConveter를 반드시 거쳐 DTO객체에 맞는 타입으로 바꿔서 바인딩을 시켜준다.

        List<RegisterCheckResponse> response = new ArrayList<>();

        Member emailcheck = memberRepository.findByEmail(member.getEmail()).orElse(null);

        String[] fields = {"email", "name", "password"};
        //defaultErrorAdd
        for (String field : fields) {
            StringBuilder errorMessage = new StringBuilder("");
            List<FieldError> fieldErrors = bindingResult.getFieldErrors(field);
            for (FieldError fieldError : fieldErrors) {
                errorMessage.append(fieldError.getDefaultMessage()+"<br>");
            }
            response.add(new RegisterCheckResponse(false, field, errorMessage.toString()));
        }

        //custom
        if (!bindingResult.hasFieldErrors("email")) {
            if (emailcheck != null) {
                response.add(new RegisterCheckResponse(false, "email", "중복되는 이메일이 있어요"));
            } else {
                response.add(new RegisterCheckResponse(true, "email", "멋진 이메일이에요"));
            }
        }
        if (!bindingResult.hasFieldErrors("name")) {
            response.add(new RegisterCheckResponse(true, "name", "멋진 이름이에요"));
        }
        if (!bindingResult.hasFieldErrors("password")) {
            response.add(new RegisterCheckResponse(true, "password", "멋진 비밀번호에요"));
        }

        return response;
    }
    @GetMapping("/member/register_ok")
    public String register_ok(){
        return "member/register_ok";
    }




}
