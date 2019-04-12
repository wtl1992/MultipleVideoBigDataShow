package biz.mappers;

import java.util.List;
import model.Tv;
import model.TvExample;
import org.apache.ibatis.annotations.Param;

public interface TvMapper {
    long countByExample(TvExample example);

    int deleteByExample(TvExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(Tv record);

    int insertSelective(Tv record);

    List<Tv> selectByExample(TvExample example);

    Tv selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") Tv record, @Param("example") TvExample example);

    int updateByExample(@Param("record") Tv record, @Param("example") TvExample example);

    int updateByPrimaryKeySelective(Tv record);

    int updateByPrimaryKey(Tv record);
}