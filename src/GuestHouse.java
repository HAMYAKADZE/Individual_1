import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GuestHouse {
	private int M = 2;
	private byte type;	
	private ArrayList<ArrayList<Queue<Application>>> GuestQueue;
	private ArrayList<ArrayList<ArrayList<String>>> GuestList;
	public GuestHouse(){
		GuestList = new ArrayList<ArrayList<ArrayList<String>>>(M);
		for(int i = 0; i<=2; i++){
			GuestList.add(i,new ArrayList<ArrayList<String>>(4));
			for(int j = 0; j<=3; j++){
				GuestList.get(i).add(new ArrayList<String>());
			}
		}
		GuestQueue = new ArrayList<ArrayList<Queue<Application>>>(M);
		for(int i = 0; i<=2; i++){
			GuestQueue.add(i,new ArrayList<Queue<Application>>(M));
			for(int j = 0; j<=3; j++){
				GuestQueue.get(i).add(new LinkedList<Application>());
			}
		}
	}

	public synchronized boolean CheckIn(Application a){
		switch(a.getRoomType()){
			case "Эконом": type = 0;
						   break;
			case "Стандарт": type = 1;
							 break;
			case "Люкс": type = 2;
						 break;
		}
		if(GuestList.get(type).get(a.getNumber()-1).size() < M){
			GuestList.get(type).get(a.getNumber()-1).add(a.getName());
			a.Checked();
			System.out.println(a.getName() + " поселен.");
			return true;
		}else{
			a.mysuspend();
			GuestQueue.get(type).get(a.getNumber()-1).add(a);
			System.out.println(a.getName() + " ожидает.");
			return false;
		}
	}

	public synchronized void CheckOut(Application a){
		switch(a.getRoomType()){
			case "Эконом": type = 0;
					   	   break;
			case "Стандарт": type = 1;
						     break;
			case "Люкс": type = 2;
					     break;
		}
		GuestList.get(type).get(a.getNumber()-1).remove(a.getName());
		if(GuestQueue.get(type).get(a.getNumber()-1).peek() != null){
			GuestQueue.get(type).get(a.getNumber()-1).peek().myresume();
			GuestQueue.get(type).get(a.getNumber()-1).poll();
		}
		System.out.println(a.getName() + " выселен.");
	}
}
