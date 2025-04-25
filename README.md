### My tests and linter status:
[![my-check](https://github.com/sergeycherkasovv/java-project-72/actions/workflows/blank.yml/badge.svg)](https://github.com/sergeycherkasovv/java-project-72/actions/workflows/blank.yml)

### SonarQube
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_java-project-72&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_java-project-72)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_java-project-72&metric=bugs)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_java-project-72)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_java-project-72&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_java-project-72)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_java-project-72&metric=coverage)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_java-project-72)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_java-project-72&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_java-project-72)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_java-project-72&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_java-project-72)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_java-project-72&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_java-project-72)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_java-project-72&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_java-project-72)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_java-project-72&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_java-project-72)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_java-project-72&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_java-project-72)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=sergeycherkasovv_java-project-72&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=sergeycherkasovv_java-project-72)

**[Page Analyzer](https://java-project-72-27yt.onrender.com)** — полноценное веб-приложение, реализованное на фреймворке **Javalin** по архитектуре **MVC**. Основная задача — дать практический опыт в создании современных серверных приложений: от роутинга и работы с HTTP до интеграции с базой данных и деплоя в продакшен.

Приложение позволяет:

- Добавлять сайты и сохранять их в базу данных.
- Выполнять проверки сайтов: извлекать статус-код, `<title>`, `<h1>` и описание (`<meta name="description">`).
- Отображать информацию на страницах с помощью шаблонизатора **JTE** и CSS-фреймворка **Bootstrap**.

## 🚀 Технологии

- Java 17+  
- [Javalin](https://javalin.io) (v6+)  
- [HikariCP](https://github.com/brettwooldridge/HikariCP)  
- [H2 Database](https://www.h2database.com)  
- PostgreSQL  
- [JTE](https://jte.gg) — шаблонизатор  
- [Unirest](https://kong.github.io/unirest-java/) — HTTP-клиент  
- [Jsoup](https://jsoup.org) — HTML-парсер  
- [SLF4J Simple](https://www.slf4j.org/) — логирование  
- [JUnit 5](https://junit.org/junit5/), 
  [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) — тестирование  
- Gradle, GitHub Actions, Codecov — CI/CD и покрытие
### Hexlet tests and linter status:
[![Actions Status](https://github.com/sergeycherkasovv/java-project-72/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/sergeycherkasovv/java-project-72/actions)

## 🔧 Установка и запуск локально
1. Клонировать репозиторий:  
   ```bash
   git clone https://github.com/sergeycherkasovv/java-project-72.git
   cd java-project-72
    ```
2. Настроить переменные окружения (по умолчанию используется H2 in-memory):
    ```bash
    # src/main/java/hexlet/code/util/DataBaseService.java  
     JDBC_H2_URL="jdbc:h2:mem:project;DB_CLOSE_DELAY=-1" 
    ```    
3. Запустить приложение:
    ```bash
    make start
   ```
4. Запуск всех тестов:
    ``` bash
    make test
   ```
## 🗂 Структура проекта
    ```bash
    src/
    ├── main/
    │   ├── java/hexlet/code/
    │   │   ├── App.java               # Точка входа и конфигурация Javalin
    │   │   ├── controller/            # HTTP контроллеры
    │   │   ├── model/                 # Сущности Url, UrlCheck
    │   │   ├── repository/            # Репозитории для работы с БД
    │   │   ├── dto/                   # DTO для шаблонов
    │   │   ├── util/                  # Утилиты (DataBaseService, ReadFiles, UrlUtils)
    │   └── resources/
    │       ├── templates/             # JTE-шаблоны
    │       ├── schema.sql             # Скрипт создания таблиц
    ├── test/                          # Юнит и интеграционные тесты c MockWebServer
    ├── build.gradle.kts              # Сборка Gradle
    └── README.md                     # Документация
    ```

## ☁️ Деплой
👉 https://your-app.onrender.com
