# job4j_cinema

Проект "job4j_cinema" является упрощенной версией Кинотеатра по ТЗ курса Job4j.
Исходными данными являются схемы таблиц БД. Панель администратора реализовывать не нужно.

Функционал проекта позволяет:
* регистрировать пользователя, наделять его правами;
* выводить расписание фильмов, одного фильма и покупать билет на сеанс с выбором из выпадающего списка ряда и места (при этом гарантируется уникальность места);
* покупка билета доступна только зарегистрированным пользователям (реализованы фильтры);
* выводить кинотеку (список фильмов с указанием жанра);
* хранить данные пользователей и билетов в БД.

### Стек технологий :technologist:.
Основные :man_technologist:: 
- Java 17
- Spring Boot 2.7.6
- Thymeleaf
- Bootstrap CSS
- Liquibase 4.15.0
- Sql2o 1.6.0
- PostgreSQL 15.1 (драйвер JDBC 42.5.1)
- checkstyle 10.0.

Тестирование :mechanic::
- H2database 2.1.214
- Jacoco 0.8.8
- Spring boot starter test (JUnit 5 + AssertJ, Mockito).

### Требования к окружению :black_circle:.
- Java 17
- Maven 3.8
- PostgreSQL 15.

### Запуск проекта :running:.
```Скачать проект job4j_cinema в IntelliJ Idea```

```Создать БД "cinema" (с помощью pgAdmin4)```

```Cоздайте и заполните таблицы БД  "cinema". Откройте закладку Maven -> plugins -> liquibase. Найдите задачу liquibase:update и выполните ее.```

```Запустите приложение в классе Main (ru/job4j/cinema/Main.java)```

```Откройте страницу http://localhost:8080/ в браузере```

### Screenshots работы с приложением Кинотеатр :cinema:.

- [x] Главная страница

  ![](https://raw.githubusercontent.com/PavelValger/job4j_cinema/master/img/start.jpg)

- [x] Расписание

  ![](https://raw.githubusercontent.com/PavelValger/job4j_cinema/master/img/seans.jpg)

- [x] Кинотека

  ![](https://raw.githubusercontent.com/PavelValger/job4j_cinema/master/img/films.jpg)

- [x] Страница регистрации

  ![](https://raw.githubusercontent.com/PavelValger/job4j_cinema/master/img/registr.jpg)

- [x] Страница регистрации - ошибка дублирования пользователя

  ![](https://raw.githubusercontent.com/PavelValger/job4j_cinema/master/img/errorRegistr.jpg)

- [x] Страница вход

  ![](https://raw.githubusercontent.com/PavelValger/job4j_cinema/master/img/identi.jpg)

- [x] Страница покупки билета

  ![](https://raw.githubusercontent.com/PavelValger/job4j_cinema/master/img/pagebuy.jpg)

- [x] Страница с результатом успешной покупки билета

  ![](https://raw.githubusercontent.com/PavelValger/job4j_cinema/master/img/coolbuy.jpg)

- [x] Страница с результатом неудачной покупки билета (билет уже купили)

  ![](https://raw.githubusercontent.com/PavelValger/job4j_cinema/master/img/conflict.jpg)


#### Контакты для связи :iphone::
* Вальгер Павел Иванович;
* +79920045094 telegram, whatsapp;
* pavelwalker@mail.ru.