package com.zyl.entity;

import lombok.Data;

@Data
public class Log {
  private long id;
  private String content;
  private String time;
  private String user;
  private String ip;

  public Log() {
  }

  public Log(long id, String content, String time, String user, String ip) {
    this.id = id;
    this.content = content;
    this.time = time;
    this.user = user;
    this.ip = ip;
  }
}
