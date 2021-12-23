package thwjd.usedbook.entity;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

@Data
public class BookPost {
    private String email;

    @NotBlank
    private String bookName;

    @NotNull //NotBlank는 String과 Collection타입에만 적용된다
    private BookCategory bookCategory;

    @NotNull //NotBlank는 String과 Collection타입에만 적용된다
    @Min(1000)
    private Integer bookPrice;

    @NotBlank
    private String bookDescription;

    //@NotBlank
    private String imgFilePath;

    //@NotBlank
    private String imgFileName;

    public BookPost(){

    }
}
