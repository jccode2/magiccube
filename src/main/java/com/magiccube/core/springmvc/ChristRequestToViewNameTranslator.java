package com.magiccube.core.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.util.UrlPathHelper;

public class ChristRequestToViewNameTranslator implements RequestToViewNameTranslator {
    
    /**
     * 斜线
     */
    private static final String SLASH = "/";
    
    /**
     * 前缀
     */
    private String prefix = "";
    
    /**
     * 后缀
     */
    private String suffix = "";
    
    /**
     * 分隔符
     */
    private String separator = SLASH;
    
    /**
     * 是否去掉顶头的斜线
     */
    private boolean stripLeadingSlash = true;
    
    /**
     * 是否去掉末尾的斜线
     */
    private boolean stripTrailingSlash = true;
    
    /**
     * 是否去掉后缀名
     */
    private boolean stripExtension = true;
    
    /**
     * URL 路径帮助类
     */
    private UrlPathHelper urlPathHelper = new UrlPathHelper();
    
    /**
     * Set the prefix to prepend to generated view names.
     * 
     * @param prefix the prefix to prepend to generated view names
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix != null ? prefix : "";
    }
    
    /**
     * Set the suffix to append to generated view names.
     * 
     * @param suffix the suffix to append to generated view names
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix != null ? suffix : "";
    }
    
    /**
     * Set the value that will replace '<code>/</code>' as the separator in the view name. The default behavior simply
     * leaves '<code>/</code>' as the separator.
     * 
     * @param separator 分隔符
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }
    
    /**
     * Set whether or not leading slashes should be stripped from the URI when generating the view name. Default is
     * "true".
     * 
     * @param stripLeadingSlash stripLeadingSlash
     */
    public void setStripLeadingSlash(boolean stripLeadingSlash) {
        this.stripLeadingSlash = stripLeadingSlash;
    }
    
    /**
     * Set whether or not trailing slashes should be stripped from the URI when generating the view name. Default is
     * "true".
     * 
     * @param stripTrailingSlash stripTrailingSlash
     */
    public void setStripTrailingSlash(boolean stripTrailingSlash) {
        this.stripTrailingSlash = stripTrailingSlash;
    }
    
    /**
     * Set whether or not file extensions should be stripped from the URI when generating the view name. Default is
     * "true".
     * 
     * @param stripExtension stripExtension
     */
    public void setStripExtension(boolean stripExtension) {
        this.stripExtension = stripExtension;
    }
    
    /**
     * Set if URL lookup should always use the full path within the current servlet context. Else, the path within the
     * current servlet mapping is used if applicable (i.e. in the case of a ".../*" servlet mapping in web.xml). Default
     * is "false".
     * 
     * @see org.springframework.web.util.UrlPathHelper#setAlwaysUseFullPath
     * @param alwaysUseFullPath alwaysUseFullPath
     */
    public void setAlwaysUseFullPath(boolean alwaysUseFullPath) {
        this.urlPathHelper.setAlwaysUseFullPath(alwaysUseFullPath);
    }
    
    /**
     * Set if the context path and request URI should be URL-decoded. Both are returned <i>undecoded</i> by the Servlet
     * API, in contrast to the servlet path.
     * <p>
     * Uses either the request encoding or the default encoding according to the Servlet spec (ISO-8859-1).
     * 
     * @see org.springframework.web.util.UrlPathHelper#setUrlDecode
     * @param urlDecode 是否url转码
     */
    public void setUrlDecode(boolean urlDecode) {
        this.urlPathHelper.setUrlDecode(urlDecode);
    }
    
    /**
     * Set the {@link org.springframework.web.util.UrlPathHelper} to use for the resolution of lookup paths.
     * <p>
     * Use this to override the default UrlPathHelper with a custom subclass, or to share common UrlPathHelper settings
     * across multiple web components.
     * 
     * @param urlPathHelper UrlPathHelper
     */
    public void setUrlPathHelper(UrlPathHelper urlPathHelper) {
        Assert.notNull(urlPathHelper, "UrlPathHelper must not be null");
        this.urlPathHelper = urlPathHelper;
    }
    
    /**
     * Translates the request URI of the incoming {@link HttpServletRequest} into the view name based on the configured
     * parameters.
     * 
     * @see org.springframework.web.util.UrlPathHelper#getLookupPathForRequest
     * @see #transformPath
     * @param request HttpServletRequest
     * @return ViewName
     */
    public String getViewName(HttpServletRequest request) {
        String strLookupPath = this.urlPathHelper.getLookupPathForRequest(request);
        return this.prefix + transformPath(strLookupPath) + this.suffix;
    }
    
    /**
     * Transform the request URI (in the context of the webapp) stripping slashes and extensions, and replacing the
     * separator as required.
     * 
     * @param lookupPath the lookup path for the current request, as determined by the UrlPathHelper
     * @return the transformed path, with slashes and extensions stripped if desired
     */
    protected String transformPath(String lookupPath) {
        String strPath = lookupPath;
        if (this.stripLeadingSlash && strPath.startsWith(SLASH)) {
            strPath = strPath.substring(1);
        }
        if (this.stripTrailingSlash && strPath.endsWith(SLASH)) {
            strPath = strPath.substring(0, strPath.length() - 1);
        }
        if (this.stripExtension) {
            strPath = StringUtils.stripFilenameExtension(strPath);
        }
        if (!SLASH.equals(this.separator)) {
            strPath = StringUtils.replace(strPath, SLASH, this.separator);
        }
        strPath = deleteActionName(strPath);
        return strPath;
    }
    
    /**
     * 去掉路径中的action名
     * 
     * @param path 路径
     * @return String
     */
    private String deleteActionName(String path) {
        StringBuffer sbResult = new StringBuffer(256);
        if (path != null) {
            String[] strPath = path.split("/");
            strPath[strPath.length - 1] = firstCharToUpperCase(strPath[strPath.length - 1]);
            for (int i = 0; i < strPath.length; i++) {
                if (i != strPath.length - 2) {
                    sbResult.append("/").append(strPath[i]);
                }
            }
        }
        return sbResult.toString();
    }
    
    /**
     * 首字母大写
     * 
     * @param fileName 文件名
     * @return String
     */
    private String firstCharToUpperCase(String fileName) {
        return fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
    }
}
