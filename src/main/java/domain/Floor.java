package domain;

import java.util.List;

public class Floor {
    private int fid;//楼层id
    private int order;//楼层序列号
    private int pid;//所属post
    private int uid;//楼层所属对象

    private String image;//楼层创作图片
    private String content;//楼层内容
    private String time;//时间

    private Post post;//所属post
    private User user;//楼层所属对象
    private List<Comment> commentList;//楼层评论列表

    public Floor() {
    }

    public Floor(int fid, int order, int pid, int uid, String image, String content, String time) {
        this.fid = fid;
        this.order = order;
        this.pid = pid;
        this.uid = uid;
        this.image = image;
        this.content = content;
        this.time = time;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getNum() {
        return order;
    }

    public void setNum(int order) {
        this.order = order;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
