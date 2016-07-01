package com.dsi.checkauthorization.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sabbir on 6/24/16.
 */

@Entity
@Table(name = "dsi_system")
@Data
public class System {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "system_id", length = 40)
    private String systemId;

    @Column(length = 50)
    private String name;

    @Column(name = "short_name", length = 40)
    private String shortName;

    @Column(name = "tenant_id", nullable = false, length = 40)
    private String tenantId;

    @Column(name = "is_active")
    private boolean isActive;

    private int version;
}
