/*
 * Author : Ketki Keni
 * Date : 20-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Pizza {
    @Id
    private String id;
    private String itemName;
    private String category;
    private int price;

}
