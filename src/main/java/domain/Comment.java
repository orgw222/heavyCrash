package domain;

public class Comment {
    private int cid;//id号
    private int fid;//所属楼层
    private int uid;//所属用户
    private String time;//发表时间
    private String content;//内容

    private Floor floor;//所属楼层
    private User user;//所属用户

    public Comment() {
    }

    public Comment(int cid, int fid, int uid, String time, String content) {
        this.cid = cid;
        this.fid = fid;
        this.uid = uid;
        this.time = time;
        this.content = content;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
