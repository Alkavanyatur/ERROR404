package com.adidas.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.adidas.model.entity.SensorData;
import com.adidas.repository.SensorDataRepository;

import java.util.List;

/**
 * @author Error404
 */
@Mapper
public interface SensorDataMapper extends SensorDataRepository {

	@Override
	Integer insertSensorDataList(List<SensorData> sensorDataList);

}
