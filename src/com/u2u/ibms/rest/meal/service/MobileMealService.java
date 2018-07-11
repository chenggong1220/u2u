package com.u2u.ibms.rest.meal.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.api.internal.util.StringUtils;
import com.u2u.ibms.common.beans.Combo;
import com.u2u.ibms.common.mapper.AssetTypeMapper;
import com.u2u.ibms.common.mapper.ComboMapper;
import com.u2u.ibms.rest.meal.vo.MealInfoByAssetTypeRequest;
import com.u2u.ibms.rest.meal.vo.MealInfoByIdRequest;
import com.u2u.ibms.rest.meal.vo.MealInfoByIdResponse;
import com.u2u.ibms.rest.meal.vo.MealInfoByTypeResponse;

@Service
public class MobileMealService {
	@Autowired
	private ComboMapper comboMapper;
	@Autowired
	private AssetTypeMapper assetTypeMapper;

	public List<MealInfoByTypeResponse> getMealInfoByAssetTypeId(
			MealInfoByAssetTypeRequest request) {
		List<MealInfoByTypeResponse> list = new ArrayList<MealInfoByTypeResponse>();
		if (StringUtils.isEmpty(request.getRentType())) {
			request.setRentType("0");
		}
		List<Combo> mealInfos = comboMapper.getAll(
				Integer.valueOf(request.getRentType()),
				Integer.valueOf(request.getAssetTypeId()), null, null,
				new RowBounds());
		for (Combo mealInfo : mealInfos) {
			MealInfoByTypeResponse response = new MealInfoByTypeResponse();
			response.setId(mealInfo.getId() + "");
			response.setAccountRules(mealInfo.getAmountRule());
			response.setAttentionItem(mealInfo.getNotice());
			response.setMealName(mealInfo.getName());
			response.setMealType(mealInfo.getRentType() + "");
			response.setRentType(mealInfo.getRentType() + "");
			response.setMiniUserdTime(mealInfo.getMinimumUseTime() + "");
			response.setRate(""+mealInfo.getAmount());
			list.add(response);
		}
		return list;
	}

	public MealInfoByIdResponse getMealInfoByMealId(MealInfoByIdRequest request) {
		MealInfoByIdResponse response = new MealInfoByIdResponse();
		Combo mealInfo = comboMapper.getById(request.getMealId());
		if (mealInfo != null) {
			response.setAccountRules(mealInfo.getAmountRule());
			response.setAttentionItem(mealInfo.getNotice());
			response.setMealName(mealInfo.getName());
			response.setMealType(mealInfo.getType() + "");
			response.setMiniUserdTime(mealInfo.getMinimumUseTime() + "");
			response.setRate(""+mealInfo.getAmount());
		}
		return response;
	}

}
