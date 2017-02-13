package com.u2u.framework.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.StringTokenizer;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @ClassName: WebUtils <br>
 * @Description: Http与Servlet工具类. <br>
 * @date 2015-1-12 上午10:45:50 <br>
 * 
 * @author dongming
 */
public class WebUtils {

	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	/**
	 * 设置客户端缓存过期时间 Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response,
			long expiresSeconds) {
		// Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis()
				+ expiresSeconds * 1000);
		// Http 1.1 header
		response.setHeader("Cache-Control", "max-age=" + expiresSeconds);
	}

	/**
	 * 设置客户端无缓存Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 0);
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache");
	}

	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response,
			long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
	 * 
	 * 如果无修改, checkIfModify返回false ,设置304 not modify status.
	 * 
	 * @param lastModified
	 *            内容的最后修改时间.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request,
			HttpServletResponse response, long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
	 * 
	 * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
	 * 
	 * @param etag
	 *            内容的ETag.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request,
			HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(
						headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>Discription:[检查浏览器客户端是否支持gzip编码.]</p>
	 * @param request HttpServletRequest
	 * @return boolean
	 * @author:[dongming]
	 * @update:[2015-1-12] [更改人姓名][变更描述]
	 */
	public static boolean checkAccetptGzip(HttpServletRequest request) {
		// Http1.1 header
		String acceptEncoding = request.getHeader("Accept-Encoding");

		if (StringUtils.contains(acceptEncoding, "gzip")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>Discription:[设置Gzip Header并返回GZIPOutputStream.]</p>
	 * @param response
	 * @return OutputStream
	 * @throws IOException
	 * @author:[dongming]
	 * @update:[2015-1-12] [更改人姓名][变更描述]
	 */
	public static OutputStream buildGzipOutputStream(
			HttpServletResponse response) throws IOException {
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Vary", "Accept-Encoding");
		return new GZIPOutputStream(response.getOutputStream());
	}

    /**
     * <p>Discription:[设置让浏览器弹出下载对话框的Header]</p>
     * @param response HttpServletResponse.
     * @param fileName 下载后的文件名.
     * @author:[dongming]
     * @update:[2015-1-12] [更改人姓名][变更描述]
     */
	public static void setDownloadableHeader(HttpServletResponse response,
			String fileName) {
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");
	}

	/**
	 * <p>Discription:[从request获取cookie]</p>
	 * @param request
	 * @param cookieName
	 * @return
	 * @author:[dongming]
	 * @update:[2015-1-12] [更改人姓名][变更描述]
	 */
	public static String getCookieValue(HttpServletRequest request,
			String cookieName) {
		//Assert.notNull(request);
		//Assert.hasText(cookieName);
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
   
	/**
	 * <p>Discription:[对response设置cookie]</p>
	 * @param response HttpServletResponse
	 * @param cookieName String
	 * @param value String 
	 * @param cookieAge int
	 * @author:[dongming]
	 * @update:[2015-1-12] [更改人姓名][变更描述]
	 */
	public static void setCookieValue(HttpServletResponse response,
			String cookieName, String value, int cookieAge) {
		//Assert.notNull(response);
		//Assert.hasText(cookieName);
		//Assert.hasText(value);
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setPath("/");
		cookie.setMaxAge(cookieAge);
		response.addCookie(cookie);
	}

}
