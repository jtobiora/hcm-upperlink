//package com.upl.nibss.hcmlib.repo.genericRepo;
//
//import com.upl.nibss.hcmlib.model.auditModel.LeaveBookingAud;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// * Created by stanlee on 12/03/2018.
// */
//public interface CustomLeaveBookingAudRepo extends ExtendedRepository<LeaveBookingAud, Long> {
//
//    //Auditing Query
//    @Query(value = " SELECT * from leave_bookings_aud \n#pageable\n ", countQuery = " SELECT count(id) from leave_bookings_aud", nativeQuery = true)
//    Page<LeaveBookingAud> getAllAudit(Pageable pageable);
//
//    @Query(value = " SELECT * from leave_bookings_aud WHERE modified_by = :employeeId or created_by = :employeeId \n#pageable\n ", countQuery = " SELECT count(id) from leave_bookings_aud WHERE modified_by = :employeeId or created_by = :employeeId", nativeQuery = true)
//    Page<LeaveBookingAud> getAllAuditByEmployeeId(@Param("employeeId") Long employeeId , Pageable pageable);
//
//    @Query(value = " SELECT * from leave_bookings_aud WHERE id = :objectId \n#pageable\n ", countQuery = " SELECT count(id) from leave_bookings_aud WHERE id = :objectId", nativeQuery = true)
//    Page<LeaveBookingAud> getAllAuditByObjectId(@Param("objectId") Long objectId ,Pageable pageable);
//}
