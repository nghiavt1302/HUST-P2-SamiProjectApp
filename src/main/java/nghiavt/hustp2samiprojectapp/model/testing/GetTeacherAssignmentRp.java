//package nghiavt.hustp2samiprojectapp.model.testing;
//
//
//import jakarta.persistence.ColumnResult;
//import jakarta.persistence.ConstructorResult;
//import jakarta.persistence.NamedNativeQuery;
//import jakarta.persistence.SqlResultSetMapping;
//
//@SqlResultSetMapping(
//        name = "GetTeacherAsgMapping",
//        classes = @ConstructorResult(
//                targetClass = GetTeacherAssignmentRp.class,
//                columns = {
//                        @ColumnResult(name ="assign_id"),
//                        @ColumnResult(name ="app_id"),
//                        @ColumnResult(name ="comm_id"),
//                        @ColumnResult(name ="instructor_id"),
//                        @ColumnResult(name ="project_type"),
//                        @ColumnResult(name ="project_id"),
//                        @ColumnResult(name ="reviewer_id"),
//                        @ColumnResult(name ="status"),
//                        @ColumnResult(name ="student_id"),
//                        @ColumnResult(name ="term"),
//                }
//        )
//)
//
//@NamedNativeQuery(name = "getTeacherAsgm1",
//        query = "SELECT a.* FROM assignment a " +
//                "INNER JOIN teacher t " +
//                "ON a.instructor_id = t.teacher_id " +
//                "WHERE t.email = :teacherEmail",
//        resultSetMapping = "GetTeacherAsgMapping",
//        resultClass = GetTeacherAssignmentRp.class)
//public class GetTeacherAssignmentRp {
//    private String assignId;
//    private String appId;
//    private String commId;
//    private String instructorId;
//    private String projectType;
//    private String projectId;
//    private String reviewerId;
//    private String status;
//    private Integer studentId;
//    private String term;
//}
