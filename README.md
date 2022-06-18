🛠스프링부트 블로그 만들기 프로젝트
================================

스프링부트로 블로그 만들기 


## 🪓개발환경
### Back-End 
- SpringBoot 2.5
- MySQL 8
### View
- Bootstrap4
- jsp
### Developer Tool
- Visual Studio Code
- STS4

## 🔨설치 방법(Installation Guide)
1) On the command line
    ```
    git clone https://github.com/easyhak/springboot-blog.git
    ```
2) inside eclipse or sts
   ```
   File > Import > Maven > Existing Mavem Projects
   ```
3) Then build on the command line
    ```
    ./mvnw generate-resources
    ```  
    or using the Eclipse launcher right click on project and
    ```
    Run As -> Maven install
    ```
    to generate the css. Run the application main method by right clicking on it and choosing.
    ```
    Run As -> Java Application
    ```
4) Visit [http://localhost:8000](http://localhost:8000) in your browser.
## 💻실행화면
![로그인](https://user-images.githubusercontent.com/48908552/174419557-74d934e6-c0e8-4991-b3d7-159a686b20c8.png)

![회원가입](https://user-images.githubusercontent.com/48908552/174419560-d0cdf5dc-5c91-4868-b342-0cefd4f20e1c.png)


![게시판](https://user-images.githubusercontent.com/48908552/174419565-c6638b6c-6688-4ce3-9882-070fede9b937.png)


![글쓰기](https://user-images.githubusercontent.com/48908552/174419569-4c6a99fc-d12e-4788-9d89-a8b0fed7359a.png)


![세부글](https://user-images.githubusercontent.com/48908552/174419580-9f4f21e2-9cd5-4e8a-acd2-96a99a4aa8b2.png)


## 🖥세부구현 항목

- Spring Security를 활용해서 로그인이 안된 사용자는 접근 못하는 항목이 있도록 함
- OAuth를 통해서 Kakao로그인을 구현
- view는 jsp를 이용해서 만들음
- Bootstrap4를 이용해서 꾸밈
- pagination
- CRUD
- 회원가입 form-check 

<div align="center">
             
![password-check](https://user-images.githubusercontent.com/48908552/174327734-432f2663-4465-47a9-97a8-f0b08f52a4d1.png)
![email-check](https://user-images.githubusercontent.com/48908552/174327743-aeaba59c-8fe3-49b3-bd29-f25d98b0fe91.png)
             
</div>
