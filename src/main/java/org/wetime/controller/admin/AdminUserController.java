package org.wetime.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wetime.authority.Authority;
import org.wetime.entity.user.Role;
import org.wetime.entity.user.User;
import org.wetime.entity.user.UserRole;
import org.wetime.entity.vo.BasePage;
import org.wetime.service.user.RoleService;
import org.wetime.service.user.UserRoleService;
import org.wetime.service.user.UserService;
import org.wetime.util.R;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @Author: Xhy
 * @CreateTime: 2023-10-24 19:35
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleService roleService;

    @GetMapping("/list")
    @Authority("admin:user:list")
    public R list(){
        return R.ok().data(userService.list(new LambdaQueryWrapper<>()));
    }

    @GetMapping("/page")
    @Authority("admin:user:page")
    public R list(BasePage basePage,
                  @RequestParam(required = false) String name){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>();
        wrapper.like(!ObjectUtils.isEmpty(name),User::getEmail,name);
        IPage<User> page = userService.page(basePage.page(), wrapper);
        // 查出用户角色中间表
        Map<Long, List<UserRole>> userRoleMap = userRoleService.list(null).stream().collect(Collectors.groupingBy(UserRole::getUserId));
        // 根据角色查出角色表信息
        Map<Long, String> roleMap = roleService.list(null).stream().collect(Collectors.toMap(Role::getId, Role::getName));
        Map<Long, Set<String>> map = new HashMap();
        userRoleMap.forEach((uId,rIds)->{
            Set<String> roles = new HashSet<>();
            for (UserRole rId : rIds) {
                roles.add(roleMap.get(rId.getRoleId()));
            }
            map.put(uId,roles);
        });
        for (User user : page.getRecords()) {
            user.setRoleName(map.get(user.getId()));
        }
        return R.ok().data(page.getRecords()).count(page.getTotal());
    }
}
