package com.u2u.ibms.rest.device.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseRestController;
import com.u2u.framework.beans.MobileResponse;
import com.u2u.ibms.rest.device.service.MobileDeviceService;
import com.u2u.ibms.rest.device.vo.AssetTypeByRenTypeRequest;
import com.u2u.ibms.rest.device.vo.AssetTypeRequest;

@Controller
@RequestMapping("/mobile/device")
public class MobileAssetController extends BaseRestController {

	@Autowired
	private MobileDeviceService mobileDeviceService;
	/**
	 * 4.1.1 获取明星设备列表
	 * @param username
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getStarAsset", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getStarAsset() throws Exception {
		return success(mobileDeviceService.getStarDevice());
	}
	/**
	 * 	4.1.2 通过设备类型ID获取设备类型信息
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOneAssetType/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getOneAssetType(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody AssetTypeRequest assetTypeRequest) throws Exception {
		return success(mobileDeviceService.getOneAssetType(assetTypeRequest));
	}
	/**
	 * 4.1.3 通过租赁类型获取设备类型列表
	 * @param username
	 * @param pwd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAssetTypeListByRenType/{username}/{pwd}", method = RequestMethod.POST)
	@ResponseBody
	public MobileResponse getAssetTypeListByRenType(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "pwd") String pwd,
			@RequestBody AssetTypeByRenTypeRequest request) throws Exception {
		return success(mobileDeviceService.getAssetTypeListByRenType(request));
	}
}
