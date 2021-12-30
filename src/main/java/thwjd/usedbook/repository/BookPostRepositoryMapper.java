package thwjd.usedbook.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import thwjd.usedbook.entity.BookCategory;
import thwjd.usedbook.entity.BookPost;
import thwjd.usedbook.entity.Member;
import thwjd.usedbook.entity.Pagination;

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

    @Select("select count(*) from bookpost where bookcategory=#{category} ${search}")
    Integer findByCategoryCount(Pagination pagination);

    @Select("select * from bookpost " +
            "where bookcategory=#{category} ${search} " +
            "order by ${order} limit #{perRows} offset #{perFirstRow}")
        //$는 값만 반환, #은 ""을 포함하여 반환
    List<BookPost> findByPaginationAndSearch(Pagination pagination);

    @Select("select * from bookpost where id=#{id}")
    BookPost findById(Long id);

    @Update("update bookpost set viewcount = #{viewCount} where id=#{id}")
    void viewPlus(BookPost bookPost);

    @Update("update bookpost set bookname=#{bookName}, bookcategory=#{bookCategory}, " +
            "bookprice=#{bookPrice}, bookdescription=#{bookDescription}, createtime=#{createTime}" +
            "where id=#{id}")
    int update(BookPost bookPost);

    @Delete("delete from bookpost where id = #{id}")
    int delete(Long bookPostId);
}
