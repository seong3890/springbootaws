# portfolio
웹쇼핑몰 프로젝트 입니다.<br/>
인프런 김영한님 강의를 베이스로 만들었습니다.
<div>
개발:유명성 <br/>

JPA를 이용해 패치조인,배치,dto클래스를 이용해 최적화 하는데 집중함. <br/>

aws 서버 배포, 스프링 시큐리티 적용 예정 <br/>
디자인 개선 및  + 기능 업데이트 중 <br/>


</div>
<br/>
<h2>배포환경</h2>
<div>
<ul>
<li>aws 예정</li>
</ul>
</div>
<h1>사용한 기술스택</h1>
<div>
    <ul>
        <li>JAVA17</li>
        <li>Spring Boot</li>
        <li>Spring Interceptor</li>
        <li>Spring Data Jpa</li>
        <li>Querydsl</li>
        <li>Thymeleaf</li>
        <li>Mysql</li>
    </ul>
</div>
<br/>
<br/>

<h1>구현 기능</h1>
<div>
    <ul>
       <li>로그인,로그아웃,회원가입 </li>
	     <li>CRUD</li>
	     <li>게시판 페이징</li>
	     <li>상품등록</li>
	     <li>주문</li>
	     <li>주문목록</li>
     </ul>

  </div>
<div>
   <h1>트러블 슈팅</h1>
<details>
<summary>member.findAll() 조회가 안 되는 문제 발생</summary>
 osiv(Open Session In View: 영속성 컨텍스트를 뷰까지 적용)<br/>
  를 잠시 꺼놓아서 문제가 발생하였다. 다시 켜놓으니까 문제 해결<br/>
</details>

<details>
<summary>타임리프에서 LIst<inquiry > 를 모델로 넘겼는데 ${inquiry.name} 이 안됨.</summary>
th:each="inquirys:${inquiry}" 로 해결
</details>


<details>
<summary>N+1 문제</summary>
패치 조인과 배치 설정, DTO 변환으로 해결하고<br/>
일대다 관계는 따로 MAP<Long DTO>로 만들어서 foreach로 값을 채워주었다.
</details>	

<details>
<summary>페이징 오류</summary>
카운트 값을 따로 생성해서 해결<br/>
</details>

<details>
<summary>aws cpu 에러 </summary>
프리티어에서 제공해주는 t3.micro 사용중인데 cpu 92%이상 사용중이라며
502 에러가 뜸. 로그 확인해보니 ignoring types_hash_bucket_size 라고 나오고
SSH 세션 들어가서 확인해보니 서버 자체가 실행이 안 되어있음.
다른 가벼운 프로젝트는 정상적으로 실행이 돼서 설정을 잘못 한 건 아닌 걸 확인.
swap 기법이나 구글링,gpt에 물어본 결과 결국 t3.small로 업그레이드 해주는 방법 밖에
없어서 일단 보류 중.
<br/>
</details>

</div>
<div>
<h1>느낀점</h1>	
먼저 메모를 따로 하지 않아 일지를 작성하는데 어려움을 느낌<br/>
aws 배포, 시큐리티 적용 등 아직 할 게 산더미이다.</br>
</div>

<div>
<h1>사진</h1>
<details>
<summary>사진</summary>
![git1](https://github.com/user-attachments/assets/5aaeddf3-67a8-49b2-a47d-f5967303c0e4)<br/>
![git2](https://github.com/user-attachments/assets/65ee753d-86ac-4f36-a455-3bbf3501c691)<br/>

</details>


</div>
