package com.u2u.ibms.common;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class Constants {
         // 操作成功
         public final static String SUCCESS = "0";// 操作成功
         // 操作失败
         public final static String OPERATION_ERROR = "-1";
         // 租赁类型（个人租赁）
         public final static String PERSON_RENT_TYPE = "0";
         // 租赁类型（企业租赁）
         public final static String COMPANY_RENT_TYPE = "1";
         // 租赁方式（分时租赁）
         public final static Integer TIME__RENT = 1;
         // 租赁方式（按月租赁）
         public final static Integer MONTH_RENT = 2;
         // 租赁方式（按月租赁）
         public final static String APPLY = "租赁申请";
         public static final String ORDER_1_APPLY = "租赁申请";// 租赁申请
         public static final String ORDER_2_ORDER_HANDLE = "订单处理";// 订单处理
         public static final String ORDER_3_PROJECT_HANDLE = "项目处理";// 项目处理
         public static final String ORDER_4_PROJECT_CHECK = "项目复核";// 项目复核
         public static final String ORDER_5_CREDIT_CHECK = "信审";// 信审
         public static final String ORDER_6_CREDIT_MULTI_CHECK = "信审复核";// 信审复核
         public static final String ORDER_7_CONTRACT_RECEIVE = "合同到司确认";// 合同到司确认
         public static final String ORDER_8_CONTRACT_SIGNOFF = "合同签约";// 合同签约
         // 押金核销改成保证金核销 chenjianfei
         public static final String ORDER_9_CONTRACT_BILL_CHECK = "保证金核销";// 保证金核销
         public static final String ORDER_10_ASSET_SEND = "发货";// 发货
         public static final String ORDER_11_ASSET_RECEIVE = "到货确认";// 到货确认

         public static final String ORDER_000_ABANDONED = "订单废弃";// 到货确认
         public static final String ORDER_001_CREDIT_ABANDONED = "信审拒绝";// 到货确认

         public static final Integer DINGDING_1_APPLY = 1;// 租赁申请
         public static final Integer DINGDING_2_ORDER_HANDLE = 2;// 订单处理
         public static final Integer DINGDING_3_PROJECT_HANDLE = 3;// 项目处理
         public static final Integer DINGDING_4_PROJECT_CHECK = 4;// 项目复核
         public static final Integer DINGDING_5_CREDIT_CHECK = 5;// 信审
         public static final Integer DINGDING_6_CREDIT_MULTI_CHECK = 6;// 信审复核
         public static final Integer DINGDING_7_CONTRACT_RECEIVE = 7;// 合同到司确认
         public static final Integer DINGDING_8_CONTRACT_SIGNOFF = 8;// 合同签约
         public static final Integer DINGDING_9_CONTRACT_BILL_CHECK = 9;// 保证金核销
         public static final Integer DINGDING_10_ASSET_SEND = 10;// 发货
         public static final Integer DINGDING_11_ASSET_RECEIVE = 11;// 到货确认

         public static final String ROLE_CUSTOMER_OPERATOR = "客户专员";
         private static Constants constants = null;
         private static Map<String, SMSCODE> customerSms = null;

         private Constants() {
                   customerSms = new ConcurrentHashMap<String, SMSCODE>();
                   new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                     Iterator<Entry<String, SMSCODE>> iterator = customerSms
                                                        .entrySet().iterator();
                                     while (iterator.hasNext()) {
                                               Entry<String, SMSCODE> iterEntry = iterator.next();
                                               if (System.currentTimeMillis()
                                                                 - iterEntry.getValue().getTime() > 2 * 60 * 1000) {
                                                        iterator.remove();
                                               }
                                     }
                            }
                   }, 0, 60 * 1000);
         }

         public static synchronized Constants getInstance() {
                   if (constants == null) {
                            constants = new Constants();
                   }
                   return constants;
         }

         public static void removeUserSms(String key) {
                   Constants.getInstance().removeUserSmsInner(key);
         }

         public static void setUserSms(String key, String value) {
                   Constants.getInstance().setUserSmsInner(key, value);
         }

         public static SMSCODE getUserSms(String key) {
                   return Constants.getInstance().getUserSmsInner(key);
         }

         private void removeUserSmsInner(String key) {
                   customerSms.remove(key);
         }

         private void setUserSmsInner(String key, String value) {
                   customerSms.put(key, new SMSCODE(value, System.currentTimeMillis()));
         }

         private SMSCODE getUserSmsInner(String key) {
                   return customerSms.get(key);
         }

         public class SMSCODE {

                   public SMSCODE(String code, long time) {
                            super();
                            this.code = code;
                            this.time = time;
                   }

                   private String code;
                   private long time;

                   public String getCode() {
                            return code;
                   }

                   public void setCode(String code) {
                            this.code = code;
                   }

                   public long getTime() {
                            return time;
                   }

                   public void setTime(long time) {
                            this.time = time;
                   }

         }
}
