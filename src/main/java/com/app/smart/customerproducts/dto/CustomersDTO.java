package com.app.smart.customerproducts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomersDTO {

    private UUID id;

    private String title;

    private boolean isDeleted;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss", timezone = "Europe/Moscow")
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss", timezone = "Europe/Moscow")
    private Date modifiedAt;

    private Set<ProductsDTO> products;
}
