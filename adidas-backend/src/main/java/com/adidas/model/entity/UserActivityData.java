package com.adidas.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Error404
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserActivityData implements Serializable {

    private static final long serialVersionUID = 8604990093149376515L;

    private Long idUserActivityData;
    private Long idActivity;
    private Long idUser;
    private Date startTime;
    private Date endTime;
    private String activity;
    private List<SensorData> sensorDatas;
    private String formattedStartTime;
    private String formattedEndTime;

    public String getFormattedStartTime() {
    	String result = "";
    	if(startTime!=null) {
    		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    		result = format.format(startTime);
    	}
    	return result;
    }
    
    public String getFormattedEndTime() {
    	String result = "";
    	if(endTime!=null) {
    		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    		result = format.format(endTime);
    	}
    	return result;
    }
}
