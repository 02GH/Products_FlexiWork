package com.work.common.utils; // 定义该类所在的包

import javax.servlet.http.HttpServletRequest; // 导入 HttpServletRequest 类
import javax.servlet.jsp.JspException; // 导入 JspException 类
import javax.servlet.jsp.JspWriter; // 导入 JspWriter 类
import javax.servlet.jsp.tagext.TagSupport; // 导入 TagSupport 类
import java.io.IOException; // 导入 IOException 类
import java.io.UnsupportedEncodingException; // 导入 UnsupportedEncodingException 类
import java.util.Map; // 导入 Map 类

/**
 * 自定义标签类，用于生成分页导航的 HTML 组件
 * 显示格式： 上一页 1 2 3 4 5 下一页
 */
public class NavigationTag extends TagSupport {
    static final long serialVersionUID = 2372405317744358833L; // 序列化的版本号

    /**
     * request 中用于保存 Page<E> 对象的变量名,默认为“page”
     */
    private String bean = "page"; // 用于获取分页数据的请求属性名

    /**
     * 分页跳转的 url 地址, 此属性必须
     */
    private String url = null; // 分页操作的目标 URL

    /**
     * 显示的页码数量
     */
    private int number = 3; // 显示当前页码前后各多少个页码

    @Override
    public int doStartTag() throws JspException {
        // 获取 JSP 输出流
        JspWriter writer = pageContext.getOut();
        // 获取当前请求
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        // 从请求中获取 Page 对象
        Page page = (Page)request.getAttribute(bean);
        if (page == null) // 如果没有找到 Page 对象，则跳过
            return SKIP_BODY;

        // 解析 URL
        url = resolveUrl(url, pageContext);
        try {
            // 将 URL 从 ISO8859-1 编码转换为 UTF-8 编码
            url = new String(url.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        try {
            // 计算总页数
            int pageCount = page.getTotal() / page.getSize();
            if (page.getTotal() % page.getSize() > 0) { // 如果有余数，说明还有额外的一页
                pageCount++;
            }
            // 开始生成分页导航的 HTML 代码
            writer.print("<nav><ul class=\"pagination flex-box\" style=\"text-align:center;\">");
            writer.print("<li><a href=\"javascript:;\">共" + pageCount + "页</a></li>");
            // 显示“上一页”按钮
            if (page.getPage() > 1) {
                String preUrl = append(url, "page", page.getPage() - 1);
                preUrl = append(preUrl, "rows", page.getSize());
                writer.print("<li><a href=\"" + preUrl + "\">上页</a></li>");
            } else {
                writer.print("<li class=\"disabled\"><a href=\"javascript:;\">上页</a></li>");
            }

            // 显示当前页码的前2页码和后2页码
            int indexPage = (page.getPage() - 2 > 0) ? page.getPage() - 2 : 1;
            for (int i = 1; i <= number && indexPage <= pageCount; indexPage++, i++) {
                // 如果是当前页，添加“active”类
                if (indexPage == page.getPage()) {
                    writer.print("<li class=\"active\"><a href=\"javascript:;\">" + indexPage + "<span class=\"sr-only\"></span></a></li>");
                    continue;
                }
                // 构建其他页码的 URL
                String pageUrl = append(url, "page", indexPage);
                pageUrl = append(pageUrl, "rows", page.getSize());
                writer.print("<li><a href=\"" + pageUrl + "\">" + indexPage + "</a></li>");
            }

            // 显示“下一页”按钮
            if (page.getPage() < pageCount) {
                String nextUrl = append(url, "page", page.getPage() + 1);
                nextUrl = append(nextUrl, "rows", page.getSize());
                writer.print("<li><a href=\"" + nextUrl + "\">下页</a></li>");
            } else {
                writer.print("<li class=\"disabled\"><a href=\"javascript:;\">下页</a></li>");
            }
            writer.print("</ul></nav>"); // 结束分页导航的 HTML 代码
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY; // 跳过标签体
    }

    /**
     * 拼接 URL 的辅助方法（重载用法）
     */
    private String append(String url, String key, int value) {
        return append(url, key, String.valueOf(value)); // 将 int 转换为 String
    }

    /**
     * 为 URL 添加请求参数
     *
     * @param url 目标 URL
     * @param key 请求参数的键
     * @param value 请求参数的值
     * @return 更新后的 URL
     */
    private String append(String url, String key, String value) {
        if (url == null || url.trim().length() == 0) {
            return ""; // URL 为空，返回空字符串
        }

        // 检查 URL 是否已有参数
        if (url.indexOf("?") == -1) {
            url = url + "?" + key + "=" + value; // 添加第一个参数
        } else {
            if (url.endsWith("?")) { // 如果 URL 以问号结尾
                url = url + key + "=" + value; // 直接添加参数
            } else {
                url = url + "&amp;" + key + "=" + value; // 添加参数并用 & 分隔
            }
        }

        return url; // 返回更新后的 URL
    }

    /**
     * 为 URL 添加翻页请求参数
     *
     * @param url 目标 URL
     * @param pageContext JSP 上下文
     * @return 更新后的 URL
     * @throws JspException
     */
    private String resolveUrl(String url, javax.servlet.jsp.PageContext pageContext) throws JspException {
        Map params = pageContext.getRequest().getParameterMap(); // 获取请求参数
        for (Object key : params.keySet()) {
            if ("page".equals(key) || "rows".equals(key)) continue; // 跳过 page 和 rows 参数
            Object value = params.get(key);
            if (value == null) continue; // 跳过 null 值
            // 如果值是数组，取第一个元素
            if (value.getClass().isArray()) {
                url = append(url, key.toString(), ((String[]) value)[0]);
            } else if (value instanceof String) {
                url = append(url, key.toString(), value.toString());
            }
        }
        return url; // 返回更新后的 URL
    }

    // 以下是 getter 和 setter 方法，用于属性的访问和修改

    /**
     * @return bean
     */
    public String getBean() {
        return bean;
    }

    /**
     * @param bean 设置 bean
     */
    public void setBean(String bean) {
        this.bean = bean;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url 设置 url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 设置显示页码数量
     *
     * @param number 页码数量
     */
    public void setNumber(int number) {
        this.number = number;
    }
}
