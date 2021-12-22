package thwjd.usedbook.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import thwjd.usedbook.domain.Member;

import java.util.List;

@Mapper
public interface MybatisMapper {
    @Select("select * from member")
    public List<Member> getList();

    //xml방식
    List<Member> getList2();
}
