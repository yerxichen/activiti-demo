package cn.yer.activiti.demo.service.serviceImpl;

import cn.yer.activiti.demo.constant.CommonConstant;
import cn.yer.activiti.demo.entity.SysPermission;
import cn.yer.activiti.demo.entity.SysUser;
import cn.yer.activiti.demo.entity.SysUserRole;
import cn.yer.activiti.demo.entity.UserYhxx;
import cn.yer.activiti.demo.mapper.SysPermissionMapper;
import cn.yer.activiti.demo.mapper.SysUserMapper;
import cn.yer.activiti.demo.mapper.SysUserRoleMapper;
import cn.yer.activiti.demo.service.ISysUserService;
import cn.yer.activiti.demo.utils.CommonUtils;
import cn.yer.activiti.demo.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户表 服务实现类
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUser getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public SysUser getUserByOpenid(String openid) {
        return userMapper.getUserByOpenid(openid);
    }

    @Override
    public SysUser getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }


    /**
     * 通过用户名获取用户角色集合
     *
     * @param username 用户名
     * @return 角色集合
     */
    @Override
    @Cacheable(value = CommonConstant.LOGIN_USER_RULES_CACHE, key = "'Roles_'+#username")
    public Set<String> getUserRolesSet(String username) {
        // 查询用户拥有的角色集合
        List<String> roles = sysUserRoleMapper.getRoleByUserName(username);
        log.info("-------通过数据库读取用户拥有的角色Rules------username： " + username + ",Roles size: " + (roles == null ? 0 : roles.size()));
        return new HashSet<>(roles);
    }

    /**
     * 通过用户名获取用户权限集合
     *
     * @param username 用户名
     * @return 权限集合
     */
    @Override
    @Cacheable(value = CommonConstant.LOGIN_USER_RULES_CACHE, key = "'Permissions_'+#username")
    public Set<String> getUserPermissionsSet(String username) {
        Set<String> permissionSet = new HashSet<>();
        List<SysPermission> permissionList = sysPermissionMapper.queryByUser(username);
        for (SysPermission po : permissionList) {
//			if (oConvertUtils.isNotEmpty(po.getUrl())) {
//				permissionSet.add(po.getUrl());
//			}
            if (CommonUtils.isNotEmpty(po.getPerms())) {
                permissionSet.add(po.getPerms());
            }
        }
        log.info("-------通过数据库读取用户拥有的权限Perms------username： " + username + ",Perms size: " + (permissionSet == null ? 0 : permissionSet.size()));
        return permissionSet;
    }

    /**
     * 校验用户是否有效
     *
     * @param sysUser
     * @return
     */
    @Override
    public Result<?> checkUserIsEffective(SysUser sysUser) {
        Result<?> result = new Result<Object>();
        //情况1：根据用户信息查询，该用户不存在
        if (sysUser == null) {
            result.setCode(1);
            result.setMessage("该用户不存在，请注册");
            return result;
        }
        //情况2：根据用户信息查询，该用户已注销
        if (CommonConstant.DEL_FLAG_1.toString().equals(sysUser.getDelFlag())) {
            result.error500("该用户已注销");
            return result;
        }
        //情况3：根据用户信息查询，该用户已被删除
        if (CommonConstant.USER_FREEZE.equals(sysUser.getDelFlag())) {
            result.error500("该用户已被删除");
            return result;
        }
        return result;
    }

    @Override
    public Map<String, String> getUserById(String id) {
        return userMapper.getUserById(id);
    }



}