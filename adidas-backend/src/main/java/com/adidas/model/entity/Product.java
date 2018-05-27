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
public class Product implements Serializable {

    private static final long serialVersionUID = 8604990093149376515L;

    private Long idProduct;
    private String product;
    private String weburl;
    private String imgurl;
    private BigDecimal prize;
    private Long idActivity;



}
