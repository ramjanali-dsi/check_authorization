package com.dsi.checkauthorization.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sabbir on 6/24/16.
 */

@Entity
@Table(name = "dsi_role_menu")
@Data
public class RoleMenu {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "role_menu_id", length = 40)
    private String roleMenuId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "system_id", nullable = false)
    private System system;

    @Column(name = "is_active")
    private boolean isActive;

    private int version;
}
