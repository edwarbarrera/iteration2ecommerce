package test;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.ConnectBd;
import junit.framework.TestCase;

class ConnectBdTest extends TestCase{

	@Test
    void testConnecte() throws SQLException {
        ConnectBd.connecte();
        assertNotNull(ConnectBd.con);
    }

}
