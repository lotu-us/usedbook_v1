package thwjd.usedbook.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import thwjd.usedbook.entity.BookPost;
import thwjd.usedbook.entity.Member;

import java.awt.print.Book;
import java.util.List;

@Mapper
@Repository
public interface BookPostRepositoryMapper {

    @Insert("insert into bookpost(writeremail, bookname, bookcategory, bookprice, bookdescription, createtime) " +
                        "values(#{writerEmail}, #{bookName}, #{bookCategory}, #{bookPrice}, #{bookDescription}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(BookPost bookPost);

    @Select("select * from bookpost")
    List<BookPost> findAll();

    @Select("select * from bookpost where bookcategory=#{category}")
    List<BookPost> findByCategory(String category);

    @Select("select * from bookpost where id=#{id}")
    BookPost findById(Long id);
}
