package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private boolean sold;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "body_id")
    private Body body;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ad_id")
    private List<Photo> photos = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Ad of(String description, boolean sold, Body body, Brand brand, User user) {
        Ad ad = new Ad();
        ad.setBody(body);
        ad.setBrand(brand);
        ad.setDescription(description);
        ad.setSold(sold);
        ad.setUser(user);
        ad.setCreated(new Date(System.currentTimeMillis()));
        return ad;
    }

    public void addPhoto(Photo photo) {
        this.photos.add(photo);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Ad{"
                + "id=" + id + '\''
                + ", description='" + description + '\''
                + ", sold=" + sold + '\''
                + ", created=" + created + '\''
                + ", brand=" + brand + '\''
                + ", body=" + body + '\''
                + ", photos=" + photos + '\''
                + ", user=" + user + '\''
                + '}';
    }
}
