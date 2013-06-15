import junit.framework.*;
import uk.co.dharc.dsb.NID;
import uk.co.dharc.dsb.NIDType;

public class NIDTest extends TestCase
{
	public void testIntegers()
	{
		NID tnid = new NID(NIDType.INTEGER,50);
		
		assertEquals(tnid.toInteger(),50);
		assertEquals(tnid.toString(),"50");
		
		tnid = NID.fromInteger(66);
		assertEquals(tnid.toInteger(),66);
		assertTrue(tnid.isInteger());
	}
}