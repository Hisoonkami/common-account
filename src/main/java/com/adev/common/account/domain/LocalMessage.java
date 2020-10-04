package com.adev.common.account.domain;

import com.adev.common.base.domian.EntityBase;
import com.adev.common.base.enums.Enums;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="local_message")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class LocalMessage extends EntityBase {
    @Id
    @Column(length = 128)
    private String id;

    /**
     * 消息体
     */
    @Column(length = 256)
    private String context;

    /**
     * 消息状态，SUCCESS：发送成功，FAIL：发送失败
     */
    private Enums.LocalMessageState state;
}
