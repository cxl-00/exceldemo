package com.example.demo.model.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.model.page.TableRows;
import lombok.Data;

import java.util.List;

/**
 * @author cxl
 * @date： 2020-11-19 15:34
 */
@Data
public class Header {
    @JSONField(name = "TableRows")
    private List<TableRows> tableRows;
}
