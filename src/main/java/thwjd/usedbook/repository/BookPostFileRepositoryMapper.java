package thwjd.usedbook.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;
import thwjd.usedbook.entity.BookPostFile;

@Mapper
@Repository
public interface BookPostFileRepositoryMapper {

    @Insert("insert into bookpostfile(bookpostid, writeremail, filepath, filename) " +
            "values(#{bookPostId}, #{writerEmail}, #{filePath}, #{fileName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(BookPostFile bookPostFile);
}
