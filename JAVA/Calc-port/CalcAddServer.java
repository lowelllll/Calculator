import java.io.*;
import java.net.*;

class CalcAddServer 
{
	private ServerSocket server = null;
	private Socket socket = null;

	private BufferedWriter bw = null;
	private	BufferedReader br = null;
	private String exp;

	CalcAddServer () {
		try {
			server = new ServerSocket(10001);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	private boolean connectSocket () {
		try{
			this.socket = server.accept();
			System.out.println("연결 완료");
			this.bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
			this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	private void receive () {
		try {
			this.exp = this.br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int calcuration () {
		String[] numbers = this.exp.split("\\+");
		// 더하기 계산
		int result = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
		return result;
	}

	private void send () {
		try {
			int result = calcuration();
			this.bw.write(result+"\n"); // 결과값 전송
			this.bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	private void closed () {
		try {
			this.socket.close();
			this.br.close();
			this.bw.close();
			System.out.println("연결 끊음");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public void process () {
		while(true){
				System.out.println("더하기 연산 서버");
				System.out.println("클라이언트와의 연결 기다리는 중..");	
				if(!connectSocket()) {
					System.out.println("소켓 연결 실패");
					continue;
				}
				receive();
				send();
				closed();
		}
	}
	public static void main(String[] args) 
	{
		CalcAddServer add = new CalcAddServer();
		add.process();
	}
}
