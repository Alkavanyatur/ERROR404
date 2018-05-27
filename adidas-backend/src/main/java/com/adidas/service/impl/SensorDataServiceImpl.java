package com.adidas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adidas.model.entity.Book;
import com.adidas.model.entity.BookWithBookStore;
import com.adidas.model.entity.SensorData;
import com.adidas.model.entity.UserActivityData;
import com.adidas.repository.BookRepository;
import com.adidas.repository.SensorDataRepository;
import com.adidas.repository.UserActivityDataRepository;
import com.adidas.service.BookService;
import com.adidas.service.SensorDataService;
import com.adidas.service.UserActivityDataService;
import com.adidas.util.PageUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
