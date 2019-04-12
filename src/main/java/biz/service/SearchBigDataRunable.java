package biz.service;

import java.util.List;
import java.util.Map;

public class SearchBigDataRunable implements Runnable {

    private String keyword;
    private Map<String, Object> map;
    private List<Map<String, Object>> multiply_list;
    private List<Map<String, Object>> single_list;
    private int pageIndex;
    private SearchBigDataServiceInterface searchBigDataServiceInterface;

    public SearchBigDataRunable(String keyword,
                                Map<String, Object> map,
                                List<Map<String, Object>> multiply_list,
                                List<Map<String, Object>> single_list,
                                int pageIndex,
                                SearchBigDataServiceInterface searchBigDataServiceInterface){
        this.keyword = keyword;
        this.map = map;
        this.multiply_list = multiply_list;
        this.single_list = single_list;
        this.pageIndex = pageIndex;
        this.searchBigDataServiceInterface = searchBigDataServiceInterface;
    }
    @Override
    public void run() {
        searchBigDataServiceInterface.analyseBigData(keyword,
                map,multiply_list,single_list,pageIndex);
    }
}
