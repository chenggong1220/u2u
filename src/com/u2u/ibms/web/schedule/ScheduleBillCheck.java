package com.u2u.ibms.web.schedule;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import com.u2u.framework.config.AppConfiguration;
import com.u2u.framework.spring.SpringContextHolder;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.BillCheck;
import com.u2u.ibms.common.beans.BillCheckHistory;
import com.u2u.ibms.common.beans.Combo;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.SubOrder;
import com.u2u.ibms.common.mapper.BillCheckHistoryMapper;
import com.u2u.ibms.common.mapper.BillCheckMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.common.mapper.SubOrderMapper;
import com.u2u.ibms.web.billcheck.service.BillCheckService;

/**
 * 
 * @ClassName: ScheduleBillCheck <br>
 * @UpdateDate 2017-02-22 <br>
 * @Description: 自动出账类 <br>
 * @Updated Logic:
 * 1. 设置每月出账日期前的账单为“未出账”状态
 * 2. 未付款的账单，每次自动计算滞纳金费用
 * @author SUNZHE
 */

public class ScheduleBillCheck {

	private static Log log = LogFactory.getLog(ScheduleBillCheck.class);
	private static String SYS_BILL_OUT_DATE;
	//private static String SYS_BILL_PAY_END_DATE;
	private static Float SYS_OVERDUE_RATE;

	@Autowired
	private BillCheckService billCheckService;
	
	public void scheduleCheck() {
		//System.out.println(new Date() + "---" + System.currentTimeMillis());
		log.info("触发账单计算操作！");
		
		if (StringUtils.isEmpty(SYS_BILL_OUT_DATE)) {
			SYS_BILL_OUT_DATE = AppConfiguration.getInstance().getString("SYS_BILL_OUT_DATE");
			//SYS_BILL_PAY_END_DATE = AppConfiguration.getInstance().getString("SYS_BILL_PAY_END_DATE");
			SYS_OVERDUE_RATE = AppConfiguration.getInstance().getFloat("SYS_OVERDUE_RATE");
		}
		
		
		BillCheckMapper billCheckMapper = SpringContextHolder
				.getBean(BillCheckMapper.class);
		List<BillCheck> billChecks = billCheckMapper.getAll(new RowBounds(),
				null, null, null, "0", null);	//获得所有未付款的账单
		for (BillCheck billcheck : billChecks) {
				
				Timestamp outdate = DateUtil.currentTimestamp();
				
				String curTermOutDate = DateUtil.getDateTime(
						DateUtil.getNextMonthDate(billcheck.getCurrentTermDate()),
						SYS_BILL_OUT_DATE);
				if(DateUtil.compareDate(outdate, 
						DateUtil.string2Timestamp(curTermOutDate, DateUtil.PATTERN_STANDARD)) >0)
				{
					//当前时间大于系统规定出账时间，标记账单为已出账状态
					billcheck.setStatus(true);
					billcheck.setOutdate(outdate);
					//是否达到最低消费
					billcheck.setRentAmount(billCheckService.getRentAmountByMinAmount(billcheck));
				}
				
				//付款金额小于需交款金额，按未交款处理，需要计算滞纳金
				int overdueDays = DateUtil.compareDate(outdate,billcheck.getEnddate());
				if(billcheck.getPayAmount() < billcheck.getAllAmount()){
					if(overdueDays > 1){
						//System.out.println("Current Date: " + outdate + "; overdueDays: " + overdueDays);
						billcheck.setInterest(billcheck.getRentAmount()*overdueDays*SYS_OVERDUE_RATE);
					}
				}
				
				//得到总费用=租金+罚息
				billcheck.setAllAmount(billcheck.getRentAmount() + billcheck.getInterest());
				
				billcheck.setOperateDate(DateUtil.currentTimestamp());
				billCheckMapper.update(billcheck);
	
				BillCheckHistory history = new BillCheckHistory();
				history.setBillCheckId(billcheck.getId());
				history.setCreateDate(DateUtil.currentTimestamp());
				history.setOperateDate(DateUtil.currentTimestamp());
				SpringContextHolder.getBean(BillCheckHistoryMapper.class).insert(
						history);
			}

	}
	
}
