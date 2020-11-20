package com.example.demo.model.page;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.model.common.TableCells;
import lombok.Data;

import java.util.List;

/**
 * @author cxl
 * @date： 2020-11-19 15:34
 */
@Data
public class TableRows {
    @JSONField(name="TableCells")
    private List<TableCells> tableCells;
}
