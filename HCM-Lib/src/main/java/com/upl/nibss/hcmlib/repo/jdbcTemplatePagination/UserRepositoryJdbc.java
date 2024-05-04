package com.upl.nibss.hcmlib.repo.jdbcTemplatePagination;

import com.upl.nibss.hcmlib.model.auditModel.LeaveBookingAud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userRepository")
public class UserRepositoryJdbc extends AbstractJdbcRepository<LeaveBookingAud, String>{
	
	@Autowired
	public UserRepositoryJdbc(JdbcTemplate template) {
		super(
			new BeanPropertyRowMapper<>(LeaveBookingAud.class),
			userUpdaterMapper,
			"leave_bookings_aud",
			"id",
			template);
	}

	//	@Override
//	public User findByUsername(String username) {
//		return this.findOneByColumn("username",username);
//	}


//	static RowMapper<LeaveBookingAud> userRowMapper = new RowMapper<LeaveBookingAud>() {
//		@Override
//		public LeaveBookingAud mapRow(ResultSet rs, int rowNum) throws SQLException {
//			return new LeaveBookingAud(
//				rs.getString("id"),
//				rs.getString("revision_id"),
//				rs.getString("revision_type"),
//				rs.getString("created_at"),
//				rs.getString("deleted"),
//				rs.getString("updated_at"),
//				rs.getString("color"),
//				rs.getString("day"),
//				rs.getString("icon"),
//				rs.getString("status"),
//				rs.getString("title"),
//				rs.getString("status"),
//				rs.getString("icon"),
//				rs.getString("role"));
//		}
//	};
//
	static Updater<LeaveBookingAud> userUpdaterMapper = new Updater<LeaveBookingAud>() {
		@Override
		public void mapColumns(LeaveBookingAud t, Map<String, Object> mapping) {
			mapping.put("id",t.getId());
			mapping.put("revision_id",t.getRevision_id());
			mapping.put("revision_type",t.getRevision_id());
			mapping.put("created_at",t.getCreatedAt());
			mapping.put("deleted",t.isDeleted());
			mapping.put("updated_at",t.getUpdatedAt());
			mapping.put("color",t.getColor());
			mapping.put("day",t.getDay());
			mapping.put("icon",t.getIcon());
			mapping.put("status",t.getStatus());
			mapping.put("title",t.getTitle());
			mapping.put("created_by",t.getCreated_by());
			mapping.put("employee_id",t.getEmployee());
			mapping.put("modified_by",t.getCreated_by());
		}
	};

}
