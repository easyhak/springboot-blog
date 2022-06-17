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

## 💻세부구현 항목
- Spring Security를 활용해서 로그인이 안된 사용자는 접근 못하는 항목이 있도록 함
- OAuth를 통해서 Kakao 로그인을 구현
- view는 jsp를 이용해서 만들음
- Bootstrap4를 이용해서 꾸밈
- javascript ajax   
- form-check  
- view page made by jsp   