package com.adidas.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Error404
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SensorData implements Serializable {

    private static final long serialVersionUID = 8604990093149376515L;

    private Long idSensorData;
    private Long idUserActivityData;
    private Long idSensorType;
    private Date updateTime;
    private Long sequence;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal axisx;
    private BigDecimal axisy;
    private BigDecimal axisz;

}
