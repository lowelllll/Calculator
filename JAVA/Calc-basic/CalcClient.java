import java.io.*;
import java.net.*;

class CalcClient 
{
	private Socket socket = null;
	
	private BufferedWriter bw = null;
	private BufferedReader input_br = null , br =null ;

	public CalcClient(){
		try{
			socket = new Socket("127.0.0.1",8000);
			// 계산기 서버와 연결.
		
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			br = new BufferedReader(in);
				
			OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
			bw = new BufferedWriter(out);
			
			
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public void send(){
		// System.in으로 계산식을 받음.
		try{
			System.out.println("계산식을 입력해주세요.(1항식만 가능)");
			input_br = new BufferedReader(new InputStreamReader(System.in));
			String line = input_br.readLine();
			
			bw.write(line+"\n");
			// readLine은 줄 바꿈이 있을 때까지 문자열을 읽어들이기 때문에 꼭 문자열 끝에 \n을 추가해준다!
			bw.flush();
			
			System.out.println("계산중...");

		}catch(IOException e){
			e.printStackTrace();
		}
		// 소켓에 계산식을 보냄.
	}

	public void receive(){
		try{
			String receive_word = br.readLine();
			System.out.println("결과:"+receive_word);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void close(){
		try{
			System.out.println("계산기 프로그램 종료");
			input_br.close();
			bw.close();
			br.close();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		CalcClient client = new CalcClient();
		client.send();
		client.receive();
		client.close();
	}
}
