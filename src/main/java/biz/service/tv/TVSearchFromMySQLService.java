package biz.service.tv;

import biz.mappers.TvAndStarsMapper;
import biz.mappers.TvMapper;
import biz.mappers.TvStarMapper;
import model.TVAndStars;
import model.Tv;
import model.TvStar;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("tvSearchFromMySQLService")
@Lazy
@Scope("singleton")
public class TVSearchFromMySQLService {

    @Resource(name = "tvAndStarsMapper")
    private TvAndStarsMapper tvAndStarsMapper;

    @Resource(name = "tvMapper")
    private TvMapper tvMapper;

    @Resource(name = "tvStarMapper")
    private TvStarMapper tvStarMapper;


    @Transactional
    public List<TVAndStars> selectByTvLikeTitle(String keyword){
        Map<String,TVAndStars> map = new HashMap<String, TVAndStars>();
        List<TVAndStars> list = tvAndStarsMapper.selectTvsByLikeTitle(keyword);
        if (list != null){
            for (int i=0;i<list.size();i++){
                map.put(list.get(i).getUuid(),list.get(i));
            }
        }

        //查询tv_star里的数据
        List<TvStar> tvStars = tvStarMapper.selectByLikeName(keyword);

        if (tvStars != null){
            for (int i=0;i<tvStars.size();i++){
                Tv tv = tvMapper.selectByPrimaryKey(tvStars.get(i).getTvuuid());

                List<TVAndStars> tvAndStars = tvAndStarsMapper.selectTvsByLikeTitle(tv.getTitle());

                if (tvAndStars != null){
                    for (int j=0;j<tvAndStars.size();j++){
                        map.put(tvAndStars.get(j).getUuid(),tvAndStars.get(j));
                    }
                }
            }
        }


        List<TVAndStars> tvAndStars = new ArrayList<>();

        for (String item : map.keySet()){
            tvAndStars.add(map.get(item));
        }
        return tvAndStars;
    }

}
