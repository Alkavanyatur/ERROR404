package com.adidas.service;

import java.util.List;
import java.util.Optional;

import com.adidas.model.entity.Book;
import com.adidas.model.entity.BookWithBookStore;
import com.adidas.model.entity.SensorData;
import com.adidas.model.entity.UserActivityData;

/**
 * @author Error404
 */
public interface SensorDataService {

    boolean save(SensorData sensorData);

	boolean insertSensorDataList(List<SensorData> sensorDataList);

}
