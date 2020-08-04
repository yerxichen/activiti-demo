package cn.yer.activiti.demo.mapper;

import cn.yer.activiti.demo.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单角色表 Mapper 接口
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select * from sys_role")
    List<SysRole> getList();

    @Select("select id from sys_role where role_name=#{roleName}")
    String getIdByName(@Param("roleName") String roleName);

}
