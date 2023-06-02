package domain;

import java.util.List;

public class Post {
    private int pid;//帖子id
    private String title;//帖子标题
    private int uid;//所属用户

    private int favornum;//点赞数

    private int collectnum;//收藏数
    private int cid;
    private User user;//所属用户
    private List<Floor> floorList;//帖子所含楼层

    public Post() {
    }
    public Post(int pid, String title, int uid) {
        this.pid = pid;
        this.title = title;
        this.uid = uid;
        this.collectnum=0;
        this.favornum=0;
    }

    public Post(int pid, String title, int uid,int collectnum,int favornum) {
        this.pid = pid;
        this.title = title;
        this.uid = uid;
        this.collectnum=collectnum;
        this.favornum=favornum;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFavornum(){return favornum;}
    public  void setFavornum(int num){
        this.favornum=num;
    }
    public int getCollectnum(){return  collectnum;}
    public  void setCollectnum(int collect){
        this.collectnum=collect;
    }
    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }


}
