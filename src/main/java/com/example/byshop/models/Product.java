package com.example.byshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long ID;
    @Column(name = "title")
    private String title;
    @Column(name = "description",columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "city")
    private String city;

@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="product")
private List<Image> images=new ArrayList<>();
private Long previewImageId;
@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
@JoinColumn
private User user;
private LocalDateTime dateOfCreated;
@PrePersist
private void init(){
    dateOfCreated=LocalDateTime.now();
}
public void addImageToProduct(Image image){
    image.setProduct(this);
    images.add(image);
}

    public Product() {
    }

    public Product(String title, String description, int price, String city, String author, Long ID) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.city = city;
        this.ID = ID;
    }


    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public String getCity() {
        return this.city;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        final Product other = (Product) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        if (this.getPrice() != other.getPrice()) return false;
        final Object this$city = this.getCity();
        final Object other$city = other.getCity();
        if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false;

        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Product;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        result = result * PRIME + this.getPrice();
        final Object $city = this.getCity();
        result = result * PRIME + ($city == null ? 43 : $city.hashCode());

        return result;
    }

    public String toString() {
        return "Product(title=" + this.getTitle() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ", city=" + this.getCity() +    ")";
    }

    public Long getID() {
        return this.ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
