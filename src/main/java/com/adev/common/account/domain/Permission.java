package com.adev.common.account.domain;

import com.adev.common.base.domian.EntityBase;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="permission")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Permission extends EntityBase {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 权限名称
     */
    @Column(length = 128)
    private String permissionName;

    /**
     * 权限代码
     */
    @Column(length = 128)
    private String permissionCode;

    /**
     * 上级权限id
     */
    @Column(length = 20)
    private Long pid;

    /**
     * 备注
     */
    @Column(length = 128)
    private String remark;
}
