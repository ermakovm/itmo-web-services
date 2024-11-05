# Технологии Web-сервисов

Лабораторные работы по дисциплине "Технологии веб-сервисов"

Статус:

- [x] Лабораторная 1
- [x] Лабораторная 2
- [ ] Лабораторная 3
- [ ] Лабораторная 4
- [ ] Лабораторная 5
- [ ] Лабораторная 6
- [ ] Лабораторная 7

## Лабораторная работа 1

### Поиск с помощью SOAP-сервиса

В данной работе требуется создать таблицу в базе данных,
содержащую не менее пяти полей,
а также реализовать возможность поиска по любым комбинациям полей с помощью SOAP-сервиса.
Данные для поиска должны передаваться в метод сервиса в качестве аргумента.

Веб-сервис необходимо реализовать в виде standalone-приложения и J2EE-приложения.
При реализации в виде J2EE-приложения следует на стороне сервера приложений настроить источник данных и осуществлять его
инъекцию в коде сервиса.

Для демонстрации работы разработанных сервисов следует также разработать и клиентское консольное приложение.

## Лабораторная работа 2

### Реализация CRUD с помощью SOAP-сервиса

В данной работе в веб-сервис, разработанный в первой работе, необходимо
добавить методы для создания, изменения и удаления записей из таблицы.

Метод создания должен принимать значения полей новой записи, метод
изменения – идентификатор изменяемой записи, а также новые значения полей, а
метод удаления – только идентификатор удаляемой записи.

Метод создания должен возвращать идентификатор новой записи, а методы
обновления или удаления – статус операции. В данной работе следует вносить
изменения только в standalone-реализацию сервиса.

В соответствии с изменениями сервиса необходимо обновить и клиентское
приложение.

## Лабораторная работа 3

### Обработка ошибок в SOAP-сервисе

Основываясь на информации из раздела 2.8, добавить поддержку обработки
ошибок в сервис. Возможные ошибки, которые могут происходить при
добавлении, изменении или удалении записей: неверное значение одного из полей;
попытка изменить или удалить несуществующую запись.

В соответствии с изменениями сервиса необходимо обновить и клиентское
приложение.

## Лабораторная работа 4

### Поиск с помощью REST-сервиса

Необходимо выполнить задание из первой работы, но с использованием
REST-сервиса. Таблицу базы данных, а также код для работы с ней можно оставить
без изменений.

## Лабораторная работа 5

### Реализация CRUD с помощью REST-сервиса

Необходимо выполнить задание из второй работы, но с использованием
REST-сервиса. Таблицу базы данных, а также код для работы с ней можно оставить
без изменений.

## Лабораторная работа 6

### Обработка ошибок в REST-сервисе

Необходимо выполнить задание из третьей работы, но с использованием
REST-сервиса. Таблицу базы данных, а также код для работы с ней можно оставить
без изменений.

## Лабораторная работа 7

### Регистрация и поиск сервиса в реестре jUDDI

Требуется разработать приложение, осуществляющее регистрацию сервиса
в реестре jUDDI, а также поиск сервиса в реестре и обращение к нему.
Рекомендуется реализовать консольное приложение, которое обрабатывает две
команды. Итог работы первой команды – регистрация сервиса в реестре; вторая
команда должна осуществлять поиск сервиса, а также обращение к нему.