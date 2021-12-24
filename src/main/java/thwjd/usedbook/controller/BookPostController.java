package thwjd.usedbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import thwjd.usedbook.entity.BookCategory;
import thwjd.usedbook.entity.BookPost;

import javax.validation.Valid;
import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class BookPostController {

    @GetMapping("/newBookPost")
    public String newPostForm(@ModelAttribute BookPost bookPost){
        return "bookpost/newBookPost";
    }

    @PostMapping("/newBookPost")
    public String newPostSave(@Validated @ModelAttribute BookPost bookPost, BindingResult bindingResult) throws IOException {


        log.info("bookPost={}", bookPost);
        if(bindingResult.hasErrors() || bookPost.getBookCategory() == null){
            bindingResult.rejectValue("bookCategory", "bookCategoryNullFail", "값을 선택해주세요");
            return "bookpost/newBookPost";
        }

        //return "/bookpost/categoryName";
        return "redirect:/";
    }

    @PostMapping("/newBookPost/fileupload")
    @ResponseBody
    public String newPostSave(@RequestParam(value = "fileList", required = false) MultipartFile[] fileList) throws IOException {

        String projectPath = System.getProperty("user.dir");
        String userUploadImgPath = projectPath + "\\src\\main\\resources\\userUploadImg";

        UUID uuid = UUID.randomUUID();

//        String filename = uuid + "_" + fileList.getOriginalFilename();
//
//        File saveFile = new File(userUploadImgPath, filename);
//        fileList.transferTo(saveFile);

        String result="";
        for (MultipartFile multipartFile : fileList) {
            result = result + multipartFile.getOriginalFilename() + ", ";
        }
        return result;
    }
}
