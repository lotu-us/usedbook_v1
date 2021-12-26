package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thwjd.usedbook.annotation.Login;
import thwjd.usedbook.entity.BookPost;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.repository.BookPostFileRepositoryMapper;
import thwjd.usedbook.repository.BookPostRepositoryMapper;
import thwjd.usedbook.service.BookPostService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/bookPost")
public class BookPostController {

    @Autowired BookPostService bookPostService;
    @Autowired BookPostRepositoryMapper bookPostMapper;
    @Autowired BookPostFileRepositoryMapper bookPostFileMapper;

    @GetMapping("/write")
    public String writeForm(@ModelAttribute BookPost bookPost){
        return "bookPost/write";
    }

    @PostMapping("/write")
    @ResponseBody
    public Map writeSave(@Validated @ModelAttribute BookPost bookPost, BindingResult bindingResult,
                               HttpServletRequest request) throws IOException {
        Map<String, Object> response = new HashMap<>();

        if(bookPost.getFileList().size() > 10){
            response.put("status", "validPhoto");
            response.put("response", "사진은 10개까지만 업로드 가능합니다.");
        }

        List validList = bookPostService.newBookPostValidCheck(bookPost, bindingResult);
        if(validList.size() > 0){
            response.put("status", "valid");
            response.put("response", validList);
        }

        if(validList.size() == 0){
            String currentUrl = request.getRequestURL().toString();
            String urlCategory = bookPost.getBookCategory().toString().toLowerCase();
            String redirectUrl = currentUrl.replace("/bookPost/write", "/category/"+urlCategory);
            response.put("status", "saveOk");
            response.put("response", redirectUrl);

//            , @Login Member loginMember
//            bookPost.setWriterEmail(loginMember.getEmail());
            bookPost.setWriterEmail("admin@admin");

            bookPostMapper.save(bookPost);  //id저장됨
            bookPostService.fileSave(bookPost);
        }

        return response;
    }
}
