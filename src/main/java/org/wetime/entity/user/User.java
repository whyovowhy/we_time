package org.wetime.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wetime.entity.BaseEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author xhy
 * @since 2023-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;


    private String nickName;

    @Email
    private String email;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String description;

    // true 为男，false为女
    private Boolean sex;

    // 用户默认收藏夹id
    private Long defaultFavoritesId;

    // 头像
    private Long avatar;

    @TableField(exist = false)
    private Boolean each;

    @TableField(exist = false)
    private Set<String> roleName;

}
