package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thwjd.usedbook.annotation.Login;
import thwjd.usedbook.entity.BookPost;
import thwjd.usedbook.entity.BookPostFile;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.repository.BookPostFileRepositoryMapper;
import thwjd.usedbook.repository.BookPostRepositoryMapper;
import thwjd.usedbook.service.BookPostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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
                               HttpServletRequest request, @Login Member loginMember) throws IOException {
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
            if(loginMember != null){
                bookPost.setWriterEmail(loginMember.getEmail());                
            }else{
                bookPost.setWriterEmail("익명@admin");    
            }

            Date currentTime = new Date();
            bookPost.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime));
            
            bookPostMapper.save(bookPost);  //id저장됨
            bookPostService.fileSave(bookPost);

            String currentUrl = request.getRequestURL().toString();
            String redirectUrl = currentUrl.replace("/bookPost/write", "/bookPost/detail/"+bookPost.getId());
            response.put("status", "saveOk");
            response.put("response", redirectUrl);
        }

        return response;
    }



    @GetMapping("/detail/{bookPostId}")
    public String detail(@PathVariable Long bookPostId, Model model){
        BookPost byId = bookPostMapper.findById(bookPostId);

        byId.setViewCount(byId.getViewCount() +1);
        bookPostMapper.viewPlus(byId);
        model.addAttribute("bookPost", byId);

        List<BookPostFile> byIdFile = bookPostFileMapper.findById(bookPostId);
        model.addAttribute("fileList", byIdFile);
        return "bookPost/detail";
    }

    @GetMapping("/getImage/{imgName}")
    private ResponseEntity<Resource> getImageUrl(@PathVariable String imgName) throws IOException {
        return bookPostService.fileFoundTest(imgName);
        //http://localhost:8080/bookPost/imgtest/69221ce3-e582-4ce5-9a56-3730b5ba53ec_9_%EC%BD%94%EB%94%A9%EB%A7%88%EC%A7%80%EB%A7%89.jpg
    }



}
