package com.zyl.entity;

import lombok.Data;

//留言表
@Data
public class Guestbook {

  private long id;
  private long uid;
  private long gid;
  private String content;
  private String sendTime;
  private String status;

  private String username; //用户名
  private String avater;  //用户头像

  public Guestbook() {
  }

  public Guestbook(long id, long uid, long gid, String content, String sendTime, String status) {
    this.id = id;
    this.uid = uid;
    this.gid = gid;
    this.content = content;
    this.sendTime = sendTime;
    this.status = status;
  }

  public Guestbook(long id, long uid, long gid, String content, String sendTime, String status, String username) {
    this.id = id;
    this.uid = uid;
    this.gid = gid;
    this.content = content;
    this.sendTime = sendTime;
    this.status = status;
    this.username = username;
  }

  public Guestbook(long id, long uid, long gid, String content, String sendTime, String status, String username, String avater) {
    this.id = id;
    this.uid = uid;
    this.gid = gid;
    this.content = content;
    this.sendTime = sendTime;
    this.status = status;
    this.username = username;
    this.avater = avater;
  }
}
