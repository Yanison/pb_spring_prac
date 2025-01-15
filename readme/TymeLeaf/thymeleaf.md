# Thymeleaf 단기속성 문법

## Java 진영 템플릿 엔진의 역사

### JSP(Java Server Pages) - 1999년 출시
- Java Servlet 기반의 웹 개발을 단순화하기 위해 등장
- HTML 내부에 Java 코드와 JSTL(JSP Standard Tag Library)을 사용하여 동적인 뷰를 구성 가능함
- JSP의 단점
    - HTML과 Java 코드가 섞여 있어 가독성이 떨어짐 즉 유지보수가 어려워짐 ( 예전엔 JSP에 비즈니스 로직이 섞여있는 경우가 많았음 그러나 요즘은 JSTL만 사용함)
### Vekocity - 2001년 출시
- JSP의 복잡성을 보완하기 위해 등장, MVC 패턴에서 뷰 역할에 집중하기 위해 설계됨
- 간단한 문법과 유연성을 제공하지만 복잡한 요구사항에는 한계가 존재했음
- HTML 템플릿이 정적 파일로서 가독성이 부족
### Apache FreeMarker - 2002년 출시
- Velocity의 단점을 보완하기 위해 등장, JSP 대안으로 많은 사용자층을 확보했었음
- 다양한 기능을 지원하지만 무겁다는 단점이 있다.

### Thymeleaf - 2011년 출시
- Java 진영에서 HTML 파일의 가독성과 유지보수를 개선하기 위해 개발 "자연스러운 HTML(Natural Templetes)"이라는 개념 도입
- Spirg Boot에서 강력한 통합 지원(Spring EL 사용)으로 인해 많은 사용자층을 확보하고 있음 (Spring에서 공식적으로 권장하는 템플릿 엔진)
- HTML 파일을 그대로 사용하면서도 동적인 데이터를 쉽게 삽입할 수 있음 (JSP는 html 파일을 `/jsp`로 변경해야함)
- 가독성이 높은 코드로 디자이너와 개발자가 동일한 템플릿에서 작업 가능

그러나 Spring 진영에서 공식적으로 지원해준다는 장점 외에는 느리다는 장점이 있다..

### 성능비교
Java-Templete 성능비교
- [국내 블로그글](https://jangcool.tistory.com/entry/Java-Template-%EC%84%B1%EB%8A%A5-%EB%B9%84%EA%B5%90)
- [Modern Type-Safe Template Engines (Part 2)](https://dzone.com/articles/modern-type-safe-template-engines-part-2)
- [Java: Benchmark Thymeleaf 2.1.4 vs Thymeleaf 3.0 SNAPSHOT](https://smarterco.de/java-benchmark-thymeleaf-2.1.4-vs-thymeleaf-3.0-snapshot/)

**요약 : Freemarker > Velocity > JSP > Thymeleaf**

---

## Thyemleaf란?

```text
공식 문서 소개

Thymeleaf는 웹 및 독립 실행형 환경에서 모두 사용할 수 있는 현대적인 서버사이드 Java 템플릿 엔진으로, 
HTML, XML, JavaScript, CSS, 심지어 일반 텍스트까지 처리할 수 있습니다.

Thymeleaf의 주요 목표는 우아하고 유지보수 가능한 방식으로 템플릿을 생성하는 것입니다. 
이를 위해, 템플릿 파일에 로직을 주입하면서도 디자인 프로토타입으로 사용할 수 있도록 하는 
Natural Templates(자연스러운 템플릿) 개념을 기반으로 설계되었습니다. 
이로 인해 디자인과 개발 팀 간의 의사소통이 개선되고, 양측 간의 격차가 줄어듭니다.

또한, Thymeleaf는 처음부터 **웹 표준(특히 HTML5)**을 염두에 두고 설계되었으며, 
필요에 따라 완전히 유효한 템플릿을 생성할 수 있는 기능을 제공합니다.
```

Thymeleaf는 Java기반 템플릿 엔진입니다. MVC 기반 웹 애플리케이션의 뷰 레이어에서 XHTML/HTML5를 제공하는데 적합합니다.
JSP와 달리 브라우저에서 바로 확인 가능한 자연스러운 HTML을 제공하며, JSP와 같은 뷰 템플릿 엔진에 유지보수가 쉽다는 장점이 있습니다.


```html
- JSP 렌더링 방식 (브라우저에서 미리보기 불가)
<form:inputText name="userName" value="${user.name}" />
- Thymeleaf 렌더링 방식 (브라우저에서 미리보기 가능)
<input type="text" name="userName" value="James Carrot" th:value="${user.name}" />
```

본문은 Thymeleaf를 빠르게 슥듭하기위한 요약본이니 자세한 내용은 [Thymeleaf 공식문서] 를 참고하세요.
- https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html#expression-basic-objects

## 목차
0. 기본 사용법
1. 서버에서 받은 값을 element에 입력할때
2. 반복문에 따라 렌더링할때
3. 조건문에 따라 렌더링할때
4. fragment를 사용하여 레이아웃 구성하기

### 0. 기본 사용법

- thymeleaf의 문법을 사용하기 위해서는 html파일을 생성하고 파일 상단에 아래 두 구문을 선언해야 한다.
```html
<!-- thymeleaf의 문법을 사용하기 위해서는 html파일을 생성하고 파일 상단에 아래 두 구문을 선언해야 한다.-->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<!-- -------------------------------------------------------- -->
```

- 그 다음 thymelead에서 제공하는 기능들을 사용하려면 element의 tag안에 th:`속성`을 입력해야 한다..
```html
<head>
    <title>Layout</title>
</head>
<body>
<div>그 다음 thymeleaf의 문법을 사용하기 위해서는 element의 tag안에 ``</div>
<div th:text="${서버에서 model.addAttribute로 받은 key 입력 혹은 thymeleaf 구문}"></div>
</body>
```

#### 변수 표현식

1. `${...}` : 컨텍스트 변수에 접근하기 위한 표현식
  - 서버에서 전달된 `Model`이나 `Context`에 담긴 데이터를 참조
  - `{...}`는 현재 객체 기준으로 상대경로 접근

ex)
```html
<!-- 컨트롤러에서 model.addAttribute("user", user)를 전달했을 경우 -->
<div th:text="${user.name}">User Name</div>
<!-- 현재 객체가 user로 설정된 경우 -->
<div th:object="${user}">
  <p th:text="*{name}">User Name</p>
</div>
```

<br><br><br>

2. `#{...}` : 메시지 표현식
  - `application.properties`에 정의된 메시지를 참조

ex)
```properties
#application.properties에 정의된 메시지를 참조
home.welcome=¡Bienvenido a nuestra tienda de comestibles!
```
```html
<div th:text="#{home.welcome}">Welcome to our grocery store!</div>
<!-- 결과 -->
<div>¡Bienvenido a nuestra tienda de comestibles!</div>
```
<br><br><br>

3. `*{...}` : 선택 변수 표현식
```html
<div th:object="${session.user}"> <!-- 부모 객체에서 session.user를 선택객체로 참조 -->
  <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p> <!-- session.user.firstName을 참조 -->
  <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p> <!-- session.user.lastName을 참조 -->
  <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p> <!-- session.user.nationality을 참조 -->
</div>
```
선택 객체가 없을경우 `*{...}`는 `${...}`와 동일하게 동작합니다.

<br><br><br>

4. `@{...}` : Link URLs 표현식

a태그에서 href속성을 동석으로 설정할때 사용합니다. `@{...}` 내부에 변수 접근식을 사용할 수 있습니다.
```html
<!-- 서버에서 컨트롤러에서 @GetMapping("/subject/uuid/{uuid}") 라고 정의했을때 a태그로 접근하는 방법 예시-->
<a th:href="@{/subject/uuid/{uuid}(uuid=${subject.uuid})">Subject</a>

<!-- 결과 -->
<a href="/subject/uuid/1234">Subject</a>
```

---
## 속전속결 주요사용법

### 1. 서버에서 받은 값을 element에 입력할때
서버에서 다음처럼 값을 받았다고 가정합니다.
```java
@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final String FRAGMENTS_PATH = "fragments/index";
    private final String FRAGMENT = "index";

    @GetMapping("/layout")
    public String layout(Model model) {
        model.addAttribute("fragPath",FRAGMENTS_PATH);
        model.addAttribute("fragment",FRAGMENT);
        return "layout-after";
    }
}
```

서버에서 model에 담아서 view로 보낸 값을을 받는 방법은 아래와 같다.
```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Layout</title>
</head>
<body>
    <div>fragPath</div>
    <div th:text="${fragPath}"></div> <!-- <div>fragments/index</div> -->
    <div>fragment</div>
    <div th:text="${fragment}"></div> <!-- <div>index</div> -->
</body>
```

위 처럼 서버에서 전달받은 값을 element에 입력할때는 `th:text`를 사용하지만 class나 id등 다른 속성에도 사용가능합니다.
```html
<!-- id 속성-->
<div th:id="${fragment}"></div> <!-- <div id="value"></div> -->
<!-- class 속성-->
<div th:class="${fragment}"></div> <!-- <div class="value"></div> -->
<div class="container" th:classappend="${fragment}"></div> <!-- <div class="container value"></div> -->
<!-- attr -->
<div th:attr="data-id=${fragment}"></div> <!-- <div data-id="value"></div> -->
<div th:attrappend="data-id=${fragment}"></div> <!-- <div data-id="value"></div> -->
```
<div 
```

---
### 2. 반복문에 따라 렌더링할때
서버에서 배열 데이터를 받았을때 배열의 길이만큼 동적으로 렌더링 하고 싶을때 다음과 같이 사용합니다.
```java
@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/layout")
    public String layout(Model model) {
        List<Objcet> list = service.findAll();
        model.addAttribute("list",list);
        return "layout-after";
    }
}
```

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Layout</title>
</head>
<body>
<div>list</div>
<div>
    <ul>
        <li th:each="item : ${list}">
            <span th:text="${item}"></span>
        </li>
    </ul>
    <!-- result 
    <ul>
        <li><span>item1</span></li>
        <li><span>item2</span></li>
        <li><span>item3</span></li>
    </ul> 
    -->           
</div>
</body>

```
---

### 3. 조건문에 따라 렌더링할때

#### thymeleaf에서의 비교 연산자는 아래처럼 사용가능합니다.
1. 비교 연산자 (Comparators)
- **숫자 비교**:
    - `>` : 크다 (`gt`로 대체 가능)
    - `<` : 작다 (`lt`로 대체 가능)
    - `>=` : 크거나 같다 (`ge`로 대체 가능)
    - `<=` : 작거나 같다 (`le`로 대체 가능)

2. 동등성 연산자 (Equality Operators)
- `==` : 같다 (`eq`로 대체 가능)
- `!=` : 같지 않다 (`neq` 또는 `ne`로 대체 가능)

<br><br><br>
서버에서 받은 데이터를 조건에 따라 렌더링 하고 싶을때 다음과 같이 사용합니다.
```java
@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/layout")
    public String layout(Model model) {
        List<Objcet> list = service.findAll();
        model.addAttribute("list",list);
        return "layout-after";
    }
}
```

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Layout</title>
</head>
<body>
<div>list</div>
<div>
    <ul>
        <li th:each="item : ${list}">
            <!-- th:text 구분에서 삼항연산자 사용 가능 -->
            <span th:text="${item} ? 'true' : 'false'"></span>
            <!-- JSP처럼 choose, otherwise 와 같은 if-else문의 형태는 제공되지 않고 아래처럼 조건문 생성 -->
            <span th:if="${item eq 'value1'}">true</span> <!-- th:if 조건이 참일경우 렌더링 -->
            <span th:unless="${item eq 'value1'}">false</span> <!-- th:unless 조건이 거짓일경우 렌더링 -->
            <!-- th:switch 조건에 따라 렌더링 -->
            <span th:switch="${item}">
                <span th:case="'value1'">value1</span> <!-- item이 value1 일 경우 렌더링 -->
                <span th:case="'value2'">value2</span> <!-- item이 value2 일 경우 렌더링 -->
                <span th:case="*">default</span> <!-- item이 어느곳도 해당하지 않을경우 기본값 렌더링 -->
            </span>
        </li>
    </ul>       
</div>
</body>

```


---

### 4. fragment를 사용하여 레이아웃 구성하기
JSP에서 `<%@ include file="...">`와 같은 기능을 하는 fragment를 사용하여 레이아웃을 구성할 수 있습니다.
JSP와 차이점은 JSP에서의 `include` 는 삽입하고자 하는 JSP 파일이 존재하는 위치에 바로 접근하여 삽입이 가능하지만
Thymeleaf에서는 `fragment`를 정의하고 `th:insert="~{path (param)}"`와 `th:replace="~{path (param)}"` 속성을 사용하여 삽입할 수 있습니다.

#### insert와 replace의 차이

- `th:insert` 단순하게 지정된 fragment를 host tag의 body로 삽입합니다. (host tag는 유지됨)
- `th:replace` host tag를 지정된 fragment로 대체합니다. (host tag는 제거됨)

*host tag는 fragment를 호출하는 태그

```html
<!-- frag.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<th:block th:fragment="fragment-insert">
    I am a peace of fragment for insert
</th:block>
<th:block th:fragment="fragment-replace">
  I am a peace of fragment for replace
</th:block>

```

```html
<!-- layout.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Layout</title>
</head>
<body>
    <div th:insert="~{frag.html :: fragment-insert}">
    </div>
    <div th:replace="~{frag.html :: fragment-replace}"></div>

<!-- 결과 -->
<!-- insert -->
<div>
    I am a peace of fragment for insert
</div>
<!-- replace -->
I am a peace of fragment for replace
</body>
```




### thymeleaf의 주요 속성 정리

- thymeleaf에서 사용하는 주요 속성
    - `th:text` : 텍스트를 출력
    - `th:utext` : HTML을 출력
    - `th:if` : 조건문
    - `th:unless` : 조건문의 반대
    - `th:switch` : switch문
    - `th:case` : case문
    - `th:each` : 반복문
    - `th:object` : 객체를 지정
    - `th:with` : 변수를 지정
    - `th:attr` : 속성을 지정
    - `th:src` : 이미지의 src를 지정
    - `th:href` : 링크의 href를 지정
    - `th:action` : 폼의 action을 지정
    - `th:method` : 폼의 method를 지정
    - `th:field` : 폼의 필드를 지정
    - `th:value` : 폼의 값 지정
    - `th:checked` : 체크박스나 라디오 버튼의 체크 여부 지정
    - `th:disabled` : 폼의 비활성화 여부 지정
    - `th:readonly` : 폼의 읽기 전용 여부 지정
    - `th:style` : 스타일 지정
    - `th:class` : 클래스 지정
    - `th:inline` : 자바스크립트나 CSS를 인라인으로 지정
    - `th:remove` : 요소를 제거
    - `th:block` : 블록을 지정
    - `th:fragment` : 프래그먼트를 지정
    - `th:replace` : 요소를 대체
    - `th:insert` : 요소를 삽입
    - `th:append` : 요소를 추가
    - `th:prepend` : 요소를 앞에 추가


[Thymeleaf 공식문서]:(https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html#expression-basic-objects)
[Java-Templete 성능비교]:(https://jangcool.tistory.com/entry/Java-Template-%EC%84%B1%EB%8A%A5-%EB%B9%84%EA%B5%90)