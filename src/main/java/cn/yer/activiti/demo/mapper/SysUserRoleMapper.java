package cn.yer.activiti.demo.mapper;

import cn.yer.activiti.demo.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色表 Mapper 接口
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    @Select("select role_name from sys_role where id in (select role_id from sys_user_role where user_id = (select id from sys_user where user_name=#{username}))")
    List<String> getRoleByUserName(@Param("username") String username);

    @Update("update sys_user_role set ")
    int updateUserRole(@Param("userId") String userId);
}
