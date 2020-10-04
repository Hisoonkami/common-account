package com.adev.common.account.domain;

import com.adev.common.base.domian.EntityBase;
import com.adev.common.base.enums.Enums;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="account_book")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class AccountBook {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 交易类型，PAY：支出，INCOME：收入
     */
    private Enums.AccountBookType type;

    /**
     * 对应的订单id
     */
    @Column(length = 128)
    private String orderId;
}
