package biz.mappers;

import model.MovieAndStars;

import java.util.List;

public interface MovieAndStarsMapper {
    List<MovieAndStars> selectMoviesByLikeTitle(String title);

    List<MovieAndStars> selectMoviesByUUID(String uuid);
}