package com.github.sd4324530.fastweixin.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 群发图文信息时Article实体
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 *
 * @author Nottyjay
 * @version 1.0.beta
 *          ====================================================================
 */
public class Article extends BaseModel {

    public final class ShowConverPic {
        public static final String YES = "1";// 显式封面
        public static final String NO  = "0";// 不显式封面
    }

    @JSONField(name = "thumb_media_id")
    private String thumbMediaId;
    private String author;
    private String title;
    @JSONField(name = "content_source_url")
    private String contentSourceUrl;
    private String content;
    private String digest;
    @JSONField(name = "show_cover_pic")
    private String showConverPic = ShowConverPic.YES;

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentSourceUrl() {
        return contentSourceUrl;
    }

    public void setContentSourceUrl(String contentSourceUrl) {
        this.contentSourceUrl = contentSourceUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getShowConverPic() {
        return showConverPic;
    }

    public void setShowConverPic(String showConverPic) {
        this.showConverPic = showConverPic;
    }
}
