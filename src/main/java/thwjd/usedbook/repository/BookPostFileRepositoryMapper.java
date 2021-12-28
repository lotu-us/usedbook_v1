package thwjd.usedbook.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import thwjd.usedbook.entity.BookPostFile;

import java.util.List;

@Mapper
@Repository
public interface BookPostFileRepositoryMapper {

    @Insert("insert into bookpostfile(bookpostid, writeremail, filepath, filename) " +
            "values(#{bookPostId}, #{writerEmail}, #{filePath}, #{fileName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(BookPostFile bookPostFile);

    @Select("select * from bookpostfile where bookpostid = #{bookPostId}")
    List<BookPostFile> findById(Long bookPostId);
}
