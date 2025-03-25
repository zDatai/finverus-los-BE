package com.zdatai.finverus.model.master;

import com.zdatai.finverus.model.AuditModifyUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "asset_category")
public class AssetCategory extends AuditModifyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_category_Id")
    private Long assetCategoryId;

    @Column(name = "asset_category")
    private String assetCategory;

    @JoinColumn(name = "vehicle_class_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleClass vehicleClass;

}