/*
 * Author : Ketki Keni
 * Date : 22-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.MenuService.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Pizza {
    @Id
    private String id;
    private String itemName;
    private String category;
    private int price;
    private String ImageUrl;
}
