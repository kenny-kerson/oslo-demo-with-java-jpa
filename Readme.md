# Oslo Demo with Java & JPA
- ��ü������ȸ ��ü���� ������ ������Ʈ�� ������� with Java, JPA
- **����**
  * ���� Oslo ������Ʈ�� ������, Original Oslo Spec�� �ٸ���, JPA / Kafka / ... �� ���ο� ��������� �����غ��°�
  * �� ��, ���Ӱ� �����߰ų�, �ݺ������� �ʿ��� ����µ鿡 ���� ������ ����
  * ���߿�����ȭ�� ���� �׽�Ʈ ������Ʈ 
   
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
  * RDB : **PostgreSQL**( Local / Product ȯ�浿�� )
  * ORM : **JPA**
- Middleware
  * TBD : Redis, Kafka, ...
- External Library
  * Lombok
    * getter, constructor, builder �� �ݺ����� �ڵ����Ÿ� ���� ���
  * TBD : openfeign, ... 

## Project Structure
1. **��Ƽ ��� ������Ʈ**
   * `hub` : ����� ������Ʈ ����, circuit breaker, failover & fallback, throttling, ...
   * `biz` : ������ �� ���� ��� ���� 
2. **DDD ������ ��Ű¡ �� Ŭ���� ����**
   * account, customer, product, ...
   * ui, domain, infra, ...
3. **docker-compose�� ���� ���� ����ȯ�� ����**
   * RDB( PostgreSQL ), Redis, Kafka, ... 

## Local Develop Environment
1. Middleware ����
   * TBD
2. Application ����
   * TBD
3. Application ����
   * TBD 
4. Middleware ����
   * TBD

## Deploy
- TBD 

## APIs
- Application(WAS)���� �⵿�Ǵ� **Hub, Biz���� �����ϴ� API List**
### Hub
- TBD

### Biz
- TBD