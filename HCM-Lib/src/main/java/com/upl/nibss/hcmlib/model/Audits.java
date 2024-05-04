
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "audits")
public class Audits extends SuperModel implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	@JsonProperty("employee")
	@JsonIgnore
	private Employee employee;

	@Column(name = "user_name")
	@JsonProperty("user_name")
	private String username;

	@Column(name = "ip_address")
	@JsonProperty("ip_address")
	private String ipAddress;

	@Column(name = "user_agent")
	@JsonProperty("user_agent")
    private String userAgent;

	@Column(name = "crud_action")
	@JsonProperty("crud_action")
	private String crudAction;

	@Column(name = "crud_action_description")
	@JsonProperty("crud_action_description")
	private String crudActionDescription;

	@Column(name = "entity")
	@JsonProperty("entity")
	private String entity;

	@Lob
	@Column(name = "value_before")
	@JsonProperty("value_before")
	private String valueBefore;

	@Lob
	@Column(name = "value_after")
	@JsonProperty("value_after")
	private String valueAfter;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Audits audits = (Audits) o;
		return Objects.equals(id, audits.id) &&
				Objects.equals(username, audits.username) &&
				Objects.equals(ipAddress, audits.ipAddress) &&
				Objects.equals(userAgent, audits.userAgent) &&
				Objects.equals(crudAction, audits.crudAction) &&
				Objects.equals(crudActionDescription, audits.crudActionDescription) &&
				Objects.equals(entity, audits.entity) &&
				Objects.equals(valueBefore, audits.valueBefore) &&
				Objects.equals(valueAfter, audits.valueAfter);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, username, ipAddress, userAgent, crudAction, crudActionDescription, entity, valueBefore, valueAfter);
	}
}
