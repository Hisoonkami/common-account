package com.adev.common.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name="user_info")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class User {
	@Id
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
	
	/**
	 * 身份
	 */
	@Column(length = 20)
	private String identity;
	
	/**
	 * 创建时间
	 */
	private Long timestamp;
}
