package uk.co.dharc.dsb;



public class NID {
	
	private static final int SPECIAL_NULL = 0;
	private static final int SPECIAL_TRUE = 1;
	private static final int SPECIAL_FALSE = 2;
	private static final int SPECIAL_SIZE = 3;
	
	public NID()
	{
		m_hasmac = false;
		m_persistent = false;
		m_serial = new char[6];
		m_n = 0;
		m_t = NIDType.SPECIAL;
		m_ll = 0;
	}
	
	public NID(NIDType type, int value)
	{
		m_t = type;
		m_hasmac = false;
		m_persistent = false;
		m_n = 0;
		m_ll = value;
	}
	
	public boolean isInteger()
	{
		return m_t == NIDType.INTEGER;
	}
	
	public int toInteger()
	{
		if (isInteger())
		{
			return m_ll;
		}
		else
		{
			return 0;
		}
	}
	
	public static NID fromInteger(int v)
	{
		return new NID(NIDType.INTEGER,v);
	}
	
	public String toString()
	{
		if (m_hasmac)
		{
			return String.format("[%02x:%02x:%02x:%02x:%02x:%02x:%02x:%010x]", 3, m_serial[0], m_serial[1], m_serial[2], m_serial[3], m_serial[4], m_serial[5], m_n);
		}
		else
		{
			switch(m_t)
			{
			case INTEGER:		return String.valueOf(m_ll);
			case CHARACTER:		return "'" + String.valueOf(m_ll) + "'";
			case SPECIAL:
				switch(m_ll)
				{
				case SPECIAL_NULL:		return "null";
				case SPECIAL_TRUE:		return "true";
				case SPECIAL_FALSE:		return "false";
				case SPECIAL_SIZE:		return "size";
				default:				break;
				}
			default: break;
			}
			
			return String.format("[%02x:%04x:%016x]",0,m_t,m_ll);
		}
	}
	
	public static NID fromString(String s)
	{
		int first = s.charAt(0);
		
		if (first >= '0' && first <= '9' || first == '-')
		{
			return new NID(NIDType.INTEGER,Integer.parseInt(s));
		}
		else if (first == '[')
		{
			
		}
		else
		{
			
		}
		
		return new NID();
	}
	
	public int pack(byte buf[])
	{
		int count = 0;
		
		if (m_hasmac && m_persistent)
		{
			if (m_persistent)
			{
				buf[0] = 0x03;
			}
			else
			{
				buf[0] = 0x01;
			}
			count++;
			for (int i=0; i<6; i++)
			{
				buf[i+1] = (byte)m_serial[i];
			}
			count += 6;
			buf[count++] = (byte)(m_n >>> 24);
			buf[count++] = (byte)(m_n >>> 16);
			buf[count++] = (byte)(m_n >>> 8);
			buf[count++] = (byte)(m_n);
			return count;
		}
		else
		{
			buf[0] = 0x00;
			switch(m_t)
			{
			case INTEGER: buf[1] = 1; break;
			default: buf[1] = 0; break;
			}
			buf[2] = 0;
			
			//Now pack the value part
			for (int i=0; i<8; i++)
			{
				buf[3+i] = (byte)(m_ll >>> ((7-i)*8));
			}
			
			return 11;
		}
	}
	
	public int unpack(byte buf[])
	{
		return 0;
	}
	
	public boolean isLocal()
	{	
		for (int i=0; i<m_serial.length; i++)
		{
			if (m_serial[i] != 0)
			{
				return false;
			}
		}
		
		return true;
	}
	
	private boolean m_hasmac;
	private boolean m_persistent;
	private char m_serial[];
	private int m_n;
	private NIDType m_t;
	private int m_ll;
}
