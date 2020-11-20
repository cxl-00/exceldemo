package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.model.common.DataSources;
import com.example.demo.model.page.FixedPage;
import lombok.Data;

import java.util.List;

/**
 * @author cxl
 * @date： 2020-11-19 15:34
 */
@Data
public class PageReportData {

    @JSONField(name = "DataSources")
    private List<DataSources> dataSources;

    @JSONField(name = "FixedPage")
    private FixedPage fixedPage;

}
