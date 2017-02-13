package com.u2u.framework.base;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.codehaus.jackson.map.ObjectMapper;
import org.hsqldb.lib.StringUtil;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.adapter.interceptor.ThreadLocalResponse;
import com.u2u.framework.beans.MobileResponse;
import com.u2u.framework.editor.DateEditor;
import com.u2u.framework.editor.DoubleEditor;
import com.u2u.framework.editor.IntegerEditor;
import com.u2u.framework.editor.LongEditor;
import com.u2u.framework.util.MobileUtil;
import com.u2u.framework.util.StringUtils;

/**
 * 
 * @ClassName: BaseController <br>
 * @Description: 基础Controller类 <br>
 * @date 2015年1月12日 下午1:27:08 <br>
 * 
 * @author Freud
 * 
 * @update:[2015年2月05日] [Dean][修改备注.增加ajaxDone方法.]
 */
public class BaseRestController {

	/**
	 * The LOGGER.
	 */
	private static final Log LOG = LogFactory.getLog(BaseRestController.class);

	public void validate(String token, String loginid, String pwd)
			throws Exception {
		if (!MobileUtil.validToken(token, loginid, pwd)) {
			throw new Exception("授权失败");
		}
	}

	/**
	 * 
	 * <p>
	 * Discription:[Ajax返回“操作成功”提示]
	 * </p>
	 * 
	 * @param message
	 * @return
	 * @author:[Freud]
	 * @update:[2015年1月12日] [更改人姓名][变更描述]
	 */
	protected MobileResponse success(Object msg) throws Exception {
		return success("操作成功", msg);
	}

	protected <T> T getRealClazz(String resource, Class<T> clazz) {
		if (StringUtil.isEmpty(resource)) {
			throw new RuntimeException("参数非法");
		} else {
			try {
				T ret = new ObjectMapper().readValue(resource, clazz);
				if (ret == null) {
					throw new RuntimeException("参数非法");
				} else {
					return ret;
				}
			} catch (Exception e) {
				throw new RuntimeException("参数非法");
			}
		}
	}

	protected MobileResponse success(String desc, Object msg) throws Exception {
		MobileResponse response = new MobileResponse();
		response.setErrorCode("0");
		response.setErrorMsg(desc);
		response.setMessage(msg);
		return response;
	}

	/**
	 * 
	 * <p>
	 * Discription:[框架统一异常处理]
	 * </p>
	 * 
	 * @param e
	 * @return
	 * @author:[Freud]
	 * @update:[2015年1月12日] [更改人姓名][变更描述]
	 */
	@ExceptionHandler(Exception.class)
	protected @ResponseBody MobileResponse exception(final Exception e) {
		MobileResponse response = new MobileResponse();
		response.setErrorCode("1");
		response.setErrorMsg(e.getMessage());
		LOG.error("Exception occured, msg:" + e.getMessage(), e);
		return response;
	}

	/**
	 * 
	 * <p>
	 * Discription:[注册PropertyEditors来实现类型转换]
	 * </p>
	 * 
	 * @param request
	 * @param binder
	 * @throws Exception
	 * @author:[Freud]
	 * @update:[2015年1月12日] [更改人姓名][变更描述]
	 */
	@InitBinder
	protected void initBinder(final HttpServletRequest request,
			final ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());

		// Following binder was conflict with the conversion service
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/**
	 * 
	 * <p>
	 * Discription:[根据页面请求封装RowBounds对象]
	 * </p>
	 * 
	 * @param baseReq
	 * @author:[XuQinghan]
	 * @update:[2015年1月12日] [更改人姓名][变更描述]
	 */
	public RowBounds buildRowBounds(BaseRequest baseReq) {
		RowBounds rb;
		if (StringUtils.isNotEmpty(baseReq.getPage())
				&& StringUtils.isNotEmpty(baseReq.getPage())) {
			rb = new RowBounds(
					(Integer.parseInt(baseReq.getPage()) - 1)
							* Integer.parseInt(baseReq.getRows()),
					Integer.parseInt(baseReq.getRows()));
		} else {
			rb = new RowBounds();
		}
		return rb;
	}

	public String saveQrcode(HttpServletRequest request, String resource) {
		return null;
	}

	/**
	 * 
	 * <p>
	 * Discription:[根据页面请求封装RowBounds对象]
	 * </p>
	 * 
	 * @author:[XuQinghan]
	 * @update:[2015年1月12日] [更改人姓名][变更描述]
	 */
	public RowBounds buildRowBounds() {
		return new RowBounds(0, Integer.MAX_VALUE);
	}

	/**
	 * 
	 * <p>
	 * Discription:[注入HttpServletResponse]
	 * </p>
	 * 
	 * @param response
	 * @author:[XuQinghan]
	 * @update:[2015年1月12日] [更改人姓名][变更描述]
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletResponse response) {
		ThreadLocalResponse.set(response);
	}

}
