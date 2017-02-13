package com.u2u.framework.base;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.u2u.framework.adapter.interceptor.ThreadLocalResponse;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.framework.editor.DateEditor;
import com.u2u.framework.editor.DoubleEditor;
import com.u2u.framework.editor.IntegerEditor;
import com.u2u.framework.editor.LongEditor;
import com.u2u.framework.enums.AjaxDoneStatusCode;
import com.u2u.framework.enums.ExceptionTypeCode;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.framework.exception.ServiceBusinessException;
import com.u2u.framework.exception.ServiceDBException;
import com.u2u.framework.exception.ServiceIOException;
import com.u2u.framework.exception.ServiceMailException;
import com.u2u.framework.exception.ServiceSMSException;
import com.u2u.framework.exception.ServiceThreadException;
import com.u2u.framework.exception.ServiceTimerException;
import com.u2u.framework.util.LogUtil;
import com.u2u.framework.util.MessageUtil;
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
public class BaseController {

	/**
	 * The LOGGER.
	 */
	private static final Log LOG = LogFactory.getLog(BaseController.class);

	/**
	 * The Error Description String : ERROR_STRING_ERROR_OCCURED.
	 */
	private static final String ERROR_STRING_ERROR_OCCURED = "Exception occured : statusCode[{0}], Message[{1}], returnUrl[{2}]";

	/**
	 * The empty string.
	 */
	private static final String EMPTY_STR = "";

	/**
	 * 国际化Messager Source.
	 */
	@Autowired
	protected ResourceBundleMessageSource _res;

	/**
	 * Discription:[返回默认提示.]
	 * 
	 * @param statusCode
	 *            AjaxDoneStatusCode
	 * @param returnUrl
	 *            String
	 * @return AjaxDone
	 * @author:[Dean]
	 */
	protected AjaxDone ajaxDone(final AjaxDoneStatusCode statusCode,
			final String returnUrl) {
		return new AjaxDone(statusCode.getCode(), statusCode.getDesc(),
				returnUrl);
	}

	/**
	 * Discription:[方法功能中文描述]
	 * 
	 * @param statusCode
	 *            错误标识码.
	 * @param message
	 *            错误信息.
	 * @param returnUrl
	 *            跳转url.
	 * @return AjaxDone
	 * @author:[Freud]
	 */
	protected AjaxDone ajaxDone(final AjaxDoneStatusCode statusCode,
			final String message, final String returnUrl) {

		return new AjaxDone(statusCode.getCode(), message, returnUrl);
	}

	/**
	 * <p>
	 * Discription:[返回默认操作失败提示.]
	 * </p>
	 * 
	 * @param message
	 * @return
	 * @author:[Freud]
	 * @update:[2015年1月12日] [更改人姓名][变更描述]
	 */
	protected AjaxDone ajaxDoneError(final String message,
			final String returnUrl) {
		return ajaxDone(AjaxDoneStatusCode.CD_FAILED, message, returnUrl);
	}

	/**
	 * Discription:[返回默认"操作失败！"提示.]
	 * 
	 * @param returnUrl
	 *            String
	 * @return AjaxDone
	 * @author:[Dean]
	 */
	protected AjaxDone ajaxDoneError(final String returnUrl) {
		return ajaxDone(AjaxDoneStatusCode.CD_FAILED, returnUrl);
	}

	/**
	 * <p>
	 * Discription:[返回默认“操作成功！”提示]
	 * </p>
	 * 
	 * @param message
	 * @return
	 * @author:[Freud]
	 * @update:[2015年1月12日] [更改人姓名][变更描述]
	 */
	protected AjaxDone ajaxDoneSuccess(final String returnUrl) {
		return ajaxDone(AjaxDoneStatusCode.CD_SUCCESS, returnUrl);
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
	protected AjaxDone ajaxDoneSuccess(final String message,
			final String returnUrl) {
		return ajaxDone(AjaxDoneStatusCode.CD_SUCCESS, message, returnUrl);
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
	protected @ResponseBody AjaxDone exception(final Exception e) {

		// 业务异常
		if (e instanceof ServiceBusinessException) {
			LogUtil.error(LOG, MessageUtil.formmatString(
					ERROR_STRING_ERROR_OCCURED,
					ExceptionTypeCode.CD_BUSINESS.getCode(), e.getMessage(),
					EMPTY_STR));
			return new AjaxDone(ExceptionTypeCode.CD_BUSINESS.getCode(),
					e.getMessage(), EMPTY_STR);
			// DB 操作异常
		} else if (e instanceof ServiceDBException || e instanceof SQLException) {
			LogUtil.error(LOG, MessageUtil.formmatString(
					ERROR_STRING_ERROR_OCCURED,
					ExceptionTypeCode.CD_DB.getCode(), e.getMessage(),
					EMPTY_STR));
			return new AjaxDone(ExceptionTypeCode.CD_DB.getCode(),
					e.getMessage(), EMPTY_STR);
			// 权限验证异常
		} else if (e instanceof ServiceAuthorizeException
				|| e instanceof AuthenticationException) {
			LogUtil.error(LOG, MessageUtil.formmatString(
					ERROR_STRING_ERROR_OCCURED,
					ExceptionTypeCode.CD_AUTHORIZATION.getCode(),
					e.getMessage(), EMPTY_STR));
			return new AjaxDone(ExceptionTypeCode.CD_AUTHORIZATION.getCode(),
					e.getMessage(), EMPTY_STR);
			// 定时器异常
		} else if (e instanceof ServiceTimerException) {
			LogUtil.error(LOG, MessageUtil.formmatString(
					ERROR_STRING_ERROR_OCCURED,
					ExceptionTypeCode.CD_TIMER.getCode(), e.getMessage(),
					EMPTY_STR));
			return new AjaxDone(ExceptionTypeCode.CD_TIMER.getCode(),
					e.getMessage(), EMPTY_STR);
			// 流异常
		} else if (e instanceof ServiceIOException || e instanceof IOException) {
			LogUtil.error(LOG, MessageUtil.formmatString(
					ERROR_STRING_ERROR_OCCURED,
					ExceptionTypeCode.CD_FILE.getCode(), e.getMessage(),
					EMPTY_STR));
			return new AjaxDone(ExceptionTypeCode.CD_FILE.getCode(),
					e.getMessage(), EMPTY_STR);
			// 线程异常
		} else if (e instanceof ServiceThreadException
				|| e instanceof InterruptedException) {
			LogUtil.error(LOG, MessageUtil.formmatString(
					ERROR_STRING_ERROR_OCCURED,
					ExceptionTypeCode.CD_THREAD.getCode(), e.getMessage(),
					EMPTY_STR));
			return new AjaxDone(ExceptionTypeCode.CD_THREAD.getCode(),
					e.getMessage(), EMPTY_STR);
			// 短信猫异常
		} else if (e instanceof ServiceSMSException) {
			LogUtil.error(LOG, MessageUtil.formmatString(
					ERROR_STRING_ERROR_OCCURED,
					ExceptionTypeCode.CD_SMS.getCode(), e.getMessage(),
					EMPTY_STR));
			return new AjaxDone(ExceptionTypeCode.CD_SMS.getCode(),
					e.getMessage(), EMPTY_STR);
			// 邮件异常
		} else if (e instanceof ServiceMailException) {
			LogUtil.error(LOG, MessageUtil.formmatString(
					ERROR_STRING_ERROR_OCCURED,
					ExceptionTypeCode.CD_EMAIL.getCode(), e.getMessage(),
					EMPTY_STR));
			return new AjaxDone(ExceptionTypeCode.CD_EMAIL.getCode(),
					e.getMessage(), EMPTY_STR);
			// 其他所有系同级别的异常
		} else {
			LogUtil.error(LOG, MessageUtil.formmatString(
					ERROR_STRING_ERROR_OCCURED,
					ExceptionTypeCode.CD_SYS.getCode(), e.getMessage(),
					EMPTY_STR));
			e.printStackTrace();
			return new AjaxDone(ExceptionTypeCode.CD_SYS.getCode(),
					e.getMessage(), EMPTY_STR);
		}

	}

	/**
	 * 
	 * <p>
	 * Discription:[组装国际化消息]
	 * </p>
	 * 
	 * @param code
	 * @param args
	 * @return
	 * @author:[Freud]
	 * @update:[2015年1月12日] [更改人姓名][变更描述]
	 */
	protected String getMessage(final String code, final Object... args) {

		final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		final LocaleResolver localeResolver = RequestContextUtils
				.getLocaleResolver(request);

		final Locale locale = localeResolver.resolveLocale(request);

		LogUtil.debug(LOG, MessageUtil.formmatString(
				"Internationalized [{0}].",
				MessageUtil.formmatString(code, args)));

		return _res.getMessage(code, args, locale);
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
			rb = new RowBounds((Integer.parseInt(baseReq.getPage()) - 1)
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
