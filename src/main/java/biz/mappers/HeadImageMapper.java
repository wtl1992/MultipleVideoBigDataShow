package biz.mappers;

import java.util.List;
import model.HeadImage;
import model.HeadImageExample;
import org.apache.ibatis.annotations.Param;

public interface HeadImageMapper {
    long countByExample(HeadImageExample example);

    int deleteByExample(HeadImageExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(HeadImage record);

    int insertSelective(HeadImage record);

    List<HeadImage> selectByExample(HeadImageExample example);

    HeadImage selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") HeadImage record, @Param("example") HeadImageExample example);

    int updateByExample(@Param("record") HeadImage record, @Param("example") HeadImageExample example);

    int updateByPrimaryKeySelective(HeadImage record);

    int updateByPrimaryKey(HeadImage record);
}