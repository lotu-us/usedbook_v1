package thwjd.usedbook.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.entity.RegisterCheckResponse;
import thwjd.usedbook.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegisterValidator implements Validator {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Member member = (Member)target;
        Member emailcheck = memberRepository.findByEmail(member.getEmail()).orElse(null);
        List<RegisterCheckResponse> response = new ArrayList<>();

        if(errors.hasFieldErrors("email")) {
            response.add(new RegisterCheckResponse(false, "email", allErrorMessage(errors, "email")));
        }else{
            if(emailcheck!=null) {
                response.add(new RegisterCheckResponse(false, "email", "중복되는 이메일이 있어요"));
            }else{
                response.add(new RegisterCheckResponse(true, "email", "멋진 이메일이에요"));
            }
        }

        if(errors.hasFieldErrors("name")){
            response.add(new RegisterCheckResponse(false, "name", allErrorMessage(errors, "name")));
        }else{
            response.add(new RegisterCheckResponse(true, "name", "멋진 이름이에요"));
        }

        if(errors.hasFieldErrors("password")){
            response.add(new RegisterCheckResponse(false, "password", allErrorMessage(errors, "password")));
        }else{
            response.add(new RegisterCheckResponse(true, "password", "멋진 비밀번호에요"));
        }

    }


    private String allErrorMessage(Errors errors, String field){
        StringBuilder errorMessage = new StringBuilder("");
        List<FieldError> list = errors.getFieldErrors(field);
        for (FieldError o : list) { errorMessage.append(o.getDefaultMessage()+"<br>"); }
        return errorMessage.toString();
    }
}
