package com.zyl.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Notice {

  private long id;
  private String uri;
  private String context;
  private String sendTime;

  public Notice() {
  }

  public Notice(long id, String uri, String context, String sendTime) {
    this.id = id;
    this.uri = uri;
    this.context = context;
    this.sendTime = sendTime;
  }
}
