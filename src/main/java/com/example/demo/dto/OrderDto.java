package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDto implements Serializable {

    @NotEmpty(message = "Name cant be null")
    private  String customerName;
    @Min(value = 1,message = "minimum milk Quantity in liter")
    private  int milkQuantityInLitres;
    @NotEmpty(message = "Shipping address cant be null")
    private  String shippingAddress;
    @Min(value = 10,message = "minimum value must be 10")
    private  int pricePerLitre;
    @NotEmpty(message = "Payment method  cant be null")
    private  String paymentMethod;

    private LocalDate deliveryDate;
    @NotNull(message = "orderStatus cant be null")
    private  String orderStatus;
}
