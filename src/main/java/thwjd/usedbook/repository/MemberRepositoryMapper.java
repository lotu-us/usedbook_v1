package thwjd.usedbook.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;
import thwjd.usedbook.domain.Member;

@Mapper
@Repository
public interface MemberRepositoryMapper {

   @Insert("insert into member(email, name, password) values(#{email}, #{name}, #{password})")
   @Options(useGeneratedKeys = true, keyProperty = "id")
   void save(Member member);
}
