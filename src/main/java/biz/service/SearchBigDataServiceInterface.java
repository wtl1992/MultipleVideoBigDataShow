package biz.service;

import java.util.List;
import java.util.Map;

public interface SearchBigDataServiceInterface {

    public void analyseBigData(String keyword,
                                             Map<String,Object> map,
                                             List<Map<String,Object>> multiply_list,
                                             List<Map<String,Object>> single_list,
                                             int pageIndex);
}
