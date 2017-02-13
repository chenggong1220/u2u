package com.u2u.ibms.rest.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseRestController;
import com.u2u.framework.beans.MobileResponse;
import com.u2u.ibms.rest.auth.service.MobileAuthorService;
import com.u2u.ibms.rest.auth.vo.ChangeLoginPwdRequest;
import com.u2u.ibms.rest.auth.vo.ChangePushMessageRequest;
import com.u2u.ibms.rest.auth.vo.LoginRequest;
import com.u2u.ibms.rest.auth.vo.MainPageRequest;
import com.u2u.ibms.rest.auth.vo.ModifyUserInfoRequest;
import com.u2u.ibms.rest.auth.vo.RegistRequest;
import com.u2u.ibms.rest.auth.vo.ResetLoginPwdRequest;
import com.u2u.ibms.rest.auth.vo.SmsCodeRequest;
import com.u2u.ibms.rest.auth.vo.UploadPersonVerifyRequest;

@Controller
@RequestMapping("/mobile/auth")
public class MobileAuthorController extends BaseRestController {

	@Autowired
	private MobileAuthorService mobileAuthorService;
	/**
	 * 1.1.1发送短信验证码
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSmsCode", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getSmsCode(@RequestBody SmsCodeRequest request)
			throws Exception {
		mobileAuthorService.sendsms(request.getMobile());
		return success("");
	}
	/**
	 * 1.1.2注册
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse register(@RequestBody RegistRequest request)
			throws Exception {
		mobileAuthorService.regist(request);
		return success("注册成功", "");
	}
	/**
	 * 1.2.1登录
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse login(@RequestBody LoginRequest request)
			throws Exception {
		MobileResponse response = success(mobileAuthorService
				.login(request.getUsername(), request.getPwd()));
		return response;
	}
	/**
	 * 1.4.1版本更新
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkVersion")
	@ResponseBody
	public MobileResponse checkVersion() throws Exception {
		return success(mobileAuthorService.checkVersion());
	}
	/**
	 * 1.3.1登出
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout/{username}/{pwd}")
	@ResponseBody
	public MobileResponse logout(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd) throws Exception {
		mobileAuthorService.logout(username, pwd);
		return success("");
	}
	/**
	 * 1.5.1修改登录密码
	 * 
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyLoginPwd/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse modifyLoginPwd(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody ChangeLoginPwdRequest request) throws Exception {
		request.setUsername(username);
		mobileAuthorService.modifyLoginPwd(request);
		return success("");
	}
	/**
	 * 1.5.2重置登录密码
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse resetLoginPwd(
			@RequestBody ResetLoginPwdRequest request) throws Exception {
		mobileAuthorService.resetLoginPwd(request);
		return success("");
	}
	/**
	 * 1.5.3完善个人信息
	 * 
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyPersonInfo/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse modifyPersonInfo(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody ModifyUserInfoRequest request) throws Exception {
		mobileAuthorService.modifyPersonInfo(request);
		return success("");
	}
	/**
	 * 1.5.4 获取个人资料信息
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPersonInfo/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getPersonInfo(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd) throws Exception {
		MobileResponse response = success(
				mobileAuthorService.getPersonInfo(username));
		return response;
	}
	/**
	 * 1.5.5 上传个人证件信息
	 * 
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadPersonVerify/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse uploadPersonVerify(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody UploadPersonVerifyRequest request) throws Exception {
		request.setUserName(username);
		mobileAuthorService.uploadPersonVerify(request);
		return success("");
	}
	/**
	 * 1.5.6 获取个人证件信息
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPersonVerify/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getPersonVerify(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd) throws Exception {
		return success(mobileAuthorService.getPersonVerify(username));
	}
	/**
	 * 1.5.7 获取个人证件信息
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changePushMessage/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse changePushMessage(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody ChangePushMessageRequest request) throws Exception {
		request.setUsername(username);
		request.setPwd(pwd);
		mobileAuthorService.changePushMessage(request);
		return success("");
	}
	/**
	 * 1.5.8联系客服接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getContactCustomerService/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getContactCustomerService(@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd)
			throws Exception {
		return success(mobileAuthorService.getContactCustomerService());
	}
	/**
	 * 1.5.9主页上方滚动图片接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMainPageImgs", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getMainPageImgs(@RequestBody MainPageRequest request)
			throws Exception {
		return success(mobileAuthorService.getMainPageImgs(request));
	}
	/**
	 * 1.5.10获取我的钱包接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getWalletInfo/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getWalletInfo(@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd)
			throws Exception {
		return success(mobileAuthorService.getWalletInfo(username));
	}
}
