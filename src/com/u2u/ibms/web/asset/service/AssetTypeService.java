package com.u2u.ibms.web.asset.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.AssetType;
import com.u2u.ibms.common.beans.Combo;
import com.u2u.ibms.common.mapper.AssetTypeMapper;
import com.u2u.ibms.common.mapper.BrandMapper;
import com.u2u.ibms.common.mapper.ComboMapper;
import com.u2u.ibms.web.asset.condition.AssetTypeCondition;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AssetTypeService extends BaseService {

	@Autowired
	private AssetTypeMapper assetTypeMapper;

	@Autowired
	private BrandMapper brandMapper;

	@Autowired
	private ComboMapper comboMapper;

	public List<AssetType> getAll(RowBounds rb, AssetTypeCondition condition) {
		return assetTypeMapper.getAll(rb,
				getIntegerCondition(condition.getBrandId()),
				getStringCondition(condition.getModel()));
	}

	public List<AssetType> getByRentType(Integer brandId, Integer rentType) {
		List<AssetType> ret = new ArrayList<AssetType>();
		List<Combo> combos = comboMapper.getAll(rentType, null, null, null,
				new RowBounds());
		for (Combo combo : combos) {
			AssetType assetType = assetTypeMapper.getById(combo
					.getAssetTypeId());
			if (brandId.equals(assetType.getBrandId())) {
				ret.add(assetType);
			}
		}
		return ret;
	}

	public AssetType getById(int id) {
		return assetTypeMapper.getById(id);
	}

	public void insert(AssetType assetType) {
		assetType.setBrand(brandMapper.getById(assetType.getBrandId())
				.getName());
		assetType.setCreateDate(DateUtil.currentTimestamp());
		assetType.setOperateDate(DateUtil.currentTimestamp());
		assetTypeMapper.insert(assetType);
	}

	public void update(AssetType assetType) {
		AssetType exist = assetTypeMapper.getById(assetType.getId());
		// exist.setRentType(assetType.getRentType());
		exist.setBrandId(assetType.getBrandId());
		exist.setBrand(brandMapper.getById(assetType.getBrandId()).getName());
		exist.setModel(assetType.getModel());
		exist.setMachinePower(assetType.getMachinePower());
		exist.setMoveMethod(assetType.getMoveMethod());
		exist.setFinishSize(assetType.getFinishSize());
		exist.setMainShaftSpeed(assetType.getMainShaftSpeed());
		exist.setCutterCount(assetType.getCutterCount());
		exist.setControlMethod(assetType.getControlMethod());
		exist.setControlSystem(assetType.getControlSystem());
		exist.setLayout(assetType.getLayout());
		exist.setDriving(assetType.getDriving());
		exist.setPicture(assetType.getPicture());
		exist.setDeposit(assetType.getDeposit());
		exist.setAmount(assetType.getAmount());
		exist.setOperateDate(DateUtil.currentTimestamp());
		//modify be dean
		exist.setInsuranceAmount(assetType.getInsuranceAmount());
		assetTypeMapper.update(exist);
	}

	public void delete(int id) {
		assetTypeMapper.delete(id);
	}
}
