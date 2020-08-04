package cn.yer.activiti.demo.service;

import cn.yer.activiti.demo.entity.SysUser;
import cn.yer.activiti.demo.entity.SysUserRole;
import cn.yer.activiti.demo.entity.UserYhxx;
import cn.yer.activiti.demo.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.Map;
import java.util.Set;

public interface ISysUserService extends IService<SysUser> {

    public SysUser getUserByName(String username);

    /**
     * 通过openid获取用户信息
     * @param openid
     * @return
     */
    SysUser getUserByOpenid(String openid);

    /**
     * 通过手机号码获取用户信息
     * @param phone
     * @return
     */
    SysUser getUserByPhone(String phone);



    /**
     * 通过用户名获取用户角色集合
     *
     * @param username 用户名
     * @return 角色集合
     */
    Set<String> getUserRolesSet(String username);

    /**
     * 通过用户名获取用户权限集合
     *
     * @param username 用户名
     * @return 权限集合
     */
    Set<String> getUserPermissionsSet(String username);

    /**
     * 校验用户是否有效
     *
     * @param sysUser
     * @return
     */
    Result checkUserIsEffective(SysUser sysUser);


    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    Map<String,String> getUserById(String id);




}
