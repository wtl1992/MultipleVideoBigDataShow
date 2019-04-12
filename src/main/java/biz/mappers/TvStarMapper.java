package biz.mappers;

import java.util.List;
import model.TvStar;
import model.TvStarExample;
import org.apache.ibatis.annotations.Param;

public interface TvStarMapper {
    long countByExample(TvStarExample example);

    int deleteByExample(TvStarExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(TvStar record);

    int insertSelective(TvStar record);

    List<TvStar> selectByExample(TvStarExample example);

    TvStar selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") TvStar record, @Param("example") TvStarExample example);

    int updateByExample(@Param("record") TvStar record, @Param("example") TvStarExample example);

    int updateByPrimaryKeySelective(TvStar record);

    int updateByPrimaryKey(TvStar record);

    List<TvStar> selectBySecondUUID(String tv_uuid);

    List<TvStar> selectByLikeName(String name);
}