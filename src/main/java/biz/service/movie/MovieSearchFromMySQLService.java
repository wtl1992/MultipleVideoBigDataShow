package biz.service.movie;

import biz.mappers.*;
import model.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("movieSearchFromMySQLService")
@Lazy
@Scope("singleton")
public class MovieSearchFromMySQLService {

    @Resource(name = "movieAndStarsMapper")
    private MovieAndStarsMapper movieAndStarsMapper;

    @Resource(name = "movieMapper")
    private MovieMapper movieMapper;

    @Resource(name = "movieStarMapper")
    private MovieStarMapper movieStarMapper;


    @Transactional
    public List<MovieAndStars> selectByMovieLikeTitle(String keyword){
        Map<String,MovieAndStars> map = new HashMap<String, MovieAndStars>();
        List<MovieAndStars> list = movieAndStarsMapper.selectMoviesByLikeTitle(keyword);
        if (list != null){
            for (int i=0;i<list.size();i++){
                map.put(list.get(i).getUuid(),list.get(i));
            }
        }

        //查询movie_star里的数据
        List<MovieStar> movieStars = movieStarMapper.selectByLikeName(keyword);

        if (movieStars != null){
            for (int i=0;i<movieStars.size();i++){
                Movie movie = movieMapper.selectByPrimaryKey(movieStars.get(i).getMovieuuid());

                List<MovieAndStars> movieAndStars = movieAndStarsMapper.selectMoviesByLikeTitle(movie.getTitle());

                if (movieAndStars != null){
                    for (int j=0;j<movieAndStars.size();j++){
                        map.put(movieAndStars.get(j).getUuid(),movieAndStars.get(j));
                    }
                }
            }
        }


        List<MovieAndStars> movieAndStars = new ArrayList<>();

        for (String item : map.keySet()){
            movieAndStars.add(map.get(item));
        }
        return movieAndStars;
    }

}
