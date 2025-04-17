package org.wetime.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wetime.entity.json.SettingScoreJson;

/**
 * <p>
 * 
 * </p>
 *
 * @author xhy
 * @since 2023-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_setting")
public class Setting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 审核策略
    private String auditPolicy;

    // 热门视频热度限制
    private Double hotLimit;

    // 审核开关
    private Boolean auditOpen;

    // 资源放行ip
    private String allowIp;

    private Boolean auth;

    @TableField(exist = false)
    private SettingScoreJson settingScoreJson;

}
//{"successScore":{"minPulp":"0.1","maxPulp":"0.9999","minTerror":"0.1","maxTerror":"0.9999","minPolitician":"0.1","maxPolitician":"0.9999","auditStatus":"0"},"manualScore":{"minPulp":"0","maxPulp":"0","minTerror":"0","maxTerror":"0","minPolitician":"0","maxPolitician":"0","auditStatus":"3"},"passScore":{"minPulp":"0","maxPulp":"0","minTerror":"0","maxTerror":"0","minPolitician":"0","maxPolitician":"0","auditStatus":"2"}}