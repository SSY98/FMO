# 식단관리사무소(FMO)

## 실무 프로젝트 작품

**<a href="https://youtu.be/PlgFz66n1YE"> 시연 영상(Youtube영상) </a>**</br>

## 팀 소개

- **팀명 : 황의조**
- **심승열 : <a href="https://github.com/SSY98">github</a>**
- **신상훈 : <a href="https://github.com/ssh7937">github</a>**
- **유성진 : <a href="https://github.com/You-Seongjin">github</a>**
- **김현성**

## 개발기간

**2021/10 ~ 2021/12**

## 작품개요

식단관리를 자기주도적으로못하는 사람에게  도움이 되기 위해 만든 어플인 식단관리 어플

## 사용기술

- Kotlin
- MPAndroidChart
	- 식단 변화 그래프 출력

## 사용방법

1. 신체 정보를 입력하여 DB에 저장시킨다.</br>
**신체 정보 수정** : 사용자의 신체 정보가 DB에 저장된다.</br>
**식단 정보 입력** : 식단 정보를 입력하면 API를 활용하여 입력한 식단의 정보가 DB에 저장된다.</br>
**식단 변화 그래프 출력** : DB에 저장된 식단정보를 활용하여 그래프를 출력한다.</br>
2. DB에 저장된 신체 정보를 기반으로 식단을 관리해준다.
3. DB에 저장된 식단 정보를 기반으로 식단을 관리해준다.
4. 알람 설정을 해놓으면 사용자가 식단 정보를 까먹지 않고 입력할 수 있게 도와준다.
5. 그 외에도 식단 관리 TIP정보, 설정 기능을 사용할 수 있다.

## 핵심기능

1. Sharedpreferences를 활용한 신체 정보 입력/수정
2. 공공 데이터 포털을 이용한 API 활용한(식재료 정보) 식단 정보 관리
3. MPAndroidChart 그래프 출력 라이브러리를 활용한 식단 정보 출력
4. Room 라이브러리를 이용한 식단 정보 DB
5. AlarmManager을 활용한 식단 입력 알람 기능
