# Oslo Demo with Java & JPA
- 전체계좌조회 자체구현 오슬로 프로젝트의 데모버전 with Java, JPA
- **목적**
  * 기존 Oslo 프로젝트의 로직을, Original Oslo Spec과 다르게, JPA / Kafka / ... 등 새로운 기술셋으로 구현해보는것
  * 그 외, 새롭게 습득했거나, 반복숙달이 필요한 기술셋들에 대한 실험적 적용
  * 개발역량강화를 위한 테스트 프로젝트 
   
## Index
- [Specification](#specification)
- [Project Structure](#project-structure)
- [Local Develop Environment](#local-develop-environment)
- [Deploy](#deploy)
- [APIs](#apis)

## Specification
- Language : **Java**
- JDK : **OpenJDK 11**
- Framework : **Spring Boot 2.3.3**
- DBMS
  * RDB : **PostgreSQL**( Local / Product 환경동일 )
  * ORM : **JPA**
- Middleware
  * TBD : Redis, Kafka, ...
- External Library
  * Lombok
    * getter, constructor, builder 등 반복적인 코드제거를 위해 사용
  * TBD : openfeign, ... 

## Project Structure
1. **멀티 모듈 프로젝트**
   * `hub` : 사용자 리퀘스트 서빙, circuit breaker, failover & fallback, throttling, ...
   * `biz` : 도메인 및 서비스 기능 구현 
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

## Deploy
- TBD 

## APIs
- Application(WAS)으로 기동되는 **Hub, Biz에서 제공하는 API List**
### Hub
- TBD

### Biz
- TBD