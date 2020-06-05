/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.springamqpexample.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author hufeng
 * @version : Order.java, v 0.1 2020年06月05日 4:49 下午 hufeng Exp $
 */
@Data
@AllArgsConstructor
@ToString
public class Order implements Serializable {
    private long   id;
    private String name;

}