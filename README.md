# Calculator
자바 네트워크 프로그래밍 과제입니다. 
## CUI-Calculator
![calc](https://github.com/lowelllll/Calculator/blob/master/img/calc2.PNG?raw=true)

TCP-IP 소켓 프로그래밍으로 구현한 계산기 프로그램.  

## GUI-Calculator
![calc-gui](https://github.com/lowelllll/Calculator/blob/master/img/calc.PNG?raw=true)

JSP tomcat WAS 에서 동작하는 계산기 프로그램.

## 로컬 도메인 변경
windows 기준.

- C:\Windows\System32\drivers\etc 경로에 있는 hosts 파일을 수정.  
    hosts 파일은 관리자의 권한이 있어야 하므로 메모장을 관리자 권한으로 연 다음,    
    hosts 파일을 열어 수정.  

    컴퓨터에 보안이나 백신 프로그램이 깔려있을 경우 파일 변경이 되지 않을 수 있으므로
    프로그램을 잠깐 꺼논 후 수정.

```
...

127.0.0.1       test.com
```
마지막 줄에 추가합니다.  

test.com 도메인으로 로컬 서버를 접속할 수 있음.

