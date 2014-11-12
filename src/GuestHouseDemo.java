
public class GuestHouseDemo {
	public static void main(String[]args){
		GuestHouse G = new GuestHouse();
        Application a1 = new Application(G,"Эконом","Первый",(byte)1,10000,1000);
        Application a2 = new Application(G,"Эконом","Второй",(byte)2,8000,2000);
        Application a3 = new Application(G,"Люкс","Третий",(byte)3,11000,3000);
        Application a4 = new Application(G,"Люкс","Четвертый",(byte)1,10000,4000);
        Application a5 = new Application(G,"Эконом","Пятый",(byte)4,10000,5000);
        Application a6 = new Application(G,"Стандарт","Шестой",(byte)1,10,6000);
        Application a7 = new Application(G,"Эконом","Седьмой",(byte)3,11000,7000);
        Application a8 = new Application(G,"Люкс","Восьмой",(byte)1,10000,8000);
	}
}
