# Practical testing Guide

---

#### Spring Boot Version : 3.2.4
#### JDK Version : 17
#### Library : Spring Web, Spring Data Jpa, Lombok, H2 Database

---

### Section 1. 테스트는 왜 필요할까?
- 올바른 테스트 코드
  - 자동화 테스트로 비교적 빠른 시간 안에 버그를 발생할 수 있고, 수동 테스트에 드는 비용을 크게 절약할 수 있다.
  - 소프트웨어의 빠른 변화를 지원한다.
  - 팀원들의 집단 지성을 팀 차원의 이익으로 승격시킨다.
  - 가까이 보면 느리지만, 멀리 보면 가장 빠르다.

### Section 2. 단위 테스트
- **Keyword** 
  - `단위 테스트`
  - `수동 테스트, 자동화 테스트`
  - `Junit5, AssertJ`
  - `해피 케이스, 예외 케이스`
  - `경계값 테스트`
  - `테스트하기 쉬운/어려운 영역 (순수함수)`
- 단위 테스트
  - 작은 코드 단위를 독립적으로 검증하는 테스트
  - 검증 속도가 빠르고, 안정적이다. (외부 API에 의존하지 않기 때문)
- [Junit 5](https://junit.org/junit5/)
  - 단위 테스트를 위한 테스트 프레임워크
  - XUnit 시리즈 - Kent Beck : SUnit(Smalltalk), JUnit(Java), NUnit(.NET), ...
- [AssertJ](https://joel-costigliola.github.io/assertj/)
  - 테스트 코드 작성을 원할하게 돕는 테스트 라이브러리
  - 풍부한 API, 메서드 체이닝 지원
- 세분화된 테스트 케이스
  - 경계값 테스트 : 범위(이상, 이하, 초과, 미만), 구간, 날짜 등
    - 해피 케이스
    - 예외 케이스
  - 테스트하기 어려운 영역을 구분하고 분리하기
    - 관측할 때마다 다른 값에 의존하는 코드 : 현재 날짜/시간, 랜덤 값, 전역 변수/함수, 사용자 입력 등
    - 외부 세계에 영향을 주는 코드 : 표준 출력, 메시지 발송, 데이터베이스에 기록하기 등
  - 테스트하기 쉬운 영역 (순수 함수)
    - 같은 입력에는 항상 같은 결과
    - 외부 세상과 단절된 형태
    - 테스트하기 쉬운 코드

### Section 3. TDD (Test Driven Development)
- **Keyword** 
  - `TDD`
  - `RED-GREEN-REFACTORING`
  - `애자일 방법론 vs 폭포수 방법론`
  - `익스트림 프로그래밍(XP, eXtreme Programming`
  - `스크럼(scrum), 칸반(kanban)`
- Test Driven Development
  - 프로덕션 코드보다 테스트 코드를 먼저 작성하여 테스트가 구현 과정을 주도하도록 하는 방법론
    1. RED(실패하는 테스트 작성) 
    2. GREEN(테스트 통과, 최소한의 코딩, 빠른 시간 내에)
    3. REFACTOR(구현 코드 개선, 테스트 통과 유지)
  - 선 기능 구현, 후 테스트 작성
    - 테스트 자체의 누락 가능성
    - 특정(해피 케이스) 테스트 케이스만 검증할 가능성
    - 잘못된 구현을 다소 늦게 발견할 가능성
    - 복잡도가 낮은(유연하며 유지보수가 쉬운), 테스트 가능한 코드로 구현할 수 있게 하다.
    - 쉽게 발견하기 어려운 엣지(Edge) 케이스를 놓치지 않게 해준다.
    - 구현에 대한 빠른 피드백을 받을 수 있다.
    - 과감한 리팩토링이 가능해진다.
  - 관점의 변화
    - 테스트는 구현부 검증을 위한 보조 수단 -> 테스트와 상호 작용하며 발전하는 구현부
    - 클라이언트 관점에서의 피드백을 주는 Test Driven

### Section 4. 테스트는 "문서"다.
- **Keyword**
  - `@DisplayName`
  - `Given-When-Then`
  - `TDD vs BDD`
  - `JUnit vs Spock`
- 문서?
  - 프로덕션 기능을 설명하는 테스트 코드 문서
  - 다양한 테스트 케이스를 통해 프로덕션 코드를 이해하는 시각과 관점을 보완
  - 어느 한 사람이 과거에 경험했던 고민의 결과물을 팀 차원으로 승격시켜서, 모두의 자산으로 공유할 수 있다.
- DisplayName을 섬세하게
  - 명사의 나열보다 문장으로
  - 테스트 행위에 대한 결과까지 기술하기
  - 도메인 용어를 사용하여 한층 추상화된 내용을 담기
  - 테스트의 현상을 중점으로 기술하지 말 것
  - 예시
    - 음료를 1개 추가하면 주문 목록에 담긴다.
    - 영업 시작 시간 이전에는 주문을 생성할 수 없다.
- BDD (Behavior Driven Development) 스타일로 작성하기
  - TDD에서 파생된 개발 방법
  - 함수 단위에 테스트에 집중하기보다, 시나리오에 기반한 테스트케이스(TC) 자체에 집중하여 테스트한다.
  - 개발자가 아닌 사람이 봐도 이해할 수 있을 정도의 추상화 수준(레벨) 권장
  - Given - When - Then
    - Given : 시나리오 진행에 필요한 모든 준비 과정 (객체, 값, 상태 등)
    - When : 시나리오 행동 진행
    - Then : 시나리오 진행에 대한 결과 명시, 검증
    - 어떤 환경에서(Given), 어떤 행동을 진행했을 때(When), 어떤 상태 변화가 일어난다(Then).

### Section 5. Spring & JPA 기반 테스트
- **Keyword**
  - `Layered Architecture`
  - `Hexagonal Architecture`
  - `단위 테스트 vs 통합 테스트`
  - `IoC, DI, AOP`
  - `ORM, 패러다임의 불일치`
  - `Spring Data JPA`
  - `QueryDSL - 타입체크, 동적쿼리`
  - `@SpringBootTest vs @DataJpaTest`
  - `@SpringBootTest vs @WebMvcTest`
  - `@Transcational(readOnly = true)`
  - `Optimistic Lock, Pessimistic Lock`
  - `CQRS`
  - `@RestControllerAdvice, @ExceptionHandler`
  - `Spring bean validation`
  - `@WebMvcTest`
  - `ObjectMapper`
  - `Mock, Mockito, @MockBean`
- Layered Architecture -> 관심사의 분리
  - Presentation Layer
    - 외부 세계의 요청을 가장 먼저 받는 계층
    - 파라미터에 대한 최소한의 검증을 수행한다.
  - Business Layer
    - 비즈니스 로직을 구현하는 역할
    - Persistence Layer와의 상호작용을 통해 비즈니스 로직을 전개시킨다.
    - **트랜잭션**을 보장해야 한다.
  - Persistence Layer
    - Data Access의 역할
    - 비즈니스 가공 로직이 포함되어서는 안 된다. (Data에 대한 CRUD에만 집중한 레이어)
- 통합 테스트
  - 여러 모듈이 협력하는 기능을 통합적으로 검증하는 테스트
  - 일반적으로 작은 범위의 단위 테스트만으로는 기능 전체의 신뢰성을 보장할 수 없다.
  - 풍부한 단위 테스트 & 큰 기능 단위를 검증하는 통합 테스트
- @SpringBootTest vs @DataJpaTest : 트랜잭션
- 테스트에서 @Transactional을 조심해서 사용하자!
- Mock(가짜) 객체를 사용해 스프링 MVC 동작을 재현할 수 있는 테스트 프레임워크

### Section 6. Mock을 마주하는 자세
- **Keyword**
  - `Test Double, Stubbing (dummy, fake, stub, spy, mock`
  - `@Mock, @MockBean, @Spy, @SpyBean, @InjectMocks`
  - `BDDMokito`
  - `Classicist vs Mockist`
- Test Double
  - Dummy : 아무 것도 하지 않는 깡통 객체
  - Fake : 단순한 형태로 동일한 기능은 수행하나, 프로덕션에서 쓰기에는 부족한 객체 (ex. FakeRepository)
  - Stub : 테스트에서 요청한 것에 대해 미리 준비한 결과를 제공하는 객체, 그 외에는 응답하지 않는다.
  - Spy : Stub이면서 호출된 내용을 기록하여 보여줄 수 있는 객체, 일부는 실제 객체처럼 동작시키고 일부만 Stubbing 할 수 있다.
  - Mock : 행위에 대한 기대를 명세하고, 그에 따라 동작하도록 만들어진 객체
- Stub vs Mock
  - Stub : 상태 검증 (State Verification)
  - Mock : 행위 검증 (Behavior Verification)
- @Mock vs @Spy
  - 일부는 실제 객체의 기능을 사용하고 싶고, 일부는 Stubbing 하고 싶은 경우 @Spy를 사용한다.
- Mockito vs BDDMockito
  - BDD에 맞게 기존 Mockito를 감싼 프레임워크

### Section 7. 더 나은 테스트를 작성하기 위한 구체적 조언
- **Keyword**
  - `테스트 하나 당 목적은 하나`
  - `완벽한 제어`
  - `테스트 환경의 독립성, 테스트 간 독립성`
  - `Test Fixture`
  - `deleteAll(), deleteAllInBatch()`
  - `@ParameterizedTest, @DynamicTest`
  - `수행 환경 통합하기`
  - `private method test`
  - `테스트에서만 필요한 코드`
- 한 문단에 한 주제 : 테스트 코드에는 분기문, 반복문과 같은 로직이 들어가지 않는 것이 좋다. -> @ParameterizedTest 활용
- 완벽하게 제어하기 : 제어할 수 없는 값(외부 시스템, 현재 시간)들은 상위 Layer로 이전해서 테스트 코드가 용이하게 한다.
- 테스트 환경의 독립성을 보장하자!
- 테스트 간 독립성을 보장하자! : 테스트 간 공유 자원을 사용하는 것을 지양하자. -> @DynamicTest
- Test Fixture (given 절에서 사용하는 고정 값들을 칭함)
  - Fixture : 고정물, 고정되어 있는 물체
  - 테스트를 위해 원하는 상태로 고정시킨 일련의 객체
- Test Fixture 클렌징
  - deleteAll() vs deleteAllInBatch() : deleteAllInBatch()를 권장! deleteAll()의 경우 내부 로직에 의해 성능 저하
- @ParameterizedTest
  - 값을 변경하여 테스트를 하고 싶은 경우
  - [Junit5 Parameterized Tests](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests)
    - @ValueSource, @MethodSource, @CsvSource ...
  - [ProductTypeTest.java](src%2Ftest%2Fjava%2Fsample%2Fcafekiosk%2Fspring%2Fdomain%2Fproduct%2FProductTypeTest.java)
- @DynamicTest
  - 공유 자원을 사용하며 여러 테스트를 시나리오대로 검증하고 싶은 경우
  - [StockTest.java](src%2Ftest%2Fjava%2Fsample%2Fcafekiosk%2Fspring%2Fdomain%2Fstock%2FStockTest.java)
- 테스트 수행도 비용이다. 환경 통합하기
  - [ControllerTestSupport.java](src%2Ftest%2Fjava%2Fsample%2Fcafekiosk%2Fspring%2FControllerTestSupport.java)
  - [IntegrationTestSupport.java](src%2Ftest%2Fjava%2Fsample%2Fcafekiosk%2Fspring%2FIntegrationTestSupport.java)
- private 메서드의 테스트는 어떻게 하나요?
  - 하지말것! -> 객체를 분리할 시점인가?에 대한 고민을 해보자!
  - [ProductNumberFactory.java](src%2Fmain%2Fjava%2Fsample%2Fcafekiosk%2Fspring%2Fapi%2Fservice%2Fproduct%2FProductNumberFactory.java)
- 테스트에서만 필요한 메서드가 생겼는데 프로덕션 코드에서는 필요 없다면? -> 만들어도 된다. 하지만 **보수적**으로 접근하자.

### Section 8. Appendix
- 학습 테스트
  - 잘 모르는 기능, 라이브러리, 프레임워크를 학습하기 위해 작성하는 테스트
  - 여러 테스트 케이스를 스스로 정의하고 검증하는 과정을 통해 보다 구체적인 동작과 기능을 학습할 수 있다.
  - 관련 문서만 읽는 것보다 훨씬 재미있게 학습할 수 있다.
- Spring REST Docs
