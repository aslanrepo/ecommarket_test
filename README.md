# Тестовое задание:
- Написать spring-boot приложение, которое будет содержать 1 контроллер с одним методом, который возвращает HTTP 200 и пустое тело.
- Написать функционал, который будет ограничивать количество запросов с одного IP адреса на этот метод в размере N штук в X минут. Если количество запросов больше, то должен возвращаться 502 код ошибки, до тех пор, пока количество обращений за заданный интервал не станет ниже N. 

_Должна быть возможность настройки этих двух параметров через конфигурационный файл._ 

- Сделать так, чтобы это ограничение можно было применять быстро к новым методам и не только к контроллерам, а также к методам классов сервисного слоя. 

Реализация должна учитывать многопоточную высоконагруженную среду исполнения и потреблять как можно меньше ресурсов (важно!). 
- Также написать простой JUnit-тест, который будет эмулировать работу параллельных запросов с разных IP. 

!!! Не использовать сторонних библиотек для троттлинга. 

Список технологий и инструментов: 
Код должен быть описан на Java 11 (или выше) 
Фреймворки: Spring + Spring Boot 
Для сборки использовать Gradle. Возможны другие вспомогательные библиотеки. 

- Написать JUnit тест с использованием JUnit 5.x (Junit Jupiter)
- Написать простой dockerfile для обёртки данного приложения в докер

# Решение:
- [x] Написать JUnit тест с использованием JUnit 5.x (Junit Jupiter)
  - `ton.telegrambots.ecommarket.testproject.service.RequestLimitCheckServiceImplTest`
- [x] Написать функционал, который будет ограничивать количество запросов с одного IP адреса на этот метод в размере N штук в X минут. Если количество запросов больше, то должен возвращаться 502 код ошибки, до тех пор, пока количество обращений за заданный интервал не станет ниже N. Должна быть возможность настройки этих двух параметров через конфигурационный файл.
  - `ton.telegrambots.ecommarket.testproject.service.ThrottlingService`
- [x] Также написать простой JUnit-тест, который будет эмулировать работу параллельных запросов с разных IP. 
  - `ton.telegrambots.ecommarket.testproject.service.RequestLimitCheckServiceImplConcurrentTest`
- [x] !!! Не использовать сторонних библиотек для троттлинга.
- [x] Сделать так, чтобы это ограничение можно было применять быстро к новым методам и не только к контроллерам, а также к методам классов сервисного слоя.
  - `ton.telegrambots.ecommarket.testproject.aspect.ThrottlingAspect`
  - `ton.telegrambots.ecommarket.testproject.aspect.Throttled`
- [x] Список технологий и инструментов: Код должен быть описан на Java 11 (или выше) Фреймворки: Spring + Spring Boot Для сборки использовать Gradle. Возможны другие вспомогательные библиотеки.
- [x] Написать простой dockerfile для обёртки данного приложения в докер

## TODO
- [ ] Количество проверок на ограничения умножается на количество аннотированных `@Throttled` методов в цепочке вызовов.
- [ ] Очистка неактуальных записей из таблицы ip на частоту.