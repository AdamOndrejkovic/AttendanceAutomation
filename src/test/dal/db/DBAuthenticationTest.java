package dal.db;

import org.junit.jupiter.api.*;

class DBAuthenticationTest {

    @BeforeAll
    public static void setUp(){
        DBTeacherRepository dbTeacherRepository = new DBTeacherRepository();
        dbTeacherRepository.registerTeacher("teacher", "test", "teacher@gmail.com", "test123");

        DBStudentRepository dbStudentRepository = new DBStudentRepository();
        dbStudentRepository.registerStudent("student", "test", "student@gmail.com", "test456");

    }

    @AfterAll
    public static void tearDown(){
        DBTeacherRepository dbTeacherRepository = new DBTeacherRepository();
        dbTeacherRepository.deleteTeacherTest("teacher@gmail.com", "test123");

        DBStudentRepository dbStudentRepository = new DBStudentRepository();
        dbStudentRepository.deleteStudentTest("student@gmail.com", "test456");

    }

    @DisplayName("Receiving teacher credintials after authenticating")
    @Test
    void getAuthenticationTeacher() {
        DBAuthentication dbAuthentication = new DBAuthentication();

        String actualValue = String.valueOf(dbAuthentication.getAuthentication("teacher@gmail.com", "test123"));
        String expectedValue = "teacher test";

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @DisplayName("Receiving student credintials after authenticating")
    @Test
    void getAuthenticationStudent() {
        DBAuthentication dbAuthentication = new DBAuthentication();

        String actualValue = String.valueOf(dbAuthentication.getAuthentication("student@gmail.com", "test456"));
        String expectedValue = "student test";

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @DisplayName("Authentication returns null when no user found")
    @Test
    void getAuthenticationWrong() {
        DBAuthentication dbAuthentication = new DBAuthentication();

        Object actualValue = dbAuthentication.getAuthentication("wrong@gmail.com", "wrong123");
        Object expectedValue = null;

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @DisplayName("Valid authentication of teacher")
    @Test
    void validAuthenticateTeacher() {
        DBAuthentication dbAuthentication = new DBAuthentication();

        Object actualValue = dbAuthentication.authenticateTeacher("teacher@gmail.com", "test123");
        Boolean expectedValue = true;

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @DisplayName("Invalid authentication of teacher")
    @Test
    void invalidAuthenticateTeacher() {
        DBAuthentication dbAuthentication = new DBAuthentication();

        Object actualValue = dbAuthentication.authenticateTeacher("invalid@gmail.com", "invalid123");
        Boolean expectedValue = false;

        Assertions.assertEquals(expectedValue, actualValue);

    }

    @DisplayName("Valid authentication of student")
    @Test
    void validAuthenticateStudent() {
        DBAuthentication dbAuthentication = new DBAuthentication();

        Object actualValue = dbAuthentication.authenticateTeacher("student@gmail.com", "test456");
        Boolean expectedValue = false;

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @DisplayName("Invalid authentication of student")
    @Test
    void invalidAuthenticateStudent() {
        DBAuthentication dbAuthentication = new DBAuthentication();

        Object actualValue = dbAuthentication.authenticateTeacher("invalid@gmail.com", "invalid456");
        Boolean expectedValue = false;

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @DisplayName("Receiving teachers credintials")
    @Test
    void getTeacherWithCredintials() {
        DBAuthentication dbAuthentication = new DBAuthentication();

        String actualValue = String.valueOf(dbAuthentication.getTeacherWithCredintials("teacher@gmail.com", "test123"));
        String expectedValue = "teacher test";

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @DisplayName("Invalid credintials for teacher")
    @Test
    void getTeacherWithCredintialsInvalid() {
        DBAuthentication dbAuthentication = new DBAuthentication();

        Object actualValue = dbAuthentication.getTeacherWithCredintials("invalid@gmail.com", "invalid123");
        Object expectedValue = null;

        Assertions.assertEquals(expectedValue, actualValue);

    }

    @DisplayName("Receiving students credintials")
    @Test
    void getStudentWithCredintials() {
        DBAuthentication dbAuthentication = new DBAuthentication();

        String  actualValue = String.valueOf(dbAuthentication.getStudentWithCredintials("student@gmail.com", "test456"));
        String expectedValue = "student test";

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @DisplayName("Invalid credintials student")
    @Test
    void getStudentWithCredintialsInvalid() {
        DBAuthentication dbAuthentication = new DBAuthentication();

        Object actualValue = dbAuthentication.getStudentWithCredintials("invalid@gmail.com", "invalid456");
        Object expectedValue = null;

        Assertions.assertEquals(expectedValue, actualValue);
    }
}