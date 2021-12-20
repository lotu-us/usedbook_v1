package thwjd.usedbook.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PostController {
    @GetMapping("/newPost")
    public String newPost(){
        return "/post/newPost";
    }
}
