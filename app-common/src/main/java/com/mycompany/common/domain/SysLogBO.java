package com.mycompany.common.domain;

import lombok.Data;

/**
 * @author john
 */

@Data
public class SysLogBO {
 
    private String className;
 
    private String methodName;
 
    private String params;
 
    private Long exeuTime;
 
    private String remark;
 
    private String createDate;
}
