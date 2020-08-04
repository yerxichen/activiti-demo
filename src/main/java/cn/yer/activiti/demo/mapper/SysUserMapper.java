package cn.yer.activiti.demo.mapper;

import cn.yer.activiti.demo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 用户表 Mapper 接口
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 通过用户账号查询用户信息
     *
     * @param username
     * @return
     */
    public SysUser getUserByName(@Param("username") String username);

    /**
     * 通过openid获取用户信息
     * @param openid
     * @return
     */
    @Select("select t.* from SYS_USER t,T_USER_YHXX s where t.id=s.gl_id and s.openid=#{openid} and zxbs=0")
    SysUser getUserByOpenid(@Param("openid") String openid);

    /**
     * 通过手机号码获取用户信息
     * @param phone
     * @return
     */
    @Select("select t.* from SYS_USER t,T_USER_YHXX s where t.id=s.gl_id and s.lxdh=#{phone} and zxbs=0")
    SysUser getUserByPhone(@Param("phone") String phone);

    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    @Select("select s.*,t.pass_word,t.salt from SYS_USER t,T_USER_YHXX s where t.id=s.gl_id and t.id=#{id} and zxbs=0")
    Map<String,String> getUserById(@Param("id") String id);



}
