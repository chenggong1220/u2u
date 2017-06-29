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
import com.u2u.ibms.common.beans.Asset;
import com.u2u.ibms.common.beans.AssetType;
import com.u2u.ibms.common.beans.location.City;
import com.u2u.ibms.common.beans.location.Province;
import com.u2u.ibms.common.mapper.AssetMapper;
import com.u2u.ibms.common.mapper.AssetTypeMapper;
import com.u2u.ibms.common.mapper.LocationMapper;
import com.u2u.ibms.web.asset.condition.AssetCondition;
import com.u2u.ibms.web.shop.mapper.ShopMapper;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AssetService extends BaseService {

	@Autowired
	private AssetMapper assetMapper;

	@Autowired
	private LocationMapper locationMapper;

	@Autowired
	private AssetTypeMapper assetTypeMapper;

	@Autowired
	private ShopMapper shopMapper;

	public List<Asset> getAll(AssetCondition condition, RowBounds rb) {
		Integer brandId = getIntegerCondition(condition.getBrandId());
		List<Integer> list = new ArrayList<Integer>();
		if (brandId == null) {
			if (getIntegerCondition(condition.getAssetTypeId()) != null) {
				list.add(getIntegerCondition(condition.getAssetTypeId()));
			}
		} else {
			if (getIntegerCondition(condition.getAssetTypeId()) != null) {
				list.add(getIntegerCondition(condition.getAssetTypeId()));
			} else {
				List<AssetType> assetTypes = assetTypeMapper.getAll(
						new RowBounds(), brandId, null);
				if (CollectionUtils.isEmpty(assetTypes)) {
					list.add(null);
				} else {
					for (AssetType assetType : assetTypes) {
						list.add(assetType.getId());
					}
				}
			}
		}

		List<Asset> assets = assetMapper.getAll(
				CollectionUtils.isNotEmpty(list) ? list : null,
				getIntegerCondition(condition.getRent()),
				getIntegerCondition(condition.getProvinceId()),
				getIntegerCondition(condition.getCityId()), rb);

		for (final Asset asset : assets) {
			this.convert(asset);
		}
		return assets;
	}

	//Start: Add this function for filtering the data with more conditions, SUNZHE, 2017-06-19
	public List<Asset> getSearchedAll(AssetCondition condition, RowBounds rb) {
		Integer brandId = getIntegerCondition(condition.getBrandId());
		List<Integer> list = new ArrayList<Integer>();
		if (brandId == null) {
			if (getIntegerCondition(condition.getAssetTypeId()) != null) {
				list.add(getIntegerCondition(condition.getAssetTypeId()));
			}
		} else {
			if (getIntegerCondition(condition.getAssetTypeId()) != null) {
				list.add(getIntegerCondition(condition.getAssetTypeId()));
			} else {
				List<AssetType> assetTypes = assetTypeMapper.getAll(
						new RowBounds(), brandId, null);
				if (CollectionUtils.isEmpty(assetTypes)) {
					list.add(null);
				} else {
					for (AssetType assetType : assetTypes) {
						list.add(assetType.getId());
					}
				}
			}
		}

		List<Asset> assets = assetMapper.getSearchedAll(
				CollectionUtils.isNotEmpty(list) ? list : null,
				getIntegerCondition(condition.getRent()),
				getIntegerCondition(condition.getProvinceId()),
				getIntegerCondition(condition.getCityId()), 
				getStringCondition(condition.getAssetCode()), 
				getStringCondition(condition.getAssetLocation()), 
				getIntegerCondition(condition.getAssetShopId()), 
				rb);

		for (final Asset asset : assets) {
			this.convert(asset);
		}
		return assets;
	}
	//End: Add this function for filtering the data with more conditions, SUNZHE, 2017-06-19
	
	public Asset getById(int id) {
		return this.convert(assetMapper.getById(id));
	}

	public Asset convert(final Asset asset) {
		asset.setAssetType(assetTypeMapper.getById(asset.getAssetTypeId()));
		Province province = locationMapper.getProvinceById(asset
				.getProvinceId());
		if (province != null) {
			asset.setProvince(province.getName());
		} else {
			asset.setProvince("");
		}
		City city = locationMapper.getCityById(asset.getCityId());
		if (city != null) {
			asset.setCity(city.getName());
		} else {
			asset.setCity("");
		}
		asset.setShop(shopMapper.getById(asset.getShopId()));
		return asset;
	}

	public void insert(Asset asset) {
		asset.setCreateDate(DateUtil.currentTimestamp());
		asset.setOperateDate(DateUtil.currentTimestamp());
		assetMapper.insert(asset);
	}

	public void update(Asset asset) {
		Asset exist = assetMapper.getById(asset.getId());
		exist.setAssetTypeId(asset.getAssetTypeId());
		exist.setCode(asset.getCode());
		exist.setShopId(asset.getShopId());
		exist.setProvinceId(asset.getProvinceId());
		exist.setCityId(asset.getCityId());
		exist.setUseTime(asset.getUseTime());
		exist.setRent(asset.getRent());
		exist.setLocation(asset.getLocation());
		exist.setSoftDog(asset.getSoftDog());
		exist.setInsurance(asset.isInsurance());
		exist.setInsuranceId(asset.getInsuranceId());
		exist.setInsuranceAmount(asset.getInsuranceAmount());
		exist.setInsuranceStart(asset.getInsuranceStart());
		exist.setInsuranceEnd(asset.getInsuranceEnd());
		exist.setOperateDate(DateUtil.currentTimestamp());
		assetMapper.update(exist);
	}

	public void delete(int id) {
		assetMapper.delete(id);
	}
	
	//Start: 租赁物明细报表, SUNZHE, 2017-02-15
	public List<Asset> getByContract(AssetCondition condition, RowBounds rb) {
		Integer brandId = getIntegerCondition(condition.getBrandId());
		List<Integer> list = new ArrayList<Integer>();
		if (brandId == null) {
			if (getIntegerCondition(condition.getAssetTypeId()) != null) {
				list.add(getIntegerCondition(condition.getAssetTypeId()));
			}
		} else {
			if (getIntegerCondition(condition.getAssetTypeId()) != null) {
				list.add(getIntegerCondition(condition.getAssetTypeId()));
			} else {
				List<AssetType> assetTypes = assetTypeMapper.getAll(
						new RowBounds(), brandId, null);
				if (CollectionUtils.isEmpty(assetTypes)) {
					list.add(null);
				} else {
					for (AssetType assetType : assetTypes) {
						list.add(assetType.getId());
					}
				}
			}
		}

		List<Asset> assets = assetMapper.getByContract(
				CollectionUtils.isNotEmpty(list) ? list : null,
				getBooleanCondition(condition.getRent()),
				getIntegerCondition(condition.getProvinceId()),
				getIntegerCondition(condition.getCityId()), rb);

		for (final Asset asset : assets) {
			this.convert(asset);
		}
		return assets;
	}
	//End: 租赁物明细报表, SUNZHE, 2017-02-15
}
