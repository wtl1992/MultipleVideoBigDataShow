package biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Lazy
@Scope("singleton")
public class AllResultItemService {

    @Autowired
    private List<SearchBigDataServiceInterface> searchBigDataServiceInterfaces;

//    @Resource(name = "flumeSparkListenerService")
//    private FlumeSparkListenerService flumeSparkListenerService;

    public Map<String,Object> getAllResults(String keyword, int pageIndex){
        Map<String,Object> map = new HashMap<String, Object>();
        List<Map<String,Object>> multiply_list = new ArrayList<Map<String, Object>>();
        List<Map<String,Object>> single_list = new ArrayList<Map<String, Object>>();
        //首先处理多模块的数据，即item_v_Divs里的内容
        map.put("multiply",multiply_list);
        //将single_list添加到map里面
        map.put("single",single_list);

        //analyseVQQCOMBigData(keyword,map,multiply_list,single_list,pageIndex);

        for (SearchBigDataServiceInterface searchBigDataServiceInterface : searchBigDataServiceInterfaces){
//            new Thread(new SearchBigDataRunable(keyword,
//                    map,multiply_list,single_list,
//                    pageIndex,searchBigDataServiceInterface)).start();
            searchBigDataServiceInterface.analyseBigData(keyword,
                    map,multiply_list,single_list,pageIndex);
        }
        return map;
    }
}
