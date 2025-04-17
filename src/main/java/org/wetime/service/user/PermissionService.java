package org.wetime.service.user;

import org.wetime.entity.user.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xhy
 * @since 2023-10-24
 */
public interface PermissionService extends IService<Permission> {

    Map<String, Object> initMenu(Long userId);


    List<Permission> treeSelect();


    void removeMenu(Long id);
}
