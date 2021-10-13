package parking;

import java.util.Scanner;

public class ParkingApp {
	Scanner scan = new Scanner(System.in);
	
	final static int MAX = 8;	
	static Parking[] car = new Parking[MAX];
	int cnt = 0;
	int sum = 0;
	
	public void start() {
		System.out.println("===============================");
		System.out.println("\t << 주차장 현황 >>");
		System.out.println("===============================");
		
		for(int i=0; i<MAX/2; i++){
			if(car[i].getCarNum() > 0) {
				System.out.printf("[■]\t%04d\t", car[i].getCarNum());
	    	}
	    	else {
	    		System.out.print("["+i+"]\t\t");
	    	}
	    	if(car[i+4].getCarNum() > 0) {
	    		System.out.printf("[■]\t%04d\t", car[i+4].getCarNum());
	    	}
	    	else {
	    		System.out.print("["+(i+4)+"]");
	    	}
	        System.out.println();
	    }
		System.out.println("===============================");
		System.out.printf("\t전체 : %d, 잔여 : %d\n",MAX,MAX-cnt);
		System.out.println("===============================");
		System.out.println("[i] : 주차, [o] : 출차, [x] : 종료");
		System.out.print("선택 : ");
		String sel = scan.next();
		System.out.println("## "+sel+" ##");
		
		switch(sel) {
		case "i":
			if (cnt==8) {
				System.out.println("만차입니다.");
			}
			else {
				newCar(); 
			}
			break;
		case "o":
			if (cnt==0) {
				System.out.println("주차되어있는 차량이 없습니다.");
			}
			else {
				outCar();
			}
			break;
		case "admin":
			admin();
			break;
		case "x":
			System.out.println("프로그램이 종료되었습니다.");
			System.exit(0);
			break;
		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력하시오.");
		}
	}
	
	public void newCar() {
		System.out.println("===============================\n 주차 할 곳의 번호를 고르시오.(0~7)");
	    int num = scan.nextInt();
	    if(num>=0 && num<=7) {
	    	if(car[num].getCarNum() != 0) {
		    	System.out.println("다른 차가 주차되어 있습니다.");
		    }
		    else {
		    	System.out.print("차량번호(0000~9999) : ");
				car[num].setCarNum(scan.nextInt());
				System.out.print("입차 시간(0~23) : ");
				car[num].setIn_Hour(scan.nextInt());
				System.out.print("입차 분(0~59) : ");
				car[num].setIn_Min(scan.nextInt());
				System.out.println(" ("+num+")번에 "+car[num].getCarNum()+" 차량이 주차되었습니다.");
				car[num].setType("IN");	
				cnt++;
		    }
	    }
	    else {
	    	System.out.println("0~7사이의 숫자를 입력하시오.");
	    }
	}
	
	public void outCar() {
		System.out.println("===============================\n 출차할 차량의 번호를 입력하시오.");
		boolean check = false;      
		int num = scan.nextInt();
		for(int i=0;i<MAX;i++) {
			if(car[i].getCarNum()==num) {
				num = i;
				check = true;
				break;
		    }
		}
		if(check==false) {
			System.out.println("없는 차량이거나 잘못 입력하셨습니다. 다시 입력하시오.");
		}
		else {
			System.out.print("출차 시간(0~23) : ");
			car[num].setOut_Hour(scan.nextInt());
			System.out.print("출차 분(0~59) : ");
			car[num].setOut_Min(scan.nextInt());
			System.out.println("===============================");
			System.out.println("차량번호 : "+car[num].getCarNum());
			System.out.printf("입차시간 : %d 시 %d 분\n",car[num].getIn_Hour(), car[num].getIn_Min());
			System.out.printf("출차시간 : %d 시 %d 분\n",car[num].getOut_Hour(), car[num].getOut_Min());
			System.out.printf("주차시간 : %d 시간 %d 분\n",car[num].getOut_Hour()-car[num].getIn_Hour(), car[num].getOut_Min()-car[num].getIn_Min());
			System.out.println("주차요금 : "+car[num].getFee()+"원");
			car[num].setType("OUT");
			cnt--;
			sum += car[num].getFee();
			car[num] = new Parking();
		}
		System.out.println("===============================");
		System.out.println("종료하시려면 x를, 되돌아가려면 아무키를 입력하시오.");
		String user = scan.next();
		if(user.equals("x")) {
			System.exit(0);
		}
	}
	
	public void admin() {
		while(true) {
			System.out.println("===============================");
			System.out.println("\t << 관리자모드 >>");
			System.out.println("===============================");
			System.out.println("비밀번호를 입력하세요(x:메인화면으로) : ");
			String pass = scan.next();
			if (pass.equals("1234")) {
				System.out.println("관리자님 환영합니다.");
				System.out.println("===============================");
				System.out.println("[1] : 주차현황, [2] : 누적 주차비,[x] : 이전으로");
				System.out.print("선택 : ");
				String sel = scan.next();
				System.out.println("## "+sel+" ##");
				switch(sel) {
				case "1":
					System.out.println("|  차고번호    |  차량번호  |  입차시간    |  ");
					System.out.println("===============================");
					for(int i=0;i<MAX;i++) {
						if (car[i].getCarNum()>0) {
							System.out.printf("  %d번 차고지 \t%04d\t%d시 %d분\n",i, car[i].getCarNum(),car[i].getIn_Hour(),car[i].getIn_Min());
						}
						else {
							System.out.printf("  %d번 차고지 \t\t%d시 %d분\n",i,car[i].getIn_Hour(),car[i].getIn_Min());
						}
					}
					break;
				case "2":
					System.out.printf("누적 주차비는 총 %d 원 입니다.\n",sum);
					break;
				case "x":
					break;
				default:
					System.out.printf("잘못 입력하셨습니다.\n",sum);
					break;
				}
			}
			else if(pass.equals("x")) {
				break;
			}
			else {
				System.out.println("비밀번호가 옳지 않습니다. 다시 입력해주세요");
			}
		}
		
	}
	
	public static void main(String[] args) {
		ParkingApp app = new ParkingApp();
		for(int i=0; i<MAX; i++){
			car[i] = new Parking();
		}
				
		while(true) {
			app.start();
		}
	}
}
