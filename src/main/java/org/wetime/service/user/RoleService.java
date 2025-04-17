package org.wetime.service.user;

import org.wetime.entity.user.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wetime.entity.user.Tree;
import org.wetime.entity.vo.AssignRoleVO;
import org.wetime.entity.vo.AuthorityVO;
import org.wetime.util.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xhy
 * @since 2023-10-24
 */
public interface RoleService extends IService<Role> {

    List<Tree> tree();

    R removeRole(String id);

    R gavePermission(AuthorityVO authorityVO);

    R gaveRole(AssignRoleVO assignRoleVO);

}
