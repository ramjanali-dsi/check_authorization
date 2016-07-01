package com.dsi.checkauthorization.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sabbir on 6/24/16.
 */

@Entity
@Table(name = "dsi_user")
@Data
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "user_id", length = 40)
    private String userId;

    @Column(name = "first_name", length = 40)
    private String firstName;

    @Column(name = "last_name", length = 40)
    private String lastName;

    @Column(length = 10)
    private String gender;

    @Column(unique = true, nullable = false, length = 40)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(name = "tenant_id", nullable = false, length = 40)
    private String tenantId;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_by", nullable = false, length = 40)
    private String createBy;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "modified_by", nullable = false, length = 40)
    private String modifiedBy;

    private int version;

    @Transient
    private UserRole userRole;
}
