import java.io.*;
import java.net.*;

class  CalcClient2
{
	private Socket socket = null;
	
	private BufferedWriter bw = null;
	private BufferedReader inputBr = null, br = null;
	private String exp;
	private final String IP = "127.0.0.1";
	
	CalcClient2 () {
		System.out.println("계산기 프로그램!");
		System.out.println("2항식만 가능합니다. ex)1+1");
	}

	private void inputExp () throws IOException {
		inputBr = new BufferedReader(new InputStreamReader(System.in));
		this.exp = inputBr.readLine();
	}
	
	private Boolean connectSocket() {
		boolean flag = false;
		try {
			if (exp.contains("+")) {
				// 더하기 서버 프로그램 연결
				socket = new Socket(IP,10001);
				flag = true;
			}else if(exp.contains("-")) {
				// 빼기 
				socket = new Socket(IP,10002);
				flag = true;
			}else if (exp.contains("*")) {
				// 곱하기
				socket = new Socket(IP,10003);
				flag = true;
			}else if (exp.contains("/")) {
				// 나누기
				socket = new Socket(IP,10004);
				flag = true;
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			return flag;	
		}
	}

	private void send() throws IOException{
		bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 계산식 전송
		bw.write(this.exp+"\n");
		bw.flush();
	}
	
	private void receive() throws IOException{
		String result = br.readLine();

		System.out.println("계산 결과 : "+result);
	}
	
	// 계산결과 받음
	private void closed() throws IOException {
		inputBr.close();
		br.close();
		bw.close();
		socket.close();
	}

	public void process() throws Exception{
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
		
		try {
			calc.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
