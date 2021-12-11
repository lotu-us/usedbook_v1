package thwjd.usedbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.repository.MemberRepository;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private MemberRepository loginRepository;

    public Member login(String email, String password){
        //일치하는 정보가 없다면 null반환
        return loginRepository.findByEmail(email)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}
