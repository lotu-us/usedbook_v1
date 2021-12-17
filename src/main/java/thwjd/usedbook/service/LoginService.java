package thwjd.usedbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.entity.ValidCheckResponse;
import thwjd.usedbook.repository.MemberRepository;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private MemberRepository loginRepository;

    public BindingResult validCheck(Member member, BindingResult bindingResult){

        Member emailCheck = loginRepository.findByEmail(member.getEmail()).orElse(null);
        Member emailAndpasswordCheck = loginRepository.findByEmail(member.getEmail())
                .filter(m -> m.getPassword().equals(member.getPassword()))
                .orElse(null);

        if(bindingResult.hasFieldErrors("email")){
            return bindingResult;
        }
        if(emailCheck == null){
            bindingResult.rejectValue("email", "EmailFoundFail", "일치하는 이메일이 없습니다");
            return bindingResult;
        }
        if(emailAndpasswordCheck == null){
            bindingResult.reject("PasswordFail", "비밀번호를 확인해주세요");
            return bindingResult;
        }
        return null;
    }

}
