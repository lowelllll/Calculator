import java.io.*;
import java.net.*;

class  CalcClient2
{
	private Socket socket = null;
	
	private BufferedWriter bw = null;
	private BufferedReader inputBr = null, br = null;
	private String exp;

	CalcClient2 () {
		System.out.println("계산기 프로그램!");
		System.out.println("2항식만 가능합니다. ex)1+1");
	}

	private void inputExp () {
		try{
			inputBr = new BufferedReader(new InputStreamReader(System.in));
			this.exp = inputBr.readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private Boolean connectSocket(){
		try {
			if (exp.contains("+")) {
				// 더하기 서버 프로그램 연결
				socket = new Socket("127.0.0.1",10001);
				return true;

			}else if(exp.contains("-")) {
				// 빼기 
				socket = new Socket("127.0.0.1",10002);
				return true;

			}else if (exp.contains("*")) {
				// 곱하기
				socket = new Socket("127.0.0.1",10003);
				return true;

			}else if (exp.contains("/")) {
				// 나누기
				socket = new Socket("127.0.0.1",10004);
				return true;

			}

		}catch(IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	private void send() {
		try{
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// 계산식 전송
			bw.write(this.exp+"\n");
			bw.flush();

		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void receive() {
		try{
			String result = br.readLine();

			System.out.println("계산 결과 : "+result);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// 계산결과 받음
	private void closed(){
		try{
			inputBr.close();
			br.close();
			bw.close();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void run() {
		inputExp();

		if(!connectSocket()){
			System.out.println("소켓 연결 실패.");
			return;
		}
		
		send();
		receive();
		closed();
		
	}


	public static void main(String[] args) 
	{
		CalcClient2 calc = new CalcClient2();
		calc.run();
	}
}
