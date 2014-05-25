package org.cell.base;

import org.apache.commons.lang.StringUtils;


/**
 * 操作 URL 的工具类。
 *
 * @author : chenyy
 * @version : 1.0
 * @since : 12-6-4 下午7:58
 */
public class UrlHelper {

    private String prefix = "";

    public UrlHelper(String prefix){
        this.prefix = StringUtils.removeEnd(prefix, "/");
    }

    public String join(String postfix){
        return String.format("%s/%s", prefix, StringUtils.removeStart(postfix, "/"));
    }
}
