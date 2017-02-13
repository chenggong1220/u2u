package com.u2u.ibms.web.asset.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
import com.u2u.ibms.common.mapper.ComboMapper;
import com.u2u.ibms.web.asset.condition.ComboCondition;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ComboService extends BaseService {

	@Autowired
	private ComboMapper comboMapper;

	@Autowired
	private AssetTypeService assetTypeService;

	@Autowired
	private AssetTypeMapper assetTypeMapper;

	public List<Combo> getAll(ComboCondition condition, RowBounds rb) {

		List<Integer> assetTypeIds = new ArrayList<Integer>();
		Integer assetTypeId = getIntegerCondition(condition.getAssetTypeId());
		if (assetTypeId == null) {
			Integer brandId = getIntegerCondition(condition.getBrandId());
			if (brandId != null) {
				List<AssetType> types = assetTypeMapper.getAll(rb, brandId,
						null);
				if (CollectionUtils.isEmpty(types)) {
					assetTypeIds.add(null);
				} else {
					for (AssetType type : types) {
						assetTypeIds.add(type.getId());
					}
				}
			}
		} else {
			assetTypeIds.add(assetTypeId);
		}

		List<Combo> combos = comboMapper.getAllByAssetTypeIds(
				getIntegerCondition(condition.getRentType()),
				CollectionUtils.isEmpty(assetTypeIds) ? null : assetTypeIds,
				getBooleanCondition(condition.getStarProduct()),
				getStringCondition(condition.getName()), rb);

		for (final Combo combo : combos) {
			this.convertToCombo(combo);
		}
		return combos;
	}

	public List<Combo> getByAssetTypeId(int assetTypeId, Integer rentType) {
		List<Combo> combos = comboMapper.getAll(rentType, assetTypeId, null,
				null, new RowBounds());
		for (final Combo combo : combos) {
			this.convertToCombo(combo);
		}
		return combos;
	}

	public Combo get(int id) {
		Combo combo = comboMapper.getById(id);
		this.convertToCombo(combo);
		return combo;
	}

	private Combo convertToCombo(final Combo combo) {
		combo.setAssetType(assetTypeService.getById(combo.getAssetTypeId()));
		return combo;
	}

	public void insert(Combo combo) {
		combo.setCreateDate(DateUtil.currentTimestamp());
		combo.setOperateDate(DateUtil.currentTimestamp());
		comboMapper.insert(combo);
	}

	public void update(Combo combo) {
		Combo exist = comboMapper.getById(combo.getId());
		exist.setAssetTypeId(combo.getAssetTypeId());
		exist.setRentType(combo.getRentType());
		exist.setStarProduct(combo.getStarProduct());
		exist.setName(combo.getName());
		exist.setAmount(combo.getAmount());
		exist.setMinimumUseTime(combo.getMinimumUseTime());
		exist.setAmountRule(combo.getAmountRule());
		exist.setNotice(combo.getNotice());
		exist.setOperateDate(DateUtil.currentTimestamp());
		comboMapper.update(exist);
	}

	public void delete(int id) {
		comboMapper.delete(id);
	}
}
