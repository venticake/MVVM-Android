## MVVM-Android

### 요점
- view의 이벤트 -> controller(activity, fragment)가 받은 뒤 -> viewModel 값 수정 또는 메소드 실행
- viewModel의 상태 변화
  - -> controller(activity, fragment)가 observing 하다가 -> UI 업데이트
  - -> view xml에서 data binding으로 바로 업데이트

// TODO: 샘플 코드 일부 수정해야함 (view 이벤트를 viewModel이 아닌 controller가 직접 받기)

---

3-layer (UI + domain + data)구조에 MVVM을 추가하여 설계된 Android 샘플 프로젝트입니다.

간단한 Todo-list App입니다. 좌하단 버튼을 통해 Todo를 추가, 우하단 버튼을 통해 Todo를 초기화 할 수 있습니다.



UI : Todo list와 Todo card로 구성되어 있습니다.

Domain : Todo와 관련된 useCase와 model을 담고 있습니다.

Data : Realm을 이용하여 Todo에 대한 정보를 담고 있습니다. (내용, 추가 시간, 완료 여부)

* 참조한 문서

  * __

  * __

* 개발자 문서
  * __
  * __
