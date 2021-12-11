package thwjd.usedbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.repository.MemberRepository;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

//    public BindingResult register(Member member, BindingResult bindingResult){
//        Member emailcheck = memberRepository.findByEmail(member.getEmail()).orElse(null);
//
//        if(!(emailcheck == null)) {  //null이어야 회원가입 가능
//            bindingResult.rejectValue("email", "loginFail", "이메일이 중복됩니다");
//        }
//        return bindingResult;
//    }

}
