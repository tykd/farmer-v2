/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jctl.cloud.manager.plant.dao;

import com.jctl.cloud.common.persistence.CrudDao;
import com.jctl.cloud.common.persistence.annotation.MyBatisDao;
import com.jctl.cloud.manager.plant.entity.PlantVariety;
import com.jctl.cloud.modules.sys.entity.User;

import java.util.List;

/**
 * 作物管理DAO接口
 * @author ll
 * @version 2017-02-25
 */
@MyBatisDao
public interface PlantVarietyDao extends CrudDao<PlantVariety> {

    List<PlantVariety> findListByUser(User user);
}