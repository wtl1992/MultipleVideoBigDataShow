package biz.mappers;

import model.TVAndStars;
import model.TvStar;
import model.TvStarExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TvAndStarsMapper {
    List<TVAndStars> selectTvsByLikeTitle(String title);

    List<TVAndStars> selectTvsByUUID(String uuid);
}