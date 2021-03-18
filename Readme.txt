для начала работы приложения:
на странице http://localhost:8189/api/v1/start
заполнить поля либо ни будут по умолчанию заполнены 1
либо
GET: http://localhost:8189/api/v1/start/run?th={int}&tdr={int}&otdr={int}&numart={int}
th - Количество потоков
tdr - Общее количество записей
otdr - Количество записей за один цикл
numart - Количество записей с одного сайта на сохраннее
любой не заполненный параметр или меньше 1 приравнивается к 1
GET: http://localhost:8189/api/v1/start/badword?word={}  --Добавить слово в черный список

на выдачу api:
GET: http://localhost:8189/api/v1/request
http://localhost:8189/api/v1/request/all -- возвращает все
http://localhost:8189/api/v1/request/id/{} -- возвращает по id
http://localhost:8189/api/v1/request/newssite/{} -- возвращает по имени новостного сайта
