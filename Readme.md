# Oslo Demo with Java & JPA
- **개요**
  * 전체계좌조회 자체구현 오슬로 프로젝트의 데모버전 with Java, JPA
- **목적**
  * 기존 Oslo 프로젝트의 로직을, Original Oslo Spec과 다르게, JPA / Kafka / ... 등 새로운 기술셋으로 구현해보는것
  * 그 외, 새롭게 습득했거나, 반복숙달이 필요한 기술셋들에 대한 실험적 적용
  * 개발역량강화를 위한 테스트 프로젝트 
   
## Index
- [Specification](#specification)
- [Project Structure](#project-structure)
- [Local Develop Environment](#local-develop-environment)
- [APIs](#apis)
- [Deploy](#deploy)

## Specification
- Language : **Java**
- JDK : **OpenJDK 11**
- Framework : **Spring Boot 2.3.3**
- DBMS
  * RDB : **PostgreSQL**( Local / Product 환경동일 )
  * NoSQL : **MongoDB**( API로그 적재용 )
  * ORM : **JPA**
- Middleware
  * Cache, In-memory DB : **Redis** 5
  * Message Broker : **Kafka** 2.12-2.3  & **Zookeeper** 3.4.6 
- External Library
  * Lombok
    * getter, constructor, builder 등 반복적인 코드제거를 위해 사용
  * TBD : openfeign, ... 

## Project Structure
1. **멀티 모듈 프로젝트**
   * `hub` : 사용자 리퀘스트 서빙, circuit breaker, failover & fallback, throttling, ...
   * `biz` : 도메인 및 서비스 기능 구현
   * `consumer` : Message Broker의 Consumer와 같이 준실시간 처리로직 구현 
   * `infra` : 외부연동, NoSql, Message Broker 등 인프라 레벨의 실제 구현체 기능을 제공하는 모듈
      * `client-feign` : openfeign 구현모듈. hub -> biz 연동을 위해 사용 
      * `db-redis` : redis 구현모듈 for cache, memoryDB
      * `db-mongo` : mongoDB 구현모듈 for API request/response Logging
      * `broker-kafka` : kafka 구현모듈 for API 로깅 및 이벤트 or 준실시간 로직처리 
   * `common` : util, exception, dto 등 고수준 레벨의 모듈들이 공통으로 사용하는 기능구현. 가장 최소화된 구현.
   
2. **DDD 관점의 패키징 및 클래스 구성**
   * account, customer, product, ...
   * ui, domain, infra, ...
   
3. **docker-compose를 통한 로컬 개발환경 구성**
   * RDB( PostgreSQL ), Redis, Kafka, ... 

## Local Develop Environment
1. Middleware 시작
   * TBD
   
2. Application 시작
   * TBD
   
3. Application 종료
   * TBD
    
4. Middleware 종료
   * TBD
   
## APIs
- Application(WAS)으로 기동되는 **Hub, Biz에서 제공하는 API List**
### Hub
- 전체계좌조회 : `POST /v1/oslo/hub/customer/all_account_list`

### Biz
- 계좌기본 목록조회 : `POST /v1/oslo/biz/customer/base_account_list` 
- 수신계좌 정보조회 : `GET /v1/oslo/biz/account/dep/info`
- 여신계좌 정보조회 : `GET /v1/oslo/biz/account/loan/info`

## Deploy
- TBD 