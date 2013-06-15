import uk.co.dharc.dsb.NID;
import uk.co.dharc.dsb.NIDType;

public class NIDTest
{
	public static void main(String[] args)
	{
		NID tnid = new NID(NIDType.INTEGER,50);
		
		System.out.println(tnid.toString());
	}
}