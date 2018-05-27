package com.adidas.service;

import java.util.List;
import java.util.Optional;

import com.adidas.model.entity.UserActivityData;

/**
 * @author Error404
 */
public interface UserActivityDataService {

    boolean save(UserActivityData userActivityData);

	boolean saveFull(UserActivityData userActivityData);
	
	public List<UserActivityData> getActivitiesByUserId(Long idUser);
	
	public List<UserActivityData> getUserActivityDataByUserActivity(Long idUser, Long idActivity);

}
