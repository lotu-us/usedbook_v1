package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thwjd.usedbook.entity.*;
import thwjd.usedbook.repository.BookPostFileRepositoryMapper;
import thwjd.usedbook.repository.BookPostRepositoryMapper;

import java.util.List;

@Slf4j
@Controller
public class HomeController {

    @Autowired BookPostRepositoryMapper bookPostMapper;
    @Autowired BookPostFileRepositoryMapper bookPostFileMapper;

    @GetMapping("/")
    public String index(Model model){

        BookCategory[] categories = BookCategory.values();

        for (BookCategory category : categories) {
            List<BookPost> bookPostList = bookPostMapper.getIndexList(category.toString());

            for (BookPost bookPost : bookPostList) {
                BookPostFile oneFile = bookPostFileMapper.getOneFile(bookPost.getId());
                if(oneFile == null){
                    bookPost.setThumbFileName(null);
                }else{
                    bookPost.setThumbFileName(oneFile.getFileName());
                }

            }

            model.addAttribute(category.getLowerCase()+"List", bookPostList);
            //System.out.println(result.get(category.getLowerCase()+"FileList"));
        }

        return "index";
    }
    /*
    HttpSession은 세션 생성 시 임의의 토큰 값을 생성하여 해당 토큰 값을 JSESSIONID 쿠키로 클라이언트에 전달한다.
    http://localhost:8080/;jsessionid=FF5914CE9797A5F3185B2EFF0E4E472F
     */

}
