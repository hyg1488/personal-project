package SpeedGame;

import java.io.*;
import java.util.*;
public class GameProcessing {
	List <GameData> gameList = new ArrayList<GameData>();
	Scanner sc = new Scanner(System.in);
	int answer = 0;
	int n = 0;
	int grade = 0;
	String name;
	String lv;
	
	public GameProcessing() {
		
	}
	public GameProcessing(int n) {
		this.n = n;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void add(int sum) {
		this.grade += sum;
	}
	public boolean load() {
		boolean b;
		try {
			File ff = new File("c:\\data\\data.txt");
			ff.createNewFile();
			FileInputStream fis = new FileInputStream(ff);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			gameList = (List <GameData>) ois.readObject(); 
			ois.close();
			b = true;
			return b;
		}
		catch(Exception e) {
			System.out.println("데이터가 없습니다.");
			b = false;
			return b;
		}
	}
	
	public void save() {
		try {
			File ff = new File("c:\\data\\data.txt");
			ff.createNewFile();
			ObjectOutput oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(ff)));
			oos.writeObject(gameList);
			oos.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void record() {
		name = sc.next();
		gameList.add(new GameData());
	}
	
	public String setting() {
		System.out.println("난이도를 입력 하세요.");
		System.out.println("상 / 중 / 하");
		lv ="하";
		boolean b = true;
		while(b) {
			System.out.print("입력 :");
			lv = sc.next();
			
			if(lv.equals("상")) {
				System.out.println("Message : 난이도는 상으로 설정되었습니다.");
				b=false;
				n=10;
				grade = 100;
			}else if(lv.equals("중")) {
				System.out.println("Message : 난이도는 중으로 설정되었습니다.");
				b=false;
				n=8;
				grade = 80;
			}else if(lv.equals("하")) {
				System.out.println("Message : 난이도는 하으로 설정되었습니다.");
				b=false;
				n=5;
				grade = 50;
			}else {
				System.out.println("입력은 상/중/하 중에서 하나만 입력하세요.");
			}
		}
		return lv;
	}
	
	public void setName() {
		boolean bo = true;
		
		while(bo) {
			System.out.println("닉네임을 입력해주세요.");
			System.out.print("입력 :");
			name = sc.next();
			while(true) {
				System.out.println(name +"이 맞습니까? (YES / NO)");
				String an = sc.next().toUpperCase();
				if(an.equals("YES")) {
					System.out.println("닉네임이 정해졌습니다."+ name +"님 환영합니다.");
					bo=false;
					break;
				}
				else if(an.equals("NO")) {
					System.out.println("닉네임을 다시 설정합니다.");
					break;
				}
				else {
					System.out.println("제대로 대답해주십시요.");
				}
			}
		}
		
		while(true) {
			System.out.println("YES 를 입력하면 타이머가 시작됩니다.");
			System.out.println("마음의 준비를 하시고 Start 를 입력해주세요.");
			System.out.print("입력 :");
			String an = sc.next().toUpperCase();
			if(an.equals("START")) {
				System.out.println(name +"님 게임을 시작합니다.");
				bo=false;
				break;
			}else {
				System.out.println("Yes를 누르면 시작입니다.");
			}
		}
	}
	
	public void gameRule() {
		System.out.println();
		System.out.println("게임 설명");
		System.out.println("이 게임은 단순 연산 게임입니다만, 한가지 다른 점이 있습니다.");
		System.out.println("문제에 나오는 모든 연산자는 우선순위가 없습니다. ");
		System.out.println(" 예시 ) 10-10*54 인 경우 54*10을 먼저 계산하는 것이 원칙이지만");
		System.out.println("       이 게임에서는 앞에서부터 순서대로 계산한 값이 정답입니다.");
		System.out.println("     즉, 정답은 10-10 => 0 , 0*54 = 0 답은 0");
		System.out.println();System.out.println();System.out.println();
		System.out.println("그리고 모든 연산에서 나온 값 또한 정수로 결과를 낸 후 정수값으로 다음 문제를 풉니다");
		System.out.println(" 예시 ) 91/84*77 인 경우, 91/84 계산시 1.0833... 이지만 계산값을 1로 본다.");
		System.out.println("그후, 1*77 가 계산되어 답은 77이 된다.");
		System.out.println();System.out.println();System.out.println();
		System.out.println("그리고 이 게임의 모든 정답은 정수로 적어주십시요!");
		System.out.println("즉, 소수점은 무시하고 앞 정수만 정답을 적어주십시요.");
		System.out.println("예시 ) 1.531 가 정답일경우 1만 적으시면 됩니다. 반올림 X ");
	}
	
	public void game(String lv) {
		long[] ekqwl = new long[n];
		boolean bo = true;
		
		for (int i = 0; i < n; i++) {
			long resualt = 0;
			int b = 0;
			int c = 0;
			int cnt = 0;
			
			
			if(lv.equals("하")) {
				b = (int) (Math.random()*3+2);
			}else if(lv.equals("중")) {
				b = (int) (Math.random()*4+3);
			}else if(lv.equals("상")) {
				b = (int) (Math.random()*5+4);
			}
			System.out.println();
			System.out.println((i+1)+"번째 문제");
			int [] question = new int [b];
			int [] computation = new int [b-1];

			for (int k = 0; k < question.length; k++) {
				question[k]= (int)(Math.random()*100+1);
			}
			
			for (int k = 0; k < b-1; k++) {
				if(k>0) {
					if(computation[k-1] == 3) {
						c++;
					} 
				}
				
				if(c>3) {
					computation[k] = (int)(Math.random()*3);
				}else {
					computation[k] = (int)(Math.random()*4);
				}
			}
			
			for (int k = 0; k < question.length; k++) {
				if(k==0) {
					resualt += question[k];
					System.out.print(resualt);
				}
				else{
					switch(computation[cnt]) {
						case 0:	System.out.print("+"+question[k]);
							resualt += question[k];
							cnt++;
							break;
						case 1:	System.out.print("-"+question[k]);
							resualt -= question[k];
							cnt++;
							break;
						case 2:	System.out.print("/"+question[k]);
							resualt /= question[k];
							cnt++;
							break;
						case 3:	System.out.print("*"+question[k]);
							resualt *= question[k];
							cnt++;
							break;
					}
				}
			}
			
			System.out.println(" = ?");
			System.out.print("정답 :");
			long answer = sc.nextInt();
			ekqwl[i] = resualt;
			if(answer == resualt) {
				System.out.println("정답입니다.");
				this.answer ++;
				grade -= 10;
			}
			else {
				System.out.println("오답입니다.");
			}
			
			resualt = 0;
		}
		System.out.println();
		System.out.println("답지");
		for (int i = 0; i < n; i++) {
			System.out.println((i+1)+"번 : "+ekqwl[i]);
		}
	}
	public void check(){
		System.out.println("내 성적 : "+answer+"개 정답, "+(answer/n*100)+"점");
		gameList.add(new GameData(answer, name,grade,lv));
	}
	
}
