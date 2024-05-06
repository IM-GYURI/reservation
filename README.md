# 매장 예약 서비스 프로젝트

## 구현 목록
- **공통 인증** : CUSTOMER/MANAGER
- **MANAGER**
  - 매장 등록
  - 매장 정보 수정
  - 매장 삭제
  - 리뷰 삭제
- **CUSTOMER**
  - 예약 진행
  - 도착 확인
  - 리뷰 작성
  - 리뷰 수정
  - 리뷰 삭제
 
## 개발 환경
- Java 17
- Spring boot 3.2.5
- Gradle
- Spring data JPA
- Spring Security
- Spring validation
- MariaDB

## 외부 라이브러리
- io.jsonwebtoken:jjwt-api:0.11.5 : JWT 발행 기능을 제공해주는 라이브러리
- Lombok : Model 클래스나 Entity 같은 도메인 클래스 등에 반복되는 getter, setter 등의 메소드를 자동으로 만들어주는 라이브러리

## ERD
![ERD](https://github.com/IM-GYURI/reservation/assets/80020777/4f44925c-2823-4db1-b614-e30904c26461)
