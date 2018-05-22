import java.io.*;
import java.net.*;

class CalcMulServer 
{
	private ServerSocket server = null;

	CalcMulServer () {
		try {
			server = new ServerSocket(10003);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public void process () {
		while(true){
			try{
				System.out.println("곱하기 연산 서버");
				System.out.println("클라이언트와의 연결 기다리는 중..");

				Socket socket = server.accept();
				System.out.println("연결 완료");
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// 계산식을 받음
				String exp = br.readLine();

				String[] numbers = exp.split("\\*");

				// 더하기 계산
				int result = Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
				
				// 결과 전송
				bw.write(result+"\n");
				bw.flush();
				
				// 연결 끊음.
				br.close();
				bw.close();
				socket.close();
				
				System.out.println("연결 끊음");
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) 
	{
		CalcMulServer mul = new CalcMulServer();
		mul.process();
	}
}
