package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author cxl
 * @date： 2020-11-19 15:34
 */
@Data
public class Item {
    @JSONField(name = "Value")
    private String value;
}
