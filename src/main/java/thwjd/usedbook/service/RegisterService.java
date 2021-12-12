package thwjd.usedbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.entity.RegisterCheckResponse;
import thwjd.usedbook.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RegisterService {

    @Autowired
    MemberRepository memberRepository;

    public List validCheck(Member member, BindingResult bindingResult){

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

}
