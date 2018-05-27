package com.adidas.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Error404
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SensorType implements Serializable {

    private static final long serialVersionUID = 8604990093149376515L;

    private Long idSensorType;
    private String sensorType;

}
