package com.dsi.checkauthorization.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sabbir on 6/24/16.
 */

@Entity
@Table(name = "dsi_menu_api")
@Data
public class MenuApi {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "menu_api_id", length = 40)
    private String menuApiId;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "api_id", nullable = false)
    private Api api;

    @ManyToOne
    @JoinColumn(name = "system_id", nullable = false)
    private System system;

    @Column(name = "is_active")
    private boolean isActive;

    private int version;
}
