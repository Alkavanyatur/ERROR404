package com.adidas.repository;

import java.util.List;

import com.adidas.model.entity.SensorData;

/**
 * @author Error404
 */
public interface SensorDataRepository {

    Integer insert(SensorData sensorData);
    
	Integer insertSensorDataList(List<SensorData> sensorDataList);

}
