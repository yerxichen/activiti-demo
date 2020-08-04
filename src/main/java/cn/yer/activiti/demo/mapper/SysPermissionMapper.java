package cn.yer.activiti.demo.mapper;

import cn.yer.activiti.demo.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单权限表 Mapper 接口
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户查询用户权限
     */
    public List<SysPermission> queryByUser(@Param("username") String username);

}
