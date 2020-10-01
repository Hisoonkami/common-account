package com.adev.common.account.domain;

import javax.persistence.*;

import com.adev.common.base.domian.EntityBase;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name="user_info")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class User extends EntityBase {
	@Id
	@GeneratedValue
	private Long userId;
	/**
	 * 登录名
	 */
	@Column(length = 128)
	private String loginName;
	
	/**
	 * 登录密码
	 */
	@Column(length = 128)
	@JsonIgnore
	private String password;
	
	/**
	 * 用户名
	 */
	@Column(length = 128)
	private String username;
}
