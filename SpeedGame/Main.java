package SpeedGame;

import java.util.Scanner;



public class Main {
	static String timerBuffer; // 04:11:15 등의 경과 시간 문자열이 저장될 버퍼 정의
	  static int oldTime;
	
	  public static void stopwatch(int onOff) {
	    if (onOff == 1) // 타이머 켜기
	      oldTime = (int) System.currentTimeMillis() / 1000;

	    if (onOff == 0) // 타이머 끄고, 시분초를 timerBuffer 에 저장
	      secToHHMMSS(  ((int) System.currentTimeMillis() / 1000) - oldTime  );

	  }


	  // 정수로 된 시간을 초단위(sec)로 입력 받아, "04:11:15" 등의 형식의 문자열로 시분초를 저장
	  public static void secToHHMMSS(int secs) {
	    int hour, min, sec;

	    sec  = secs % 60;
	    min  = secs / 60 % 60;
	    hour = secs / 3600;

	    timerBuffer = String.format("%02d:%02d:%02d", hour, min, sec);
	  }
	  
	public static void main(String[] args) {
		
		boolean bk = true;
		String lv = "";
		boolean b = true;
		int n = 0;
		int sum = 0;
		
		Scanner sc = new Scanner(System.in);
		while(b) {
			int ch = 1;
			System.out.println("******************** 가짜 연산 게임  ********************");
			System.out.println("[ 메뉴 ]");
			System.out.print("[ 1. 게임시작 ]");
			System.out.print("\t[ 2. 게임순위 ]");
			System.out.print("\t[ 3. 게임업적 ]");
			System.out.println("\t[ 4. 종료 ]");
			System.out.println();
			System.out.println("원하는 항목을 입력해주세요. (! 여러 숫자를 입력시 맨 앞자리 수로 인식합니다.)");
			System.out.print("입력:");
			String choice = sc.next();
			System.out.println();
			
			if(choice.contains("시작") ||choice.contains("1")) {
				System.out.println("! 게임 스타트 !");
				ch = 0;
			}else if(choice.contains("순위") ||choice.contains("2")) {
				System.out.println("! 게임 순위 !");
				ch = 1;
			}else if(choice.contains("업적") ||choice.contains("3")) {
				System.out.println("! 게임 업적 !");
				ch = 2;
			}else if(choice.contains("종료") ||choice.contains("4")) {
				System.out.println("! 게임 종료 !");
				ch = 3;
			}else {
				System.out.println(" ERROR :정확하게 다시 입력해주십시요. ");
				System.out.println();
				continue;
			}
			
			GameProcessing gp = new GameProcessing(n);
			bk = gp.load();
			if(ch == 0) {
				gp.gameRule();
				System.out.println();
				System.out.println();
				lv = gp.setting();
				gp.setName();
				
				stopwatch(1);
				gp.game(lv);
				System.out.println("게임이 끝났습니다 !");
				System.out.println();
				stopwatch(0);
			    System.out.format("Timer OFF! 경과 시간: [%s]%n", timerBuffer); // 경과 시간 화면에 출력
			    String[] sl = new String[3];
			    sl = timerBuffer.split(":");
			 
			    sum += (Integer.parseInt(sl[0])*3600);
			    sum += (Integer.parseInt(sl[1])*60);
			    sum += Integer.parseInt(sl[2]);
			    gp.add(sum);
			    gp.check();
			    System.out.println("기록 : "+gp.getGrade());
			    System.out.println();System.out.println();System.out.println();
			}
			else if(ch == 1){
				if(bk) {
					System.out.println("난이도마다 순위가 다릅니다.");
					System.out.println("보고싶은 난이도를 선택하세요.");
					System.out.print("입력 :");
					String s = sc.next();
					System.out.println();
					System.out.println("처리능력은 낮을수록 좋은 점수입니다.(처리능력은 정답수, 걸린 시간으로 계산합니다)");
					System.out.println("랭크\t닉네임\t정답수\t처리능력\t선택 난이도");
					for (int j = 0; j < gp.gameList.size(); j++) {
						if(s.equals(gp.gameList.get(j).getLv())) {
							System.out.print((j+1)+"\t"+gp.gameList.get(j).getName()+"\t");
							System.out.print(gp.gameList.get(j).getScore()+"\t"+gp.gameList.get(j).getGrade());
							System.out.println("\t"+gp.gameList.get(j).getLv());
						}
					}
					System.out.println();
				}
				
			}else if(ch == 2){
				
			}
			else if(ch == 3){
				break;
			}
			
			gp.save();
		}
		
	}
}
