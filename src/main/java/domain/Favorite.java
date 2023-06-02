package domain;

public class Favorite {
    private int pid;//帖子对象
    private int uid;//所属用户

    private Post post;//帖子对象
    private User user;//所属用户

    public Favorite() {
    }

    public Favorite(int pid, int uid) {
        this.pid = pid;
        this.uid = uid;
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
}
