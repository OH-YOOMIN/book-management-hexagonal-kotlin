# Book Management Hexagonal Kotlin

이 프로젝트는 **헥사고날 아키텍처(Hexagonal Architecture, Ports & Adapters)**를 적용한 **Book Management API** 예제입니다.
도메인 로직을 중심으로 외부 의존성을 분리하여 유지보수성과 확장성을 극대화하는 것을 목표로 합니다.

---

## 📝 배경 및 동기

이 프로젝트는 이전에 **Java 기반 클린 아키텍처(Clean Architecture)**를 적용했던 경험에서 출발했습니다.
당시 계층 분리와 아키텍처적 이점을 얻을 수 있었지만,
보일러플레이트 코드가 많아져 생산성이 일시적으로 저하되는 듯한 한계를 느꼈습니다.

이를 보완하기 위해 **Kotlin**을 활용한 헥사고날 아키텍처를 시도했습니다.  
Kotlin의 간결한 문법(data class, null-safety, 확장 함수 등)은  
보일러플레이트를 줄이고 가독성과 생산성을 높여줄 것이라 판단했고, 실제로 그런 효과를 얻을 수 있었습니다.  

해당 프로젝트는 **헥사고날 아키텍처 + Kotlin** 학습 과정의 일부를 샘플로 올린 것입니다.

---

## 🚀 프로젝트 목표

- 도메인 계층을 외부 의존성(JPA, Web, DB 등)으로부터 분리
- `Port`와 `Adapter` 개념을 활용한 유연한 구조 설계
- 클린 아키텍처 원칙을 따른 계층 분리 및 의존성 역전
- Kotlin + Spring Boot 기반의 간결하고 안전한 코드 작성

---

## ⚙️ 기술 스택

- **Language**: Kotlin 1.9.24
- **Framework**: Spring Boot 3.3.6
- **Architecture**: Hexagonal Architecture, Multi-Module
- **ORM**: JPA
- **Database**: H2
- **Build Tool**: Gradle (Kotlin DSL)
- **Test**: JUnit5, MockK

---

## 📂 모듈 구조
```
book-management-hexagonal-kotlin
 ├─ domain          <-- 순수 도메인 엔티티, 비즈니스 규칙
 ├─ application     <-- 비즈니스 수행, Port 인터페이스 정의
 ├─ adapter-inbound      <-- API (Controller, DTO) → Application 호출
 ├─ adapter-outbound     <-- DB/외부API,Event → Application Port 구현
 └─ bootstrap       <-- Spring Boot 실행
```
