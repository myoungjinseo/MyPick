# My pick
# Architecture
![image](https://github.com/user-attachments/assets/6b499c93-16aa-4ec1-ab8e-c57b5bb6b262)
# ERD 다이어그램
![image](https://github.com/user-attachments/assets/f20261d9-bd16-4b8e-97d1-48825a7266bf)

# Multi Module
- Multi Module 아키텍처는 여러 모듈로 분리하여 코드의 재사용성을 높이고, 종속성을 줄여주는 역할을 한다.
```
├── myPick-api
│   ├── src
│   └── build.gradle
├── myPick-batch
│   ├── src
│   └── build.gradle
├── myPick-common
│   ├── src
│   └── build.gradle
├── myPick-domain
│   ├── src
│   └── build.gradle
// ...
```
