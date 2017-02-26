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


    public Order merge(final Order orderUpdate) {

        final Integer updatedUserId = orderUpdate.getUserId() <= 0 ? this.userId : orderUpdate.userId;
        final String updatedDateCreated = orderUpdate.getDateCreated() == null ? this.dateCreated : orderUpdate.dateCreated;
        final String updatedDateModified = orderUpdate.getDateModified() == null ? this.dateModified : orderUpdate.dateModified;
        final String updatedSize = orderUpdate.getSize() == null ? this.size : orderUpdate.size;
        final CoverType updatedCover = orderUpdate.getCover() == null ? this.cover : orderUpdate.cover;
        final PaperType updatedPaper = orderUpdate.getPaper() == null ? this.paper : orderUpdate.paper;
        final Integer updatedPages = orderUpdate.getPages() <= 0 ? this.pages : orderUpdate.pages;
        final double updatedPrice = orderUpdate.getPrice() <= 0.0 ? this.price : orderUpdate.price;
        final OrderStatus updatedStatus = orderUpdate.getStatus() == null ? this.status : orderUpdate.status;

        return new Order(this.id, updatedUserId, updatedDateCreated, updatedDateModified, updatedSize, updatedCover,
                updatedPaper, updatedPages, updatedPrice, updatedStatus);
    }


}


