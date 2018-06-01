import java.io.*;
import java.net.*;

class CalcAddServer 
{
	private ServerSocket server = null;
	private Socket socket = null;

	private BufferedWriter bw = null;
	private	BufferedReader br = null;
	private String exp;

	CalcAddServer () throws IOException {
		server = new ServerSocket(10001);
	}
	
	private boolean connectSocket () {
		boolean flag = false;
		
		try {
			this.socket = server.accept();
			System.out.println("연결 완료");
			flag = true;
			return flag;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return flag;
		}
	}

	private void receive () throws IOException {
		this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.exp = this.br.readLine();
	}

	private int calcuration () {
		String[] numbers = this.exp.split("\\+");
		
		int result = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
		return result;
	}

	private void send () throws IOException {
		this.bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
		int result = calcuration();
		this.bw.write(result+"\n"); // 결과값 전송
		this.bw.flush();	
	}

	private void closed () throws IOException {
		this.socket.close();
		this.br.close();
		this.bw.close();
		
		System.out.println("연결 끊음");
	}
	
	public void process () throws Exception{
		System.out.println("더하기 연산 서버");
		
		while(true){
			if(!connectSocket()) {
				System.out.println("소켓 연결 실패");
				continue;
			}
			receive();
			send();
			closed();
		}
	}
	
	public static void main(String[] args) {	
		CalcAddServer add = null;
		
		try {
			add = new CalcAddServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			add.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
