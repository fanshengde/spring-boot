package com.fsd.entity.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sid;

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (this.sid == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
			return false;
		}

		AbstractEntity that = (AbstractEntity) obj;

		return this.sid.equals(that.getSid());
	}

	@Override
	public int hashCode() {
		return sid == null ? 0 : sid.hashCode();
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

}
