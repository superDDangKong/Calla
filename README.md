## Calla

> 해당 프로젝트는 spring으로 구현한 쇼핑몰사이트 입니다.
> <br>
> 쇼핑몰 + 중고거래 + 커뮤니티를 결합한 사이트를 구현하였습니다.

## 개발환경
  - OS : windows 10 64 bit
  - JAVA : Java JDK 11.0.2
  - DBMS : Oracle 11g
  - DB Client Tool : Oracle SQL Developer
  - WAS : Apache Tomcat 9.0v
  - IDE : Eclipse
  - Spring : STS 3.9.18
  - Build : Maven 3.8.1

## 개발환경 세팅
<details>
<summary>JAVA</summary>
1. <a href="https://jdk.java.net/archive/">JDK 11.0.2</a> 설치 및 압축 풀기<br/>
2. 환경 변수 설정 [내 pc] -> [속성] -> [고급 시스템 설정] -> [고급] -> [환경 변수] 탭 <br/> 
&nbsp;&nbsp;[사용자 변수] -> [새로 만들기] (변수 명: JAVA_HOME / 변수 값 : jdk-11.0.2 폴더가 존재하는 경로 ex) C:\Program Files\jdk-11.0.2) <br/>
&nbsp;&nbsp;[시스템 변수] -> [Path] -> [편집] -> [새로 만들기] -> [%JAVA_HOME%\bin\] -> [확인] <br/>
  * 설정확인 : [cmd](관리자 권한) -> java -version
</details>

<details>
<summary>DB</summary>
1. <a href="https://www.oracle.com/database/technologies/xe-prior-release-downloads.html">Oracle Database 11g Express Edition Release 11.2.0.2.0</a> 설치 및 exe 파일 실행 <br/>
2. 윈도우 시작창에서 Run SQL Command Line 선택 <br/>
&nbsp;&nbsp;● conn /as sysdba로 접속 <br/>
&nbsp;&nbsp;● CREATE scott IDENTIFIED BY tiger <br/>
&nbsp;&nbsp;● GRANT CONNECT, DBA, RESOURCE TO scott <br/>
3. <a href="https://www.oracle.com/tools/downloads/sqldev-downloads.html">SQL developer Windows 32-bit/64-bit</a> 설치 (자바 8이상 설치되어 있는 경우) 및 실행 <br/>
4. 호스트, 포트, SID를 다음과 같이 입력하고 접속 클릭 <br/>
   <img loading="lazy" src="https://github.com/superDDangKong/Calla/assets/140034486/61b774d8-123b-45fa-a35d-0d25efdd2645">
</details>

<details>
<summary>WAS</summary>
1. <a href="https://tomcat.apache.org/download-90.cgi">Apache Tomcat 9.0</a> 설치
</details>

<details>
<summary>eclipse</summary>
1. <a href="https://www.eclipse.org/downloads/">Eclipse IDE for Enterprise Java and Web Developers</a> 설치 및 압축 해제 <br/>
2. eclipse.isi 파일 열기 <br/>
&nbsp;&nbsp;● -vm [설치된 jdk 경로]\bin 변경<br/>
&nbsp;&nbsp;● -Dosgi.requiredJavaVersion = 11 변경<br/>
</details>

<details>
<summary>STS</summary>
1. <a href="https://github.com/spring-attic/toolsuite-distribution/wiki/Spring-Tool-Suite-3">STS 3.9.18 full distribution on Eclipse 4.21.zip</a> 설치 및 압축 해제 <br/>
2. sts.isi 파일 열기 <br/>
&nbsp;&nbsp;● -vm [설치된 jdk 경로]/bin/javaw.exe 변경<br/>
&nbsp;&nbsp;● -Dosgi.requiredJavaVersion = 11 변경<br/>
</details>

<details>
<summary>maven</summary>
1.<a href="https://maven.apache.org/download.cgi"> apache-maven-x.x.x.bin.zip</a> 설치 및 압축 해제 <br/>
2. apache-maven-x.x.x 폴더를 C:\Study 폴더로 복사(폴더 생성) <br/>
3. 환경 변수 설정 [내 pc] -> [속성] -> [고급 시스템 설정] -> [고급] -> [환경 변수] -> [시스템 변수] -> [path] -> <br/>
  ‘C:\Study\apache-maven-x.x.x\bin 경로 추가 <br/>
4. 오라클 설치된 경로 확인 ex) C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6.jar <br/>
5. 아래 코드를 cmd 화면에서 실행하여 Oracle JDBC driver를 로컬 메이븐 저장소에 추가 <br/>
  
```
mvn install:install-file
-Dfile=C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6.jar
-DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0
-Dpackaging=jar 
```
BUILD SUCCESS가 뜨면 설치 성공
</details>

<details>
<summary>Spring project 설정</summary>
1. Project → Properties → Project Facets → Dynamic Web Module Version 4.0 & Java Version 11로 변경 <br/>
2. Project → Properties → Server → 서버 선택 <br/>
&nbsp;&nbsp;Project → Properties → Targeted Runtimes → Apache Tomcat v9.0 선택<br/>
3. xml 설정 (현 프로젝트 코드 참고) <br/>
4. (Maven Update 수행)프로젝트 마우스 오른쪽 클릭 -> Maven -> Update Project <br/>

</details>


## 웹 실행 모습
<details> 
  <summary> 자유 게시판 메인 </summary> 
  <p>
    1. 게시글 데이터의 제목, 작성자, 조회수, 좋아요 등의 정보를 확인할 수 있습니다.<br/>
    2. 검색기능(작성자 or 제목+내용) 및 페이지네이션이 적용되어 있습니다.<br/>
    3. 로그인한 사용자는 글 작성이 가능합니다.
  </p>
  <img loading="lazy" src="https://github.com/superDDangKong/Calla/assets/140034486/0a3c93f6-bcd1-4648-be4c-04a3462b4724" width="500px">
  <br>
</details>

<details> 
  <summary> 자유 게시판 상세 </summary> 
  <p>
    1. 상세페이지 접근 시 쿠키를 생성해 조회수를 증가시킵니다.<br/>
    2. 작성자는 게시글을 수정 및 삭제할 수 있습니다.<br/>
    3. 로그인한 사용자는 댓글, 대댓글을 입력 가능하고, 작성자는 삭제할 수 있습니다.<br/>
    4. 댓글에 페이지네이션이 적용되어 있습니다.<br/>
  </p>
  <img loading="lazy" src="https://github.com/superDDangKong/Calla/assets/140034486/9c785eea-cd1a-4b69-a8e6-bc5f13457d4f" width="500px">
  <img loading="lazy" src="https://github.com/superDDangKong/Calla/assets/140034486/4b2f01c0-30d8-44ba-9b1f-68d6a42f8cd4" width="500px">
  <br>
</details>

<details> 
  <summary> 개인정보 확인/수정 </summary> 
  <p>
    1. 현재 개인정보를 조회할 수 있습니다.<br/>
    2. 원하는 정보만 선택해서 수정할 수 있습니다.<br/>
    3. 회원 탈퇴를 할 수 있습니다.<br/>
  </p>
  <img loading="lazy" src="https://github.com/superDDangKong/Calla/assets/140034486/65d35492-0cb6-4d58-abf2-09cf986fbe22" width="500px">
  <br>
</details>

<details> 
  <summary> 내가 쓴 글/댓글/좋아요 리스트 </summary> 
  <p>
    1. 더보기 형식의 페이지네이션이 적용되어 있습니다.<br/>
    2. 전체 조회 시, 전체 게시판 통합 조회가 가능하고, 각 카테고리 클릭 시, 해당 게시판의 내용만 조회 가능합니다.<br/>
    3. 좋아요 리스트의 경우, 좋아요 일괄 삭제가 가능합니다.<br/>
    4. 댓글에 페이지네이션이 적용되어 있습니다.<br/>
  </p>
  <img loading="lazy" src="https://github.com/superDDangKong/Calla/assets/140034486/84349232-4242-4fd8-b3f6-f355a4cb0269" width="500px">
  <img loading="lazy" src="https://github.com/superDDangKong/Calla/assets/140034486/fb83dd64-ad70-4af3-858c-60cf70f64a5d" width="500px">
  <br>
</details>

<details> 
  <summary> 최근 본 상품 </summary> 
  <p>
    1. 이전/다음 버튼 형식의 페이지네이션이 적용되어 있습니다.<br/>
    2. 최근 본 상품을 공용 상품/중고 상품 나눠서 조회할 수 있습니다.<br/>
    3. 상품 클릭 시 해당 상품 상세 페이지로 이동합니다.<br/>
    4. 내역에서 삭제할 수 있습니다.<br/>
    5. 조회한 후 30일이 지난 상품은 내역에서 사라집니다.<br/>
  </p>
  <img loading="lazy" src="https://github.com/superDDangKong/Calla/assets/140034486/83899c82-d322-44ea-815a-7fb6fdab6c68" width="500px">
  <br>
</details>

<details> 
  <summary> 헤더 </summary> 
  <p>
    1. 로그인 시, 알림/마이페이지/로그아웃 버튼을, 로그아웃 시, 로그인/회원가입 버튼을 생성합니다.<br/>
    2. 공용 상품/중고 상품을 카테고리별로 검색할 수 있습니다.<br/>
    3. 원하는 게시판으로 이동 가능한 버튼이 있습니다.<br/>
  </p>
  <img loading="lazy" src="https://github.com/superDDangKong/Calla/assets/140034486/5850a53e-93c1-4d68-8966-a0a638521339" width="500px">
  <br>
</details>

<details> 
  <summary> 알림 </summary> 
  <p>
    1. 내가 쓴 게시글/댓글에 댓글/답글이 입력될 시 작동합니다.<br/>
    2. 로그인 시, 보고있는 페이지 상단에 실시간으로 알람이 생성 되며 알림판에 저장 됩니다.<br/>
    3. 비로그인 시, 알림판에 알림이 저장되며 다음 로그인 시 확인이 가능합니다.<br/>
    4. 알림판은 읽지 않은 알림 -> 읽은 알림 순, 알림 온 시간 내림차순 정렬됩니다.<br/>
    5. 실시간 알람/알림판 클릭 시, 해당 댓글/답글로 페이지 이동 및 스크롤합니다.<br/>
    6. 알림판 내역 삭제가 가능합니다.<br/>
  </p>
  <img loading="lazy" src="https://github.com/superDDangKong/Calla/assets/140034486/e7aa5be5-2ed6-4fbe-93cd-c6294421ef56" width="500px">

  <br>
</details>

## 사용 기술

| 분류                 | Badge                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| -------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Front - end**      | <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&amp;logo=html5&amp;logoColor=white"> <img src="https://img.shields.io/badge/css3-1572B6?style=flat-square&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=flat-square&logo=javascript&logoColor=white"> <img src="https://img.shields.io/badge/jQuery-0769AD?style=flat-square&amp;logo=jQuery&amp;logoColor=white"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=flat-square&logo=bootstrap&logoColor=white"> |
| **Back - end**       | <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&amp;logo=Spring&amp;logoColor=white">                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| **Version Control**  | <img src="https://img.shields.io/badge/git-F05032?style=flat-square&logo=git&logoColor=white">                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| **DB**               | <img src="https://img.shields.io/badge/ORACLE-F80000?style=flat-square&logo=oracle&logoColor=white">                                                                                                                                                                                                                                                                                                                                                                                                                                             |

## ERD - Diagram

![calla DB 모델링](https://github.com/superDDangKong/Calla/assets/140034486/f616b63f-72bd-493e-adb0-2a521e5c6aab)

## 제작인원 및 기간

- **총 제작인원:** <a href="https://github.com/superDDangKong">박진성</a>, <a href="https://github.com/cocobono1">강정묵</a> <a href="https://github.com/prodo813">김홍석</a> <a href="https://github.com/DongGun01">이동건</a> | 해당 링크를 누르면 깃허브 페이지로 이동합니다.
- **제작 기간:** 2023/10/20 ~ 2023/12/15
