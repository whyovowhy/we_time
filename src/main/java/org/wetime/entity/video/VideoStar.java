package org.wetime.entity.video;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wetime.entity.BaseEntity;

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
public class VideoStar extends BaseEntity {

    private static final long serialVersionUID = 1L;


    private Long videoId;

    private Long userId;


}
