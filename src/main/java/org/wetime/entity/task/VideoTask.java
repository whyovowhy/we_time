package org.wetime.entity.task;

import lombok.Data;
import org.wetime.entity.video.Video;

/**
 * @description: 封装发布视频
 * @Author: Xhy
 * @CreateTime: 2023-11-02 13:57
 */
@Data
public class VideoTask {

    // 新视频
    private Video video;

    // 老视频
    private Video oldVideo;

    // 是否是新增
    private Boolean isAdd;

   // 老状态 : 0 公开  1 私密
    private Boolean oldState;

    private Boolean newState;
}
