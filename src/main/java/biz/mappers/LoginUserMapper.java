package biz.mappers;

import java.util.List;
import model.LoginUser;
import model.LoginUserExample;
import org.apache.ibatis.annotations.Param;

public interface LoginUserMapper {
    long countByExample(LoginUserExample example);

    int deleteByExample(LoginUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LoginUser record);

    int insertSelective(LoginUser record);

    List<LoginUser> selectByExample(LoginUserExample example);

    LoginUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LoginUser record, @Param("example") LoginUserExample example);

    int updateByExample(@Param("record") LoginUser record, @Param("example") LoginUserExample example);

    int updateByPrimaryKeySelective(LoginUser record);

    int updateByPrimaryKey(LoginUser record);
}