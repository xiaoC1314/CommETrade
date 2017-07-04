package com.zhzx.uip.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class OperatorSchedule {

	private static final Logger logger = LoggerFactory.getLogger(OperatorSchedule.class);

//	@Autowired
//	private OperatorDao operatorDao;

	/**
	 * 解锁经办人
	 */
	@Scheduled(cron = "0 */1 * * * ?") // 每天00:00:00
	public void deBlockingOperator() {
//		logger.info("解锁经办人开始...");
//
//		Operator condition = new Operator();
//		condition.setStatus(UserStatus.LOCK.getStatus());
//		List<Operator> operatorList = operatorDao.listObject(condition);
//		if (CollectionUtils.isNotEmpty(operatorList)) {
//			for (Operator operator : operatorList) {
//				MemCachedUtil.cleanCache(UserCenterConstants.PASSWORD_ERROR_COUNT + operator.getMobile());
//			}
//		}
//		logger.info("清空经办人密码输错次数成功！");
//
//		int count = operatorDao.deLock();
//		count = count < 0 ? 0 : count;
//		logger.info("解锁经办人个数:" + count);
//
//		logger.info("解锁经办人结束...");
	}

}
