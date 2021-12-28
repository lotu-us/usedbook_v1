package thwjd.usedbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;
import thwjd.usedbook.entity.BookPost;
import thwjd.usedbook.entity.BookPostFile;
import thwjd.usedbook.entity.Pagination;
import thwjd.usedbook.entity.ValidCheckResponse;
import thwjd.usedbook.repository.BookPostFileRepositoryMapper;
import thwjd.usedbook.repository.BookPostRepositoryMapper;

import javax.servlet.ServletContext;
import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BookPostService {

    @Autowired BookPostRepositoryMapper bookPostMapper;
    @Autowired BookPostFileRepositoryMapper bookPostFileMapper;

    public List newBookPostValidCheck(BookPost bookPost, BindingResult bindingResult){
        List<ValidCheckResponse> response = new ArrayList<>();

        String[] fields = {"bookName", "bookCategory", "bookPrice", "bookDescription"};
        //defaultErrorAdd
        for (String field : fields) {
            if(bindingResult.hasFieldErrors(field)){
                StringBuilder errorMessage = new StringBuilder("");
                List<FieldError> fieldErrors = bindingResult.getFieldErrors(field);
                for (FieldError fieldError : fieldErrors) {
                    errorMessage.append(fieldError.getDefaultMessage()+"<br>");
                }
                response.add(new ValidCheckResponse(false, field, errorMessage.toString()));
            }
        }

        //custom
        if(bookPost.getBookCategory() == null){
            response.add(new ValidCheckResponse(false, "bookCategory", "값을 선택해주세요"));
        }

//        for (String field : fields) {
//            if(!bindingResult.hasFieldErrors(field)){
//                response.add(new ValidCheckResponse(true, field, ""));
//            }
//        }
        return response;
    }


    public void fileSave(BookPost bookPost) throws IOException {
        //String uploadPath = Paths.get("D:", "projectEn", "usedbook", "src", "main", "resources", "userUploadImg").toString();
        String uploadPath = Paths.get("D:", "projectEn", "usedbook", "userUploadImg").toString();

        int order = 10;
        for (MultipartFile multipartFile : bookPost.getFileList()) {
            UUID uuid = UUID.randomUUID();
            String filename = uuid + "_" + order + "_" + multipartFile.getOriginalFilename();
            Path savePath = Paths.get(uploadPath + File.separator + filename).toAbsolutePath();

            multipartFile.transferTo(savePath.toFile());
            //MultipartFile.transferTo()는 요청 시점의 임시 파일을 로컬 파일 시스템에 영구적으로 복사하는 역할을 수행한다.
            // 단 한번만 실행되며 두번째 실행부터는 성공을 보장할 수 없다.
            //Embedded Tomcat을 컨테이너로 사용할 경우 DiskFileItem.write()가 실제 역할을 수행한다.
            // I/O 사용을 최소화하기 위해 파일 이동을 시도하며, 이동이 불가능할 경우 파일 복사를 진행한다.

            BookPostFile bookPostFile = new BookPostFile(
                    bookPost.getId(),
                    bookPost.getWriterEmail(),
                    uploadPath,
                    filename
            );
            bookPostFileMapper.save(bookPostFile);
            order--;
        }
    }



    public ResponseEntity<Resource> fileFoundTest(String imgName) throws IOException {
        String uploadPath = Paths.get("D:", "projectEn", "usedbook", "userUploadImg").toString();
        Resource resource = new FileSystemResource(uploadPath + File.separator + imgName);

        if(!resource.exists()){
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        Path savePath = Paths.get(uploadPath + File.separator + imgName).toAbsolutePath();
        headers.add("Content-Type", Files.probeContentType(savePath));

        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }



    public String pageInit(String categoryName, Pagination pagination){
        pagination.setCategory(categoryName);
        pagination.setLastPageNum(bookPostMapper.findByCategoryCount(pagination.getCategory()));

        //log.info("pagination.getPage()={}", pagination.getPage());
        if(pagination.getPage() < 1){
            return "category/"+categoryName+"?page=1";
        }
        if(pagination.getPage() > pagination.getLastPageNum()){
            return "category/"+categoryName+"?page="+pagination.getLastPageNum();
        }

        pagination.setPerFirstRow(pagination.getPage());
        pagination.setListStartNum(pagination.getPage());
        pagination.setListEndNum();

        return null;
    }


}
