package com.u2u.ibms.web.customer.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.service.AuthorizeService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.AutoSubOrderService;
import com.u2u.ibms.common.Constants;
import com.u2u.ibms.common.CustomerName;
import com.u2u.ibms.common.beans.AssetType;
import com.u2u.ibms.common.beans.Customer;
import com.u2u.ibms.common.beans.IdentifyCertification;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.Project;
import com.u2u.ibms.common.beans.RentCompanyInfo;
import com.u2u.ibms.common.beans.RentPersonInfo;
import com.u2u.ibms.common.beans.SubOrder;
import com.u2u.ibms.common.beans.location.City;
import com.u2u.ibms.common.beans.location.Province;
import com.u2u.ibms.common.mapper.AssetTypeMapper;
import com.u2u.ibms.common.mapper.ComboMapper;
import com.u2u.ibms.common.mapper.CustomerMapper;
import com.u2u.ibms.common.mapper.IdentifyCertificationMapper;
import com.u2u.ibms.common.mapper.LocationMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.common.mapper.ProjectMapper;
import com.u2u.ibms.common.mapper.RentCompanyMapper;
import com.u2u.ibms.common.mapper.RentPersonMapper;
import com.u2u.ibms.common.mapper.SubOrderMapper;
import com.u2u.ibms.common.util.CommonIdGenerator;
import com.u2u.ibms.web.customer.condition.CustomerCondition;
import com.u2u.ibms.web.order.condition.OrderCondition;
import com.u2u.ibms.web.project.bean.OrderStatistics;

/**
 * Title: [Customer] <br>
 * Description:[客户管理操作类] <br>
 * Date: 2017年02月16日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author SUNZHE
 */

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CustomerService extends BaseService {

	@Autowired
	private RentPersonMapper rentPersonMapper;

	@Autowired
	private RentCompanyMapper rentCompanyMapper;
	
	@Autowired
	private CustomerMapper customerMapper;	
	
	@Autowired
	private OrderMapper orderMapper;	
	

	
	public void insert(HttpServletRequest request, 
			RentCompanyInfo rentCompanyInfo, RentPersonInfo rentPersonInfo) {
		String rentPersonType = request.getParameter("rentPersonType");

		if (rentPersonType.equals("0")) {
			//个人客户
			rentPersonInfo
					.setName(this.getStartComma(rentPersonInfo.getName()));
			rentPersonInfo.setAddress(this.getStartComma(rentPersonInfo
					.getAddress()));
			rentPersonInfo.setPostcode(this.getStartComma(rentPersonInfo
					.getPostcode()));
			rentPersonInfo.setCreateDate(DateUtil.currentTimestamp());
			rentPersonInfo.setOperateDate(DateUtil.currentTimestamp());
			rentPersonMapper.insert(rentPersonInfo);
		} else {
			//企业客户
			rentCompanyInfo
					.setName(this.getEndComma(rentCompanyInfo.getName()));
			rentCompanyInfo.setAddress(this.getEndComma(rentCompanyInfo
					.getName()));
			rentCompanyInfo.setPostcode(this.getEndComma(rentCompanyInfo
					.getPostcode()));
			rentCompanyInfo.setCreateDate(DateUtil.currentTimestamp());
			rentCompanyInfo.setOperateDate(DateUtil.currentTimestamp());
			//解决jsp页面input标签name同名问题
			rentCompanyInfo.setIdCardFrontImg(rentCompanyInfo.getC_idCardFrontImg());
			rentCompanyInfo.setIdCardBackImg(rentCompanyInfo.getC_idCardBackImg());
			rentCompanyInfo.setIdCardHandImg(rentCompanyInfo.getC_idCardHandImg());
			rentCompanyMapper.insert(rentCompanyInfo);
		}
	}
	
	
	public void update(HttpServletRequest request, 
			RentCompanyInfo rentCompanyInfo, RentPersonInfo rentPersonInfo) {
		String rentPersonType = request.getParameter("rentPersonType");

		if (rentPersonType.equals("0")) {
			//个人客户
			rentPersonInfo
					.setName(this.getStartComma(rentPersonInfo.getName()));
			rentPersonInfo.setAddress(this.getStartComma(rentPersonInfo
					.getAddress()));
			rentPersonInfo.setPostcode(this.getStartComma(rentPersonInfo
					.getPostcode()));
			rentPersonInfo.setCreateDate(DateUtil.currentTimestamp());
			rentPersonInfo.setOperateDate(DateUtil.currentTimestamp());
			rentPersonMapper.insert(rentPersonInfo);
		} else {
			//企业客户
			rentCompanyInfo
					.setName(this.getEndComma(rentCompanyInfo.getName()));
			rentCompanyInfo.setAddress(this.getEndComma(rentCompanyInfo
					.getName()));
			rentCompanyInfo.setPostcode(this.getEndComma(rentCompanyInfo
					.getPostcode()));
			rentCompanyInfo.setCreateDate(DateUtil.currentTimestamp());
			rentCompanyInfo.setOperateDate(DateUtil.currentTimestamp());
			//解决jsp页面input标签name同名问题
			rentCompanyInfo.setIdCardFrontImg(rentCompanyInfo.getC_idCardFrontImg());
			rentCompanyInfo.setIdCardBackImg(rentCompanyInfo.getC_idCardBackImg());
			rentCompanyInfo.setIdCardHandImg(rentCompanyInfo.getC_idCardHandImg());
			rentCompanyMapper.insert(rentCompanyInfo);
		}
	}	
	
	public Customer getById(String id, String custType) {
		Customer customer = customerMapper.getById(id,custType);		
		if(custType.equals("0"))
		{
			RentPersonInfo rentPersonInfo = rentPersonMapper.getById(Integer.parseInt(id));
			System.out.println(rentPersonInfo.getIdCardFrontImg() + "==========" + rentPersonInfo.getIdCardImg());
			customer.setRentPersonInfo(rentPersonInfo);
		}else{
			RentCompanyInfo rentCompanyInfo = rentCompanyMapper.getById(Integer.parseInt(id));
			customer.setRentCompanyInfo(rentCompanyInfo);
		}
		
		return customer;
	}	
	
	public List<Customer> getAll(RowBounds rb, CustomerCondition condition,
			Boolean assigned) {
		List<Customer> customers = customerMapper.getAll(rb);
/*		
		for (final Customer customer : customers) {
			this.convertOrder(customer);
		}
*/		
		return customers;
	}	
	
	private String getStartComma(String src) {
		if (StringUtils.isNotEmpty(src)) {
			return src.substring(0, src.indexOf(","));
		} else {
			return null;
		}
	}

	private String getEndComma(String src) {
		if (StringUtils.isNotEmpty(src)) {
			return src.substring(src.indexOf(",") + 1);
		} else {
			return null;
		}
	}
}
