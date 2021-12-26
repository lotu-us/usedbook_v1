package thwjd.usedbook.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import thwjd.usedbook.entity.BookPost;
import thwjd.usedbook.entity.Member;

import java.util.List;

@Mapper
@Repository
public interface BookPostRepositoryMapper {

    @Insert("insert into bookpost(writeremail, bookname, bookcategory, bookprice, bookdescription) " +
                        "values(#{writerEmail}, #{bookName}, #{bookCategory}, #{bookPrice}, #{bookDescription})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(BookPost bookPost);

    @Select("select * from bookpost")
    List<BookPost> findAll();
}
