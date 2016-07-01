package com.dsi.checkauthorization.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sabbir on 6/24/16.
 */

@Entity
@Table(name = "dsi_menu")
@Data
public class Menu {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "menu_id", length = 40)
    private String menuId;

    @Column(length = 50)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "parent_menu_id", length = 40)
    private String parentMenuId;

    @Column(name = "is_active")
    private boolean isActive;

    private int version;
}
