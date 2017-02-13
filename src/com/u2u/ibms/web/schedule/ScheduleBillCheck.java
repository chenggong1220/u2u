package com.u2u.ibms.web.schedule;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;

import com.u2u.framework.spring.SpringContextHolder;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.beans.BillCheck;
import com.u2u.ibms.common.beans.BillCheckHistory;
import com.u2u.ibms.common.mapper.BillCheckHistoryMapper;
import com.u2u.ibms.common.mapper.BillCheckMapper;

public class ScheduleBillCheck {

	private static Log log = LogFactory.getLog(ScheduleBillCheck.class);

	public void scheduleCheck() {
		//System.out.println(new Date() + "---" + System.currentTimeMillis());
		log.info("触发账单出账操作！");
		BillCheckMapper billCheckMapper = SpringContextHolder
				.getBean(BillCheckMapper.class);
		List<BillCheck> billChecks = billCheckMapper.getAll(new RowBounds(),
				null, null, false,null,null);
		for (BillCheck billcheck : billChecks) {
			billcheck.setStatus(true);

			Timestamp outdate = DateUtil.currentTimestamp();
			billcheck.setOutdate(outdate);
			billcheck.setEnddate(new Timestamp(outdate.getTime() + 30 * 24 * 60
					* 60 * 1000));

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
