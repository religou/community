package life.martin.community.dto;

/**
 * @createTime 2022-09-02 23:41:00
 */
public class GithubUserDTO {

    private String name;
    private Long id;
    private String bio;

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUserDTO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
