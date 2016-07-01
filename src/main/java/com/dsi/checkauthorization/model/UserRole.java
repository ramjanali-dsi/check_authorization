package com.dsi.checkauthorization.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sabbir on 6/24/16.
 */

@Entity
@Table(name = "dsi_user_role")
@Data
public class UserRole {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "user_role_id", length = 40)
    private String userRoleId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "system_id", nullable = false)
    private System system;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_by", nullable = false, length = 40)
    private String createBy;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "modified_by", nullable = false, length = 40)
    private String modifiedBy;

    private int version;
}
