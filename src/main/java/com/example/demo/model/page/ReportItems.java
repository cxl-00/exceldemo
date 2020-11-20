package com.example.demo.model.page;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.model.common.Header;
import com.example.demo.model.page.Details;
import lombok.Data;


/**
 * @author cxl
 * @date： 2020-11-19 15:34
 */
@Data
public class ReportItems {
    @JSONField(name = "Header")
    private Header header;
    @JSONField(name = "Details")
    private Details details;
}
