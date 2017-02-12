package com.photobook.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by masya on 2/7/17.
 */
public class Order {
    private final String id;
    private final int userId;
    private final String dateCreated;
    private final String dateModified;
    private final String size;
    private final CoverType cover;
    private final PaperType paper;
    private final int pages;
    private final double price;
    private final OrderStatus status;

    @JsonCreator
    public Order(@JsonProperty("id") String id,
                 @JsonProperty("userId")int userId,
                 @JsonProperty("dateCreated") String dateCreated,
                 @JsonProperty("dateModified") String dateModified,
                 @JsonProperty("size") String size,
                 @JsonProperty("cover")CoverType cover,
                 @JsonProperty("paper") PaperType paper,
                 @JsonProperty("pages") int pages,
                 @JsonProperty("price") double price,
                 @JsonProperty("status") OrderStatus status)
    {
        this.id = id;
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.size = size;
        this.cover = cover;
        this.paper = paper;
        this.pages = pages;
        this.price = price;
        this.status = status;

    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("userId")
    public int getUserId() {
        return userId;
    }

    @JsonProperty("dateCreated")
    public String getDateCreated() {
        return dateCreated;
    }

    @JsonProperty("dateModified")
    public String getDateModified() {
        return dateModified;
    }

    @JsonProperty("size")
    public String getSize() {
        return size;
    }

    @JsonProperty("cover")
    public CoverType getCover() {
        return cover;
    }

    @JsonProperty("pages")
    public int getPages() {
        return pages;
    }

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("status")
    public OrderStatus getStatus() {
        return status;
    }

    @JsonProperty("paper")
    public PaperType getPaper() {
        return paper;
    }


}


