package uk.co.dharc.dsb;

public class Event {
	
	private static final int EVTFLAG_SENT = 1;
	private static final int EVTFLAG_DONE = 2;

	public Event()
	{
		m_type = EventType.GET;
		m_d1 = new NID();
		m_d2 = new NID();
		m_flags = 0;
	}
	
	public Event(EventType type)
	{
		m_d1 = new NID();
		m_d2 = new NID();
		m_type = type;
		m_flags = 0;
	}
	
	public Event(EventType type, NID d1, NID d2)
	{
		m_d1 = d1;
		m_d2 = d2;
		m_type = type;
		m_flags = 0;
	}
	
	public NID getDestination1()
	{
		return m_d1;
	}
	
	public NID getDestination2()
	{
		return m_d2;
	}
	
	public EventType getType()
	{
		return m_type;
	}
	
	public NID getResult()
	{
		return m_param1;
	}
	
	public void setResult(NID r)
	{
		m_param1 = r;
	}
	
	public void setParameter(NID p)
	{
		m_param1 = p;
	}
	
	public void completed()
	{
		m_flags |= EVTFLAG_DONE;
	}
	
	public void sent()
	{
		m_flags |= EVTFLAG_SENT;
	}
	
	public boolean hasSent()
	{
		return (m_flags & EVTFLAG_SENT) != 0;
	}
	
	public boolean hasCompleted()
	{
		return (m_flags & EVTFLAG_DONE) != 0;
	}
	
	public int pack(byte[] buf)
	{
		return 0;
	}
	
	public int unpack(byte[] buf)
	{
		return 0;
	}
	
	public String pretty()
	{
		return "";
	}
	
	public static Event makeDefine(NID d1, NID d2, NID def, int eval)
	{
		Event res = new Event(EventType.DEFINE,d1,d2);
		res.m_param1 = def;
		res.m_eval = eval;
		return res;
	}
	
	public static Event makeGet(NID d1, NID d2)
	{
		Event res = new Event(EventType.GET,d1,d2);
		return res;
	}
	
	private EventType m_type;
	private NID m_d1;
	private NID m_d2;
	private NID m_param1;
	private NID m_param2;

	private int m_eval;
	private int m_flags;
}
