package com.u2u.ibms.rest.device.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u2u.ibms.common.beans.AssetType;
import com.u2u.ibms.common.beans.Combo;
import com.u2u.ibms.common.mapper.AssetTypeMapper;
import com.u2u.ibms.common.mapper.ComboMapper;
import com.u2u.ibms.rest.device.vo.AssetTypeByRenTypeRequest;
import com.u2u.ibms.rest.device.vo.AssetTypeRequest;
import com.u2u.ibms.rest.device.vo.AssetTypeResponse;
import com.u2u.ibms.rest.device.vo.StartAssetResponse;

@Service
public class MobileDeviceService {

	@Autowired
	private AssetTypeMapper assetTypeMapper;

	@Autowired
	private ComboMapper comboMapper;

	public List<StartAssetResponse> getStarDevice() {
		List<Combo> combos = comboMapper.getAll(null, null, true, null,
				new RowBounds());
		List<StartAssetResponse> list = new ArrayList<StartAssetResponse>();
		for (Combo combo : combos) {
			AssetType assetType = assetTypeMapper.getById(combo
					.getAssetTypeId());
			StartAssetResponse response = new StartAssetResponse();
			response.setId(assetType.getId() + "");
			String imgPath = assetType.getPicture();
			String end = imgPath.substring(( imgPath.lastIndexOf("/")+1),imgPath.length());
			String before = imgPath.substring(0,imgPath.lastIndexOf("/"));
			response.setImgPath(before+"/deviceTypeImgs/"+end);
			//response.setImgPath(assetType.getPicture());
			response.setModel(assetType.getModel());
			response.setType(combo.getRentType() + "");
			response.setTypeName(combo.getRentType() == 0 ? "分时租赁" : "分月租赁");
			response.setDeposit(assetType.getDeposit() + "");
			response.setControlSystem(assetType.getControlSystem());// 控制系统
			response.setMainShaftSpeed(assetType.getMainShaftSpeed());// 主轴转速
			response.setMachinePower(assetType.getMachinePower());// 主电机功率
			list.add(response);
		}
		return list;
	}

	public AssetTypeResponse getOneAssetType(AssetTypeRequest request) throws IOException {
		AssetTypeResponse response = new AssetTypeResponse();
		AssetType typeType = assetTypeMapper.getById(request.getAssetTypeId());
		String picture = typeType.getPicture();
		String end = picture.substring(( picture.lastIndexOf("/")+1),picture.length());
		String before = picture.substring(0,picture.lastIndexOf("/"));
		System.out.println(before+"/deviceTypeImgs/"+end);
		response.setPicture(before+"/deviceTypeImgs/"+end);
		response.setBrand(typeType.getBrand());
		response.setModel(typeType.getModel());
		response.setMachinePower(typeType.getMachinePower());
		response.setMoveMethod(typeType.getMoveMethod());
		response.setFinishSize(typeType.getFinishSize());
		response.setMainShaftSpeed(typeType.getMainShaftSpeed());
		response.setCutterCount(typeType.getCutterCount());
		response.setControlMethod(typeType.getControlMethod());
		response.setControlSystem(typeType.getControlSystem());
		response.setLayout(typeType.getLayout());
		response.setDriving(typeType.getDriving());
		response.setDeposit(typeType.getDeposit() + "");
		return response;
	}
	
	
	public List<AssetTypeResponse> getAssetTypeListByRenType(
			AssetTypeByRenTypeRequest request) {

		List<Combo> combos = comboMapper.getAll(request.getRentTypeId(), null,
				null, null, new RowBounds());

		List<Integer> typeIds = new ArrayList<Integer>();
		for (Combo combo : combos) {
			typeIds.add(combo.getAssetTypeId());
		}

		AssetTypeResponse response = null;
		Integer page = Integer.parseInt(request.getPage());// 当前第几页
		Integer pageSize = Integer.parseInt(request.getPageSize());// 每页显示多少条记录
		// 计算开始数
		int startNum = 0;
		if (page == 1) {
			startNum = 0;
		} else {
			startNum = (page - 1) * pageSize;
		}
		List<AssetTypeResponse> list = new ArrayList<AssetTypeResponse>();
		if (CollectionUtils.isNotEmpty(typeIds)) {
			List<AssetType> assetTypes = assetTypeMapper.getByIds(
					new RowBounds(startNum, pageSize), typeIds);
			for (AssetType assetType : assetTypes) {
				response = new AssetTypeResponse();
				response.setAssetTypeId(assetType.getId() + "");
				response.setPicture(assetType.getPicture());
				response.setBrand(assetType.getBrand());
				response.setModel(assetType.getModel());
				response.setMachinePower(assetType.getMachinePower());
				response.setMoveMethod(assetType.getMoveMethod());
				response.setFinishSize(assetType.getFinishSize());
				response.setMainShaftSpeed(assetType.getMainShaftSpeed());
				response.setCutterCount(assetType.getCutterCount());
				response.setControlMethod(assetType.getControlMethod());
				response.setControlSystem(assetType.getControlSystem());
				response.setLayout(assetType.getLayout());
				response.setDriving(assetType.getDriving());
				response.setDeposit(assetType.getDeposit() + "");
				response.setInsurance(assetType.getInsuranceAmount() + "");
				list.add(response);
			}
		}
		return list;
	}
}
