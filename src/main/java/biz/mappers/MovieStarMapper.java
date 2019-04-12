package biz.mappers;

import java.util.List;
import model.MovieStar;
import model.MovieStarExample;
import model.TvStar;
import org.apache.ibatis.annotations.Param;

public interface MovieStarMapper {
    long countByExample(MovieStarExample example);

    int deleteByExample(MovieStarExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(MovieStar record);

    int insertSelective(MovieStar record);

    List<MovieStar> selectByExample(MovieStarExample example);

    MovieStar selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") MovieStar record, @Param("example") MovieStarExample example);

    int updateByExample(@Param("record") MovieStar record, @Param("example") MovieStarExample example);

    int updateByPrimaryKeySelective(MovieStar record);

    int updateByPrimaryKey(MovieStar record);

    List<MovieStar> selectBySecondUUID(String tv_uuid);

    List<MovieStar> selectByLikeName(String name);
}