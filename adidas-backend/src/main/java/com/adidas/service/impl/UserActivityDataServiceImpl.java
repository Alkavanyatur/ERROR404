package com.adidas.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adidas.model.entity.SensorData;
import com.adidas.model.entity.UserActivityData;
import com.adidas.repository.SensorDataRepository;
import com.adidas.repository.UserActivityDataRepository;
import com.adidas.service.UserActivityDataService;
import com.adidas.util.PageUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Error404
 */
@Service
public class UserActivityDataServiceImpl implements UserActivityDataService {

    private final UserActivityDataRepository userActivityDataRepository;
    private final SensorDataRepository sensorDataRepository;
    
    @Autowired
    public UserActivityDataServiceImpl(UserActivityDataRepository userActivityDataRepository, SensorDataRepository sensorDataRepository) {
        this.userActivityDataRepository = userActivityDataRepository;
        this.sensorDataRepository = sensorDataRepository;
    }
    
    

    @Override
    @Transactional
    public boolean save(UserActivityData userActivityData) {
    	boolean result = true;
       
    	result = userActivityDataRepository.insert(userActivityData) > 0;
    
        return result;
    }
    
    @Override
    @Transactional
    public boolean saveFull(UserActivityData userActivityData) {
    	boolean result = true;
       
    	result = userActivityDataRepository.insert(userActivityData) > 0;
    	
    	for (SensorData sensorData : userActivityData.getSensorDatas()) {
    		sensorData.setIdUserActivityData(userActivityData.getIdUserActivityData());
		}
    	result = sensorDataRepository.insertSensorDataList(userActivityData.getSensorDatas()) > 0; 
        return result;
    }
    
    @Override
    public List<UserActivityData> getActivitiesByUserId(Long idUser) {
        return userActivityDataRepository.selectActivitiesByUserId(idUser);
    }
    
    @Override
    public List<UserActivityData> getUserActivityDataByUserActivity(Long idUser, Long idActivity) {
        return userActivityDataRepository.selectUserActivityDataByUserActivity(idUser, idActivity);
    }
    
}
