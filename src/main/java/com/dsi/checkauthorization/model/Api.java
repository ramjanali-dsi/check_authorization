package com.dsi.checkauthorization.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sabbir on 6/24/16.
 */

@Entity
@Table(name = "dsi_api")
@Data
public class Api {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "api_id", length = 40)
    private String apiId;

    private String url;

    @Column(length = 20)
    private String method;

    @ManyToOne
    @JoinColumn(name = "system_id", nullable = false)
    private System system;

    @Column(name = "is_active")
    private boolean isActive;

    private int version;
}
