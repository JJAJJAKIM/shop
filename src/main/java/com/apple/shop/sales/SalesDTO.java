package com.apple.shop.sales;

import lombok.Data;

@Data
public class SalesDTO {

    public String itemName;
    public Integer price;
    public Integer count;
    public String userName;
}
