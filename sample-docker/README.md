# Sample 프로젝트

## docker-compose 로컬 환경
- kafka
- zookeeper
- mysql (관련 스키마를 생성하여 기동 됨)
- zipkin
- Eventuate CDC Service

## 도커 컨테이너 실행 명령어
- docker-compose up -d

## hosts 파일 등록
- 127.0.0.1       mysql
- 127.0.0.1       broker
- 127.0.0.1       zipkin
- 127.0.0.1       cdcservice

## DB 스키마 생성
- CREATE SCHEMA `account` DEFAULT CHARACTER SET utf8 ;
- CREATE SCHEMA `board` DEFAULT CHARACTER SET utf8 ;
- 각각의 스키마에서 ./mysql/template.sql 실행

## 접속 정보
- kafka 토픽내용 확인
  - http://localhost:3030/
- 서비스 Tracing
  - http://localhost:9411/
- Account 서비스 Swagger UI
  - http://localhost:9091/com/swagger-ui/index.html
- Board 서비스 Swagger UI
  - http://localhost:9090/com/swagger-ui/index.html

### 이벤트 기반 비동기 메시징
도메인에서 발생되는 모든 이벤트를 누락없이 Event Store(kafka)에 기록하여 다른 서비스에서 활용.
샘플은 Account 서비스 및 Board 서비스의 Entity에 Created, Updated, Deleted 이벤트가 발생하면
kafka에 저장되고 해당 Event를 구독하는 서비스에서 처리함.
- Swagger에서 POST/PUT/DELETE 실행
- kafka topic 내용확인
  - http://localhost:3030/ 접속하여 해당 Entity 이름의 topic 내용 확인
 
### 커멘드 기반 비동기 메시징
서비스에서 타 서비스에 메세지를 전달할때, 비동기로 전달하기 위해 Kafka를 사용하는 방법으로
샘플은 타 서비스에 메세지를 전달하고, 결과를 수신하는 Command 방식을 구현함.
예를 들어, Board 서비스에서 게시글을 삭제하고 Account 서비스에 작성자 삭제 Command를 전송하면
Account 서비스에서 해당 사용자 삭제가 처리되었음 Board 서비스에 메시지로 리턴함.
- Board 서비스의 Swagger UI에서 DELETE 실행
- kafka topic 내용확인
  - http://localhost:3030/ 접속하여 해당 Command 및 Reply Channel 이름의 topic 내용 확인

### FeignClient 기반 동기 메시징
Netflix OSS에서 제공된 Http Client 임. Interface만 작성하면 되므로 RestTemplate 보다 사용이 간편함.
안정적 서비스 호출을 위해 Istio의 Circuit Breaker 기능을 적용함.
