package com.zyl.entity;

import lombok.Data;

@Data
public class Friend {

  private long uid;
  private long fuid;

  public Friend() {
  }

  public Friend(long uid, long fuid) {
    this.uid = uid;
    this.fuid = fuid;
  }
}
