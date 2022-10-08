import database.QueryWrapper;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class QueryWrapperTest {

    @Test
    public void guavaHashTest(){
        String testPassword = "ZDliNWY1OGYwYjM4MTk4MjkzOTcxODY1YTE0MDc0ZjU5ZWJhM2U4MjU5NWJlY2JlODZhZTUxZjFkOWYxZjY1ZQ==";
        QueryWrapper qw = new QueryWrapper();
        assertEquals(testPassword, qw.guavaHash("Test123"));

    }
}
