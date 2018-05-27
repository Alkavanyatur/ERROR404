package com.adidas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adidas.model.entity.SensorData;
import com.adidas.repository.SensorDataRepository;
import com.adidas.service.SensorDataService;

import java.util.List;

/**
 * @author Error404
 */
@Service
public class SensorDataServiceImpl implements SensorDataService {

    private final SensorDataRepository sensorDataRepository;

    @Autowired
    public SensorDataServiceImpl(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    @Override
    @Transactional
    public boolean save(SensorData sensorData) {
        return sensorDataRepository.insert(sensorData) > 0;
    }
    
    @Override
    public boolean insertSensorDataList(List<SensorData> sensorDataList) {
        return sensorDataRepository.insertSensorDataList(sensorDataList) > 0;
    }
}
