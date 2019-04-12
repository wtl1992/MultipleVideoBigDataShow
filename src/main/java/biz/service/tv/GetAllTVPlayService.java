package biz.service.tv;

import biz.mappers.TvMapper;
import biz.mappers.TvStarMapper;
import model.Tv;
import model.TvExample;
import model.TvStar;
import model.TvStarExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Service
@Lazy
@Scope("singleton")
public class GetAllTVPlayService {

    @Resource(name = "tvMapper")
    private TvMapper tvMapper;

    @Resource(name = "tvStarMapper")
    private TvStarMapper tvStarMapper;

    private class TVSearchRunable implements Runnable{

        @Override
        public void run() {
            while(true){

                //清空数据库中的数据
                TvExample tvExample = new TvExample();
                tvMapper.deleteByExample(tvExample);

                TvStarExample tvStarExample = new TvStarExample();
                tvStarMapper.deleteByExample(tvStarExample);

                for (int i=0;i<100;i++){
                    Map<String,Object> map = getAllResults(i+1);

                    try {
                        List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("tvList");

                        for (int j=0;j<list.size();j++){
                            Map<String,Object> tmpMap = list.get(j);
                            String uuid = UUID.getUUID();
                            try {
                                tvMapper.insert(new Tv(uuid,
                                        tmpMap.get("title").toString(),
                                        tmpMap.get("href").toString(),
                                        tmpMap.get("imgSrc").toString(),
                                        tmpMap.get("jiNumber").toString()));
                                Object o = ((Map<String,Object>)tmpMap.get("star")).get("starts");

                                if (o instanceof String){
                                    tvStarMapper.insert(new TvStar(UUID.getUUID(),
                                            uuid,
                                            o.toString(),
                                            null));
                                }
                                else{
                                    List<Map<String,Object>> ktList = (List<Map<String, Object>>) o;
                                    for (int k=0;k<ktList.size();k++){
                                        Map<String,Object> ktMap = ktList.get(k);
                                        tvStarMapper.insert(new TvStar(UUID.getUUID(),
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
    private List<TVPlaySearchServiceInterface> tvPlaySearchServiceInterfaces;

    public Map<String,Object> getAllResults(int pageIndex){
        Map<String,Object> map = new HashMap<String, Object>();
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        map.put("tvList",list);

        for (int i=0;i<tvPlaySearchServiceInterfaces.size();i++){
            tvPlaySearchServiceInterfaces.get(i).analyseBigData(map,list,pageIndex);
        }

        return map;
    }

}
