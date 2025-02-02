### Серсис организации и оценки мероприятий.
Сервис, который полезен организаторам, т.к. в нем можно создавать задачи, которые можно распределить между организаторской
командой, и участникам, т.к. можно зарегистрироваться на какое-либо мероприятие.
Сервис состоит из 5 микросервисов (user_service, event_service, task_service, registration_service, review_service).
### Задачи

**Задача 1.** 
- Написать микросервис user_service.
- Миграции и docker.
- Юнит и интеграционные тесты.

Эндпоинты:

POST /users - создание пользователя 
PATCH /users - обновление пользователя (userId (кто делает запрос) берем из header и проверяем верность введенного пароля, нельзя обновить email)  
GET /users/{id} - вернуть пользователя по id (без пароля, НО если запрашивает сам себя (проверяем по header), то с паролем)  
GET /users?page={page}&size={size} - получение пользователей с пагинацией (вернуть без паролей)  
DELETE /users - удаление пользователя по id (userId (кто делает запрос) берем из header и проверяем верность введенного пароля)  

Модель User включает следующие поля: name, email, password, aboutMe

**Задача 2.** (user_service):
- В микросервисе настроен github ci так, чтобы при каждом создании ПР и при каждом пуше в ПР проходила сборка проекта,
прогон тестов и (задача со звездочкой) чекстайл и проверка покрытия кода тестами через jacoco (60%).
- Каждый из 5 микросервис развернут на сервере. К эндпоинтам этого микросервиса можно обратиться через Postman,
установленный на локальном компьютере, и получить успешный ответ.

**Задача 3.** (user_service).
- В микросервисы добавлен сваггер (в минимальном варианте - реализация по умолчанию)
- С локального компьютера можно открыть сваггер микросервисов, расположенных на сервере
- на сервере приложения были развернуты в докер-контейнерах, выполена настройка FeignClient таким образом, чтобы они видели друг друга и могли общаться.