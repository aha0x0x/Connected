package code.aha.connected;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aha
 */
public class ConnectionsInputCollectorTest {
    
    public ConnectionsInputCollectorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test a correctly formed input file without any illegal characters
     */
    @Test
    public void testValidInput() {
        
        List<String> input = new ArrayList<>();
        input.add( "Philadelphia, Pittsburgh" ); 
        input.add( "Boston, New York" );
        input.add( "Hartford, New York");
        
        Connections.ConnectionsBuilder builder = new Connections.ConnectionsBuilder();
        builder.add( "Philadelphia", "Pittsburgh");
        builder.add( "Boston","New York" );
        builder.add( "Hartford", "New York" );
     
        Connections expected = builder.build();
        Connections actual = input.stream().collect(new ConnectionsInputCollector<String>());
        assertEquals(expected, actual);
    }
    
    @Test
    public void testInputWithInvalidLines() {
        
        List<String> input = new ArrayList<>();
        input.add( "Philadelphia, Pittsburgh" ); 
        input.add( "Boston, New York,Montreal" );
        input.add( "Hartford,");
        input.add( "djskjsfdsfdefd");
        
        Connections.ConnectionsBuilder builder = new Connections.ConnectionsBuilder();
        builder.add( "Philadelphia", "Pittsburgh");
        
        Connections expected = builder.build();
        Connections actual = input.stream().collect(new ConnectionsInputCollector<String>());
        assertEquals(expected, actual);
    }
    
    @Test
    public void testInputWithEmptyCities() {
        
        List<String> input = new ArrayList<>();
        input.add( "," ); 
        
        Connections.ConnectionsBuilder builder = new Connections.ConnectionsBuilder();
        Connections expected = builder.build();
        Connections actual = input.stream().collect(new ConnectionsInputCollector<String>());
        assertEquals(expected, actual);
    }

    @Test
    public void testEmptyFile() {
        
        List<String> input = new ArrayList<>();
        Connections.ConnectionsBuilder builder = new Connections.ConnectionsBuilder();
        Connections expected = builder.build();
        
        Connections actual = input.stream().collect(new ConnectionsInputCollector<String>());
        assertEquals(expected, actual);
    }
}
