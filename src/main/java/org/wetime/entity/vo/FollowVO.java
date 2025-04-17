package org.wetime.entity.vo;

import lombok.Data;
import org.wetime.entity.user.Follow;

/**
 * @description:
 * @Author: Xhy
 * @CreateTime: 2023-10-25 18:03
 */
@Data
public class FollowVO extends Follow {

    private String nickName;
}
