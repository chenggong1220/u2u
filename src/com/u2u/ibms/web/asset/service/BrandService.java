package com.u2u.ibms.web.asset.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.AssetType;
import com.u2u.ibms.common.beans.Brand;
import com.u2u.ibms.common.beans.Combo;
import com.u2u.ibms.common.mapper.AssetTypeMapper;
import com.u2u.ibms.common.mapper.BrandMapper;
import com.u2u.ibms.common.mapper.ComboMapper;
import com.u2u.ibms.web.asset.condition.BrandCondition;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BrandService extends BaseService {

	@Autowired
	private BrandMapper brandMapper;

	@Autowired
	private ComboMapper comboMapper;

	@Autowired
	private AssetTypeMapper assetTypeMapper;

	public List<Brand> getAll(RowBounds rb, BrandCondition condition) {
		return brandMapper.getAll(rb, getStringCondition(condition.getName()));
	}

	public List<Brand> getByRentType(Integer rentType) {

		List<Combo> combos = comboMapper.getAll(rentType, null, null, null,
				new RowBounds());
		Map<Integer, Brand> map = new HashMap<Integer, Brand>();
		for (Combo combo : combos) {
			AssetType assetType = assetTypeMapper.getById(combo
					.getAssetTypeId());
			Brand brand = brandMapper.getById(assetType.getBrandId());
			if (!map.containsKey(brand.getId())) {
				map.put(brand.getId(), brand);
			}
		}

		return new ArrayList<Brand>(map.values());
	}

	public Brand getById(int id) {
		return brandMapper.getById(id);
	}

	public void insert(Brand brand) {
		brand.setCreateDate(DateUtil.currentTimestamp());
		brand.setOperateDate(DateUtil.currentTimestamp());
		brandMapper.insert(brand);
	}

	public void update(Brand brand) {
		Brand exist = brandMapper.getById(brand.getId());
		exist.setName(brand.getName());
		exist.setOperateDate(DateUtil.currentTimestamp());
		brandMapper.update(exist);
	}

	public void delete(int id) {
		brandMapper.delete(id);
	}
}
