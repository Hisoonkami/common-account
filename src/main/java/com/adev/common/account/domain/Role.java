package com.adev.common.account.domain;

import com.adev.common.base.domian.EntityBase;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="role")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Role extends EntityBase {
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 角色名
	 */
	@Column(length = 128)
	private String roleName;
	
	/**
	 * 角色代码
	 */
	@Column(length = 128)
	private String roleCode;
	
	/**
	 * 备注
	 */
	@Column(length = 128)
	private String remark;

	/**
	 * 包含的权限
	 */
	@ManyToMany
	@JoinTable(name = "role_permission",joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private List<Permission> permissionList;
}
