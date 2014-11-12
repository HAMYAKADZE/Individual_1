
public class Application implements Runnable {
	private Thread t;
	private long time;
	private long preStart;
	private byte Number;
	private String RoomType;
	private String name;
	private boolean In = false;
	private boolean SuspendFlag;
	private GuestHouse G;
	public Application(GuestHouse G,String RoomType,String name,byte Number,int time,int preStart){
		this.name = name;
		this.Number = Number;
		this.time = time;
		this.RoomType = RoomType;
		this.G = G;
		this.preStart = preStart;
		SuspendFlag = false;
		t = new Thread(this);
		t.start();
	}
	public synchronized void mysuspend(){
		SuspendFlag = true;
	}
	public synchronized void myresume(){
		SuspendFlag = false;
		notify();
	}
	public void Checked(){
		In = true;
	}
	public String getRoomType(){
		return RoomType;
	}
	public byte getNumber(){
		return Number;
	}
	public Thread getThread(){
		return t;
	}
	public String getName(){
		return name;
	}
	public void run(){
		try {
			t.sleep(preStart);
			while(!In){
				G.CheckIn(this);
				synchronized(this){
					while(SuspendFlag){
						wait();
					}
				}
			}
			t.sleep(time);
			G.CheckOut(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}