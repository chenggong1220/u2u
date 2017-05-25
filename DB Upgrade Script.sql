insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('435','1','400','4','','','项目维护','admin','2017-01-04 14:01:44','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('436','2','400','4','','','项目复核','admin','2017-01-04 14:01:55','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('437','1','403','4','','','信用审核','admin','2017-01-04 14:11:24','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('438','2','403','4','','','信用复核','admin','2017-01-04 14:11:33','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('439','1','385','4','','','签收','admin','2017-01-04 14:12:32','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('440','2','385','4','','','签约','admin','2017-01-04 14:12:42','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('441','3','385','4','','','终止','admin','2017-01-04 14:12:50','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('442','4','385','4','','','作废','admin','2017-01-04 14:12:59','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('443','1','411','4','','','发货','admin','2017-01-04 14:15:27','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('444','2','411','4','','','到货','admin','2017-01-04 14:15:34','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('445','1','419','4','','','起租','admin','2017-01-04 14:23:38','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('446','2','419','4','','','翻单','admin','2017-01-04 14:23:47','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('447','3','419','4','','','退换货','admin','2017-01-04 14:23:56','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('448','4','419','4','','','风险处置','admin','2017-01-04 14:24:11','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('449','5','419','4','','','申请延期','admin','2017-01-04 14:24:34','1');
insert into `sys_resource` (`id`, `priority`, `parent_id`, `level`, `image_url`, `link`, `description`, `update_user`, `update_time`, `if_valid`) values('450','2','317','3','','/web/mailhistory/index','邮件列表','admin','22017-01-05 22:41:09','1');



ALTER TABLE `t_rent_person_info` ADD `ID_Card_Front_img` VARCHAR(255) DEFAULT NULL COMMENT '身份证正面';
ALTER TABLE `t_rent_person_info` ADD `ID_Card_Back_Img` VARCHAR(255) DEFAULT NULL COMMENT '身份证反面';
ALTER TABLE `t_rent_person_info` ADD `Id_Card_Hand_Img` VARCHAR(255) DEFAULT NULL COMMENT '身份证手持';
 
 
ALTER TABLE `t_rent_company_info`ADD `ID_Card_Front_img` VARCHAR(255) DEFAULT NULL COMMENT '身份证正面';
ALTER TABLE `t_rent_company_info`ADD `ID_Card_Back_Img` VARCHAR(255) DEFAULT NULL COMMENT '身份证反面';
ALTER TABLE `t_rent_company_info`ADD `Id_Card_Hand_Img` VARCHAR(255) DEFAULT NULL COMMENT '身份证手持';

Alter table `t_order` ADD `del_status` tinyint(1) DEFAULT '0' COMMENT '是否删除' after `customer_manager`;

Alter table `t_rent_company_info` ADD `Comp_Code` varchar(50) DEFAULT '' COMMENT '企业代码' after `Id_Card_Hand_Img`;

Alter table `t_bill` ADD `bank_Serial_Number` varchar(64) DEFAULT '' COMMENT '银行流水号';
Alter table `t_bill` ADD `remark` varchar(255) DEFAULT '' COMMENT '备注';

Alter table `t_order` ADD  `credit_file` varchar(256) DEFAULT '' COMMENT '文件下载' after `customer_manager`;



--自动分单，为客户专员增加邮箱
ALTER TABLE `sys_user` ADD `email` VARCHAR(256) DEFAULT NULL COMMENT '邮箱' AFTER `city_id`;
ALTER TABLE `sys_user` ADD `cities` longtext DEFAULT NULL COMMENT '辖区市ID' AFTER `email`;
ALTER TABLE `t_l_city` ADD `union_code` bigint(20) DEFAULT NULL COMMENT '唯一值' AFTER `province_id`;
UPDATE t_l_city SET union_code = (id+5000);
