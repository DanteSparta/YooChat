package model;

/**
 * Created by zw531129 on 15/4/5
 * So, what's the fucking copyright?
 */
public class User {
    private Integer Id;
    private String name;
    private String url;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
