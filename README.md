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
- Swagger : 개발한 Rest API를 문서화해주는 라이브러리, 문서화된 내용을 통해 관리 & API 호출을 통한 테스트를 가능케 함

## ERD
![ERD](https://github.com/IM-GYURI/reservation/assets/80020777/4f44925c-2823-4db1-b614-e30904c26461)


## Swagger
![Swagger_1](https://github.com/IM-GYURI/Weather-Diary/assets/80020777/7d008739-6360-482b-b93f-b5d77fe13abe)

### member-controller
![img.png](img.png)

#### POST : /member/signup
![img_1.png](img_1.png)
- 회원 가입 api
- Request
  - Body : name, email, password, phone, role
- Response
  - memberKey, name, email, role

#### POST : /member/signin
![img_2.png](img_2.png)
- 로그인 api
- Request
  - Body : email, password
- Response
  - Header : Authorization
  - memberKey, name, email, role

### store-controller
![Swagger_2](https://github.com/IM-GYURI/Weather-Diary/assets/80020777/e5e4ba09-68b5-43cd-a66a-7e487fce4470)

#### POST : /store/registration
![Swagger_3](https://github.com/IM-GYURI/Weather-Diary/assets/80020777/b6815808-4931-43b4-80b3-c6e3df93c125)
- 매장 등록 api
- Request
  - Header : Authorization (MANAGER)
  - Body : memberKey, storeName, address, description, phone
- Response
  - storeKey, storeName

#### PATCH : /store
![Swagger_4](https://github.com/IM-GYURI/Weather-Diary/assets/80020777/c88a98fb-5a92-4a08-835b-92a0850d4ecf)
- 매장 정보 수정 api
- Request
  - Header : Authorization (MANAGER)
  - Body : storeKey, storeName, address, description, phone
- Response
  - storeKey, storeName, address, description, phone

#### GET : /store/{storeKey}
![Swagger_5](https://github.com/IM-GYURI/Weather-Diary/assets/80020777/8c308d23-ad27-4c0b-8536-53a3570ab5d4)
- 매장 상세 정보 조회 api
- Request
  - Header : Authorization (MANAGER/CUSTOMER)
  - Path : storeKey
- Response
  - storeKey, name, address, description, phone

#### DELETE : /store/{storeKey}
![Swagger_6](https://github.com/IM-GYURI/Weather-Diary/assets/80020777/e9d61cb4-3f48-4320-87d0-d211b49cb9c4)
- 매장 삭제 api
- Request
  - Header : Authorization (MANAGER)
  - Path : storeKey
- Response
  - storeKey

#### GET : /store/sortedlist
![Swagger_7](https://github.com/IM-GYURI/Weather-Diary/assets/80020777/757dc6be-f8f2-47f4-b8fb-3e02440b9265)
- 매장 전체 조회 api (가나다순)
- Request
  - Header : Authorization (MANAGER/CUSTOMER)
- Response
  - List of storeName, address

#### GET : /store/search
![Swagger_8](https://github.com/IM-GYURI/Weather-Diary/assets/80020777/6ef43d3a-08e1-4a5d-a32e-bc03d83a3bfa)
- 매장 검색 api (자동 완성)
- Request
  - Header : Authorization (MANAGER/CUSTOMER)
  - Param : keyword
- Response
  - List of storeName

### reservation-controller
![img_3.png](img_3.png)

#### POST : /reserve
![img_4.png](img_4.png)
- 예약 진행 api
- Request
  - Header : Authorization (CUSTOMER)
  - Body : memberKey, storeName, address, description, phone(of member)
- Response
  - storeKey, storeName 

#### PATCH : /reserve/visit
![img_5.png](img_5.png)
- 방문 확인 api
- Request
  - Header : Authorization (CUSTOMER)
  - Body : memberKey, storeName, address, description, phone(of member)
- Response
  - reservationKey

#### GET : /reserve/{id}/{date}
![img_6.png](img_6.png)
- 날짜별 예약 정보
- Request
  - Header : Authorization (MANAGER/CUSTOMER)
  - Path : id(of store), date(of reservation)
- Response
  - Body : List of storeName, reservationKey, reservationDate, reservationTime, headcount, memberName, memberPhone

#### DELETE : /reserve/{reservationKey}
![img_7.png](img_7.png)
- 예약 취소 api
- Request
  - Header : Authorization (MANAGER/CUSTOMER)
  - Path : reservationKey
- Response
  - reservationKey

### review-controller
![img_8.png](img_8.png)

#### POST : /review
![img_9.png](img_9.png)
- 리뷰 작성 api
- Request
  - Header : Authorization (CUSTOMER)
  - Body : reservationKey, title, content, score
- Response
  - reviewId

#### PATCH : /review
![img_10.png](img_10.png)
- 리뷰 수정 api
- Request
  - Header : Authorization (CUSTOMER)
  - Body : reviewId, title, content, score
- Response
  - reviewId

#### DELETE : /review/{reviewId}
![img_11.png](img_11.png)
- 리뷰 삭제 api
- Request
  - Header : Authorization (MANAGER/CUSTOMER)
  - Path : reviewId
- Response
  - reviewId

### Schemas
![img_12.png](img_12.png)