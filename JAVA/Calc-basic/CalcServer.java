import java.io.*;
import java.net.*;

class CalcServer
{

	private ServerSocket server = null;

	CalcServer(){
		try{
			server = new ServerSocket(8000);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void process(){
		while(true){
			try{
				System.out.println("계산기 프로그램!");
				System.out.println("클라이언트와의 접속을 기다리는 중..");

				Socket con = server.accept();

				System.out.println("접속 완료");

				// 문자 스트림

				InputStreamReader in = new InputStreamReader(con.getInputStream());
				BufferedReader br = new BufferedReader(in);
				
				OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
				BufferedWriter bw = new BufferedWriter(out);
					
				String line = br.readLine();
				// 계산식 받음
				
				int result = calc(line);

				System.out.println("계산 완료, 결과:"+result+"\n");

				bw.write(result+"\n");
				bw.flush();

				bw.close();
				con.close();
				
				// 서버 소켓은 닫아두지 않으므로 계속 해서 클라이언트의 연결을 기다림.
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	private int calc(String line){
		String[] expression = line.split("\\s");
				// 공백을 기준으로 식을 나눔
				
		String op = expression[1];

		int result=0;
		int num1 = Integer.parseInt(expression[0]), num2 = Integer.parseInt(expression[2]);
				
		// 계산식 
		if(op.equals("+"))
			result = num1+num2 ;
		else if(op.equals("-"))
			result = num1-num2 ;
		else if(op.equals("*"))
			result = num1 * num2 ;
		else if(op.equals("/"))
			result = num1 / num2;

		return result;
	}

	public static void main(String[] args) 
	{
		CalcServer cs = new CalcServer();
		cs.process();
	}
}
