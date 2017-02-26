package com.photobook.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Stores information about a user.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {


    private final int id;
    private final String name;
    private final String email;
    private final String phone;
    private final String deliveryAddress;
    private final String postalCode;
    private final PaymentMethod paymentMethod;

    public User(@JsonProperty("id") int id,
                @JsonProperty("name") String name,
                @JsonProperty("email") String email,
                @JsonProperty("phone") String phone,
                @JsonProperty("deliveryAddress") String deliveryAddress,
                @JsonProperty("postalCode") String postalCode,
                @JsonProperty("paymentMethod") PaymentMethod paymentMethod) /*throws Exception*/ {
        this.name = name;
       // if (id < 0) {
       //     throw new Exception("id should be greater than zero");
        //}
        this.id = id;

        this.email = email;
        this.phone = phone;
        this.deliveryAddress = deliveryAddress;
        this.postalCode = postalCode;
        this.paymentMethod = paymentMethod;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("deliveryAddress")
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("paymentMethod")
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @JsonIgnore
    public User merge(final User userUpdate) {

        final String updatedName = userUpdate.getName() == null ? this.name : userUpdate.name;
        final String updatedEmail = userUpdate.getEmail() == null ? this.email : userUpdate.email;
        final String updatedPhone = userUpdate.getPhone() == null ? this.phone : userUpdate.phone;
        final String updatedDeliveryAddress = userUpdate.getDeliveryAddress() == null ? this.deliveryAddress : userUpdate.deliveryAddress;
        final String updatedPostalCode = userUpdate.getPostalCode() == null ? this.postalCode : userUpdate.postalCode;
        final PaymentMethod updatedPaymentMethod = userUpdate.getPaymentMethod() == null ? this.paymentMethod : userUpdate.paymentMethod;

        return new User(this.id, updatedName, updatedEmail, updatedPhone, updatedDeliveryAddress, updatedPostalCode, updatedPaymentMethod);
    }

}
