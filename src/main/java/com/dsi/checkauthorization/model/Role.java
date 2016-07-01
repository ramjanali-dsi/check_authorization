package com.dsi.checkauthorization.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sabbir on 6/24/16.
 */

@Entity
@Table(name = "dsi_role")
@Data
public class Role {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "role_id", length = 40)
    private String roleId;

    @Column(length = 50)
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

    private int version;
}
