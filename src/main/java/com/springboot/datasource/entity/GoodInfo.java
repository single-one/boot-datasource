package com.springboot.datasource.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tgId;
    private String tgTitle;
    private BigDecimal tgPrice;
}
