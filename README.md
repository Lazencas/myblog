# myblog
JavaSpring을 이용하여 만든 내 블로그 백엔드 서버
![usecase.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8910d69a-ab5d-4380-a0b3-75c15e54e159/usecase.jpg)


1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
@PathVariable, @RequestBody를 사용하여 받았다.
2. 어떤 상황에 어떤 방식의 request를 써야하나요?
바디에 데이터가 들어왔을 때 > @RequestParam 처리
3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
대문자는 때로 문제를 일으키는 경우가 있기 때문에 URI를 작성할 때는 소문자를 선호한다 잘지킴
인터페이스 일관성 : 일관적인 인터페이스로 분리되어야 한다. mvc로 잘 나누어져 있기 때문에 잘 지킨거같다.
캐시 처리 기능 : 클라이언트는 응답을 캐싱할수 있어아한다. 캐시를 통해 대량의 요청을 효율적으로 처리한다. > 처리안됨
URI에 작성되는 영어를 복수형으로 작성한다. 잘 안지켜짐 > post를 posts로 바꿔야할듯
4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
잘 사용하였다.
5. API 명세서 작성 가이드라인을 검색하여 직접 작성한 API 명세서와 비교해보세요!
