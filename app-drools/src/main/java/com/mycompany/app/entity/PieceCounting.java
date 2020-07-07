package com.mycompany.app.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 计件 实体
 * @author john
 */

@ToString
public class PieceCounting implements Serializable {

  // POS收银\计件 0,1 计时 计件

  private String pieceRule = "1";

  //任务类型

  private String taskType;

  //省区计件任务单价

  private String provinceLevel;

  //市区计件任务单价

  private String cityLevel;

  //县区计件任务单价

  private String countyLevel;


  public String getPieceRule() {
    return pieceRule;
  }

  public void setPieceRule(String pieceRule) {
    this.pieceRule = pieceRule;
  }

  public String getTaskType() {
    return taskType;
  }

  public void setTaskType(String taskType) {
    this.taskType = taskType;
  }

  public String getProvinceLevel() {
    return provinceLevel;
  }

  public void setProvinceLevel(String provinceLevel) {
    this.provinceLevel = provinceLevel;
  }

  public String getCityLevel() {
    return cityLevel;
  }

  public void setCityLevel(String cityLevel) {
    this.cityLevel = cityLevel;
  }

  public String getCountyLevel() {
    return countyLevel;
  }

  public void setCountyLevel(String countyLevel) {
    this.countyLevel = countyLevel;
  }

  public PieceCounting(String pieceRule, String taskType, String provinceLevel, String cityLevel, String countyLevel) {
    this.pieceRule = pieceRule;
    this.taskType = taskType;
    this.provinceLevel = provinceLevel;
    this.cityLevel = cityLevel;
    this.countyLevel = countyLevel;
  }

  public PieceCounting(String pieceRule, String taskType, String provinceLevel) {
    this.pieceRule = pieceRule;
    this.taskType = taskType;
    this.provinceLevel = provinceLevel;
  }

  public PieceCounting() {
    super();
  }
}
