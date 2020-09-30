package com.adev.common.account.domain;

import com.adev.common.base.domian.EntityBase;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="user_role")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@IdClass(UserRoleKey.class)
public class UserRole extends EntityBase {
    @Id
    private Long userId;

    @Id
    private Long roleId;
}
