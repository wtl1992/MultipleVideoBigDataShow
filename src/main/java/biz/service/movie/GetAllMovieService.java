package biz.service.movie;

import biz.mappers.MovieMapper;
import biz.mappers.MovieStarMapper;
import biz.mappers.TvMapper;
import biz.mappers.TvStarMapper;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Lazy
@Scope("singleton")
public class GetAllMovieService {

    @Resource(name = "movieMapper")
    private MovieMapper movieMapper;

    @Resource(name = "movieStarMapper")
    private MovieStarMapper movieStarMapper;

    private class TVSearchRunable implements Runnable{

        @Override
        public void run() {
            while(true){

                //清空数据库中的数据
                MovieExample movieExample = new MovieExample();
                movieMapper.deleteByExample(movieExample);

                MovieStarExample movieStarExample = new MovieStarExample();
                movieStarMapper.deleteByExample(movieStarExample);

                for (int i=0;i<100;i++){
                    Map<String,Object> map = getAllResults(i+1);

                    try {
                        List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("movieList");

                        for (int j=0;j<list.size();j++){
                            Map<String,Object> tmpMap = list.get(j);
                            String uuid = UUID.getUUID();
                            try {
                                movieMapper.insert(new Movie(uuid,
                                        tmpMap.get("title").toString(),
                                        tmpMap.get("href").toString(),
                                        tmpMap.get("imgSrc").toString(),
                                        tmpMap.get("jiNumber").toString()));
                                Object o = ((Map<String,Object>)tmpMap.get("star")).get("starts");

                                if (o instanceof String){
                                    movieStarMapper.insert(new MovieStar(UUID.getUUID(),
                                            uuid,
                                            o.toString(),
                                            null));
                                }
                                else{
                                    List<Map<String,Object>> ktList = (List<Map<String, Object>>) o;
                                    for (int k=0;k<ktList.size();k++){
                                        Map<String,Object> ktMap = ktList.get(k);
                                        movieStarMapper.insert(new MovieStar(UUID.getUUID(),
                                                uuid,
                                                ktMap.get("name").toString(),
                                                ktMap.get("href").toString()));
                                    }
                                }
                            }
                            catch (Exception e){
                                //
                            }
                        }
                    }
                    catch (Exception e){
                        //
                    }
                }


                try {
                    Thread.sleep(24 * 1000 * 60 * 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    @PostConstruct
    public void after (){
        //new Thread(new TVSearchRunable()).start();
    }

    @Autowired
    private List<MovieSearchServiceInterface> movieSearchServiceInterfaces;

    public Map<String,Object> getAllResults(int pageIndex){
        Map<String,Object> map = new HashMap<String, Object>();
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        map.put("movieList",list);

        for (int i=0;i<movieSearchServiceInterfaces.size();i++){
            movieSearchServiceInterfaces.get(i).analyseBigData(map,list,pageIndex);
        }

        return map;
    }

}
