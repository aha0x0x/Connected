package code.aha.connected;

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
public class ConnectionsTest {
    
    private static final String CAMBRIDGE = "Cambridge";
    private static final String BOSTON = "Boston";
    private static final String PHILADELPHIA = "Philadelphia";
    private static final String PITTSBURG = "Pittsburgh";
    private static final String NEWYORK = "New York";
    private static final String HARTFORD = "Hartford";
    private static final String LA = "Los Angeles"; 
    private static final String SANDIAGO = "San Diego";
    private static final String CROTONHARMON = "Croton-Harmon";
    private static final String STPETERSBURG = "St. Petersburg";
    private static final String TAMPABAY = "Tampa Bay";
    
    private static final String MONTREAL = "Montreal";
    private static final String TORONTO = "Toronto";
    
    private Connections mConnections;
    
    public ConnectionsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {

        Connections.ConnectionsBuilder builder = new Connections.ConnectionsBuilder();
        builder.add( CAMBRIDGE, BOSTON );
        builder.add( BOSTON, NEWYORK );
        builder.add( PHILADELPHIA, PITTSBURG );
        builder.add( HARTFORD, NEWYORK );
        builder.add( LA, SANDIAGO );
        builder.add( NEWYORK, CROTONHARMON );
        builder.add( STPETERSBURG, TAMPABAY );
        mConnections = builder.build();
    }
    
    @After
    public void tearDown() {
        mConnections = null;
    }

    /**
     * test: cities are directly connected. 
     */
    @Test
    public void testCitiesDirectlyConnected() {
        assertTrue( mConnections.isConnected( NEWYORK, BOSTON ) );
        assertTrue( mConnections.isConnected( PHILADELPHIA, PITTSBURG ) );
        assertTrue( mConnections.isConnected( NEWYORK, HARTFORD ) );
    }
    
    /**
     * test: cities are not provided.
     */
    @Test
    public void testCitiesNotPresent() {
        assertFalse( mConnections.isConnected( NEWYORK, MONTREAL ) );
        assertFalse( mConnections.isConnected( TORONTO, MONTREAL ) );
        assertFalse( mConnections.isConnected( TORONTO, PHILADELPHIA ) );
    }
    
    /**
     * test: cities are directly connected. 
     */
    @Test
    public void testCitiesIndirectlyConnected() {
        
        // BOSTON <-> NEWYORK <-> HARTFORD
        assertTrue( mConnections.isConnected( HARTFORD, BOSTON ) );
        
        //CROTONHARMON <-> NEWYORK <-> BOSTON <-> CAMBRIDGE
        assertTrue( mConnections.isConnected( CROTONHARMON, CAMBRIDGE ) );
    }
}
