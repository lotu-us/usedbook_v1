package thwjd.usedbook.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import thwjd.usedbook.domain.SessionConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute(SessionConstants.LOGIN_MEMBER) == null){

            response.sendRedirect("/login?redirectURL="+requestURI);
            //기존 요청을 쿼리 파라미터로 redirectURL로 지정함으로써 로그인한 이후에는 기존 요청 페이지로 리다이렉트 될 수 있도록 처리하는 것이 고객 입장에서 편리
            return false;
        }
        return true;
    }
}
