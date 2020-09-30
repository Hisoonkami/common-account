package com.adev.common.account.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@NoArgsConstructor
@Embeddable
public class UserRoleKey implements Serializable {
    private Long userId;

    private Long roleId;
}
