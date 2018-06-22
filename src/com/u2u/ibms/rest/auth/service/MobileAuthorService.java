package com.u2u.ibms.rest.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u2u.common.component.sms.AlidayuSmsMessage;
import com.u2u.common.component.sms.ThridPartySmsTemplate;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.Constants;
import com.u2u.ibms.common.Constants.SMSCODE;
import com.u2u.ibms.common.beans.AppVersion;
import com.u2u.ibms.common.beans.MainImg;
import com.u2u.ibms.common.beans.UserInfo;
import com.u2u.ibms.common.beans.location.City;
import com.u2u.ibms.common.beans.location.Province;
import com.u2u.ibms.common.mapper.AppVersionMapper;
import com.u2u.ibms.common.mapper.LocationMapper;
import com.u2u.ibms.common.mapper.SystemMapper;
import com.u2u.ibms.common.mapper.UserInfoMapper;
import com.u2u.ibms.rest.auth.vo.ChangeLoginPwdRequest;
import com.u2u.ibms.rest.auth.vo.ChangePushMessageRequest;
import com.u2u.ibms.rest.auth.vo.ContactCustomerServiceResponse;
import com.u2u.ibms.rest.auth.vo.LoginRespose;
import com.u2u.ibms.rest.auth.vo.MainPageImgResponse;
import com.u2u.ibms.rest.auth.vo.MainPageRequest;
import com.u2u.ibms.rest.auth.vo.ModifyUserInfoRequest;
import com.u2u.ibms.rest.auth.vo.PersonInfoResponse;
import com.u2u.ibms.rest.auth.vo.PersonVerifyInfoResponse;
import com.u2u.ibms.rest.auth.vo.QuestionInfo;
import com.u2u.ibms.rest.auth.vo.RegistRequest;
import com.u2u.ibms.rest.auth.vo.ResetLoginPwdRequest;
import com.u2u.ibms.rest.auth.vo.UploadPersonVerifyRequest;
import com.u2u.ibms.rest.auth.vo.VersionResponse;
import com.u2u.ibms.rest.auth.vo.WalletInfoResponse;

@Service
public class MobileAuthorService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private AppVersionMapper appVersionMapper;
	@Autowired
	private ThridPartySmsTemplate smsTemplate;
	@Autowired
	private LocationMapper locationMapper;
	@Autowired
	private SystemMapper systemMapper;
	public LoginRespose login(String username, String pwd, String regID) throws Exception {
		LoginRespose response = new LoginRespose();
		UserInfo userinfo = userInfoMapper.getUserByUserName(username);
		if (userinfo == null) {
			throw new Exception("登陆失败！用户名不存在！");
		} else if (!userinfo.getPassword().equals(pwd)) {
			throw new Exception("登陆失败！密码错误！");
		} else {
			response.setNickname(userinfo.getNickname());
			response.setEmail(userinfo.getEmail());
			response.setUsername(userinfo.getUsername());
			response.setMobile(userinfo.getMobile());
			response.setUserType(userinfo.getUserType() + "");
			response.setHeadImg(userinfo.getHeadImg());
			response.setPushMessage(userinfo.isPushMessage() ? 1 : 0);
			response.setProvinceId(userinfo.getProvinceId() + "");
			response.setCityId(userinfo.getCityId() + "");
			Province province = locationMapper
					.getProvinceById(userinfo.getProvinceId());
			City city = locationMapper.getCityById(userinfo.getCityId());
			response.setCityName(city.getName());
			response.setProviceName(province.getName());
			
			//更新App RegID信息，SUNZHE, 2017-03-26
			userinfo.setDeviceRegID(regID);
			userInfoMapper.update(userinfo);
		}
		return response;
	}

	public void sendsms(String telphone) throws Exception {
		int random = (int) (Math.random() * 9000 + 1000);
		Constants.setUserSms(telphone, String.valueOf(random)); // 将动态短信吗放入内存，等待手机绑定
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("AppName", "鹫翎企业管理");
		maps.put("VerifyCode", random + "");
		AlidayuSmsMessage message = new AlidayuSmsMessage();
		message.setSignName("zhuce");
		message.setTemplateCode("zhuce");
		message.setPhoneNum(telphone);
		message.bulidContext(maps);
		smsTemplate.sendMessage(message);
	}

	public void regist(RegistRequest request) throws Exception {
		SMSCODE code = Constants.getUserSms(request.getMobile());
		if (System.currentTimeMillis() - code.getTime() > 2 * 60 * 1000) {
			throw new Exception("验证码已失效");
		}
		if (!request.getSmsCode().equals(code.getCode())) {
			throw new Exception("验证码错误！");
		}
		UserInfo userinfo = userInfoMapper
				.getUserByUserName(request.getUsername());
		if (userinfo != null) {
			throw new Exception("用户名已存在！");
		}
		userinfo = userInfoMapper.getUserInfoByMobile(request.getMobile());
		if (userinfo != null) {
			throw new Exception("手机号已经注册！");
		}

		UserInfo user = new UserInfo();
		user.setUsername(request.getUsername());
		user.setNickname(request.getNickname());
		user.setPassword(request.getPwd());
		user.setMobile(request.getMobile());
		user.setEmail(request.getEmail());
		user.setProvinceId(request.getProvince());
		user.setCityId(request.getCity());
		user.setHeadImg(request.getHeadImg());
		user.setIndustry(request.getIndustry());
		user.setUserType(request.getUserType());
		user.setCreateDate(DateUtil.currentTimestamp());
		user.setOperateDate(DateUtil.currentTimestamp());
		userInfoMapper.insert(user);
	}

	public VersionResponse checkVersion() throws Exception {
		AppVersion version = appVersionMapper.checkVersion();
		if (version == null) {
			throw new Exception("已是最新版本");
		}
		VersionResponse response = new VersionResponse();
		response.setVersion(version.getVersion());
		response.setEnforce(version.isEnforce() ? "1" : "0");
		response.setUpdateTime(DateUtil.timestamp2String(
				version.getOperateDate(), DateUtil.PATTERN_STANDARD));
		return response;
	}

	/**
	 * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
	 * 
	 * @param version1
	 * @param version2
	 * @return
	 */
	public static int compareVersion(String version1, String version2)
			throws Exception {
		if (version1 == null || version2 == null) {
			throw new Exception("compareVersion error:illegal params.");
		}
		String[] versionArray1 = version1.split("\\.");// 注意此处为正则匹配，不能用"."；
		String[] versionArray2 = version2.split("\\.");
		int idx = 0;
		int minLength = Math.min(versionArray1.length, versionArray2.length);// 取最小长度值
		int diff = 0;
		while (idx < minLength
				&& (diff = versionArray1[idx].length()
						- versionArray2[idx].length()) == 0// 先比较长度
				&& (diff = versionArray1[idx]
						.compareTo(versionArray2[idx])) == 0) {// 再比较字符
			++idx;
		}
		// 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
		diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
		return diff;
	}

	public void logout(String username, String pwd) {
		// 跟踪用户状态
	}

	public void modifyLoginPwd(ChangeLoginPwdRequest request) throws Exception {
		int count = userInfoMapper.checkPwd(request);
		if (count <= 0) {
			throw new Exception("原密码错误！");
		}
		userInfoMapper.modifyLoginPwd(request);
	}

	public void resetLoginPwd(ResetLoginPwdRequest request) throws Exception {
		SMSCODE code = Constants.getUserSms(request.getMobile());
		if (System.currentTimeMillis() - code.getTime() > 2 * 60 * 1000) {
			throw new Exception("验证码已失效");
		}
		if (!request.getSmsCode().equals(code.getCode())) {
			throw new Exception("验证码错误！");
		}
		int result = userInfoMapper.resetLoginPwd(request);
		if (result <= 0) {
			throw new Exception("重置失败，请输入该账户绑定的初始手机号");
		}
	}

	public void modifyPersonInfo(ModifyUserInfoRequest request)
			throws Exception {
		// String code = Constants.getUserSms(request.getMobile());
		// if (!request.getSmsCode().equals(code)) {
		// throw new Exception("验证码错误！");
		// }
		UserInfo user = userInfoMapper.getUserByUserName(request.getUsername());
		user.setEmail(request.getEmail());
		user.setProvinceId(request.getProvince());
		user.setCityId(request.getCity());
		user.setIdentify(request.getIndustry());
		user.setOperateDate(DateUtil.currentTimestamp());
		user.setIndustry(request.getIndustry());
		user.setNickname(request.getNickName());
		userInfoMapper.update(user);
	}

	public PersonInfoResponse getPersonInfo(String username) {
		PersonInfoResponse response = new PersonInfoResponse();
		UserInfo userinfo = userInfoMapper.getUserByUserName(username);
		if (userinfo != null) {
			response.setUsername(userinfo.getUsername());
			response.setNickname(userinfo.getNickname());
			response.setMobile(userinfo.getMobile());
			response.setEmail(userinfo.getEmail());
			response.setProvince(userinfo.getProvinceId());
			response.setCity(userinfo.getCityId());
			response.setIndustry(userinfo.getIndustry());
			response.setUserType(userinfo.getUserType());
		}
		return response;
	}

	public void uploadPersonVerify(UploadPersonVerifyRequest request) {
		UserInfo userinfo = userInfoMapper
				.getUserByUserName(request.getUserName());
		userinfo.setRealname(request.getRealName());
		userinfo.setIdentifyFrontImg(request.getFront());
		userinfo.setIdentifyBackendImg(request.getBack());
		userinfo.setOperateDate(DateUtil.currentTimestamp());
		userinfo.setIdentify(request.getIdCard());
		userinfo.setCertificationPicture(request.getCertificationPicture());
		userInfoMapper.update(userinfo);
	}

	public PersonVerifyInfoResponse getPersonVerify(String username) {
		UserInfo userinfo = userInfoMapper.getUserByUserName(username);
		PersonVerifyInfoResponse response = new PersonVerifyInfoResponse();
		response.setRealName(userinfo.getRealname());
		response.setIdCard(userinfo.getIdentify());
		response.setFront(userinfo.getIdentifyFrontImg());
		response.setBack(userinfo.getIdentifyBackendImg());
		return response;
	}

	public void changePushMessage(ChangePushMessageRequest request) {
		// TODO Auto-generated method stub
		UserInfo userinfo = userInfoMapper
				.getUserByUserName(request.getUsername());
		userinfo.setPushMessage(request.getPushMessage() == 0 ? false : true);
		userInfoMapper.update(userinfo);
	}

	public List<ContactCustomerServiceResponse> getContactCustomerService() {
		List<ContactCustomerServiceResponse> list = new ArrayList<ContactCustomerServiceResponse>();
		ContactCustomerServiceResponse response = new ContactCustomerServiceResponse();
		response.setCustomerServiceMobile("400-133-1567");
		List<QuestionInfo> infos = new ArrayList<QuestionInfo>();
		QuestionInfo info = new QuestionInfo();
		info.setQuestionName("租赁物一般几天能邮寄到啊");
		info.setAnswer("省内一般两天，感谢您的咨询,祝您生活愉快");
		infos.add(info);
		response.setQuestionInfo(infos);
		list.add(response);
		return list;
	}

	public List<MainPageImgResponse> getMainPageImgs(MainPageRequest request)
			throws Exception {
		MainPageImgResponse response = null;
		Integer topNo = request.getTopNo();
		if (null == topNo) {
			throw new Exception("参数非法");
		}
		List<MainPageImgResponse> list = new ArrayList<MainPageImgResponse>();
		try {
			List<MainImg> mainImgs = systemMapper
					.getAll(new RowBounds(0, topNo));
			for (MainImg mainImg : mainImgs) {
				response = new MainPageImgResponse();
				response.setId(mainImg.getId());
				response.setImg(mainImg.getImg());
				list.add(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public WalletInfoResponse getWalletInfo(String username) {
		WalletInfoResponse response = new WalletInfoResponse();
		UserInfo userInfo = userInfoMapper.getUserByUserName(username);
		response.setBond(String.valueOf(userInfo.getPayMemberAmount()));
		response.setDeposit(String.valueOf(userInfo.getPayDeposit()));
		response.setBalance(String.valueOf(userInfo.getAmount()));
		return response;
	}
}
