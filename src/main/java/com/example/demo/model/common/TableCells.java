package com.example.demo.model.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.model.common.Item;
import lombok.Data;

/**
 * @author cxl
 * @date： 2020-11-19 15:34
 */
@Data
public class TableCells {
    @JSONField(name = "Item")
    private Item item;
}
