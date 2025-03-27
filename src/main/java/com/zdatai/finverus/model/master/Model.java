package com.zdatai.finverus.model.master;

import com.zdatai.finverus.model.AuditModifyUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "model")
public class Model extends AuditModifyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Long modelId;

    @Column(name = "model")
    private String model;

    @JoinColumn(name = "make_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Make make;

    @JoinColumn(name = "vehical_class_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleClass vehicleClass;

    @JoinColumn(name = "asset_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AssetCategory assetCategory;

}
