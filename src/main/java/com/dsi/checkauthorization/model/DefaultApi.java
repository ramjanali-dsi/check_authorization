package com.dsi.checkauthorization.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sabbir on 6/24/16.
 */

@Entity
@Table(name = "dsi_default_api")
@Data
public class DefaultApi {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "default_api_id", length = 40)
    private String defaultApiId;

    @ManyToOne
    @JoinColumn(name = "api_id", nullable = false)
    private Api api;

    @Column(name = "allow_type", length = 20)
    private String allowType;

    private int version;
}
