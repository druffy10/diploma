Шаги для запуска тестов:
1. Создаем Image для Docker на основе файлов симулятора с помощью  [Dockerfile](https://github.com/druffy10/diploma/blob/23b5cf5457adf111edde495d0c3ca5e47769bc65/Dockerfile)
2. Создаем контейнеры с помощью [Docker Compose](https://github.com/druffy10/diploma/blob/23b5cf5457adf111edde495d0c3ca5e47769bc65/docker-compose.yml)
3. Запускаем [aqa-shop.jar](https://github.com/druffy10/diploma/blob/cc13876a86ae8cdc477e9dcd8d6ca49d1c74054a/artifacts/aqa-shop.jar)
4. Запускаем тесты из файла [PaymentTest](https://github.com/druffy10/diploma/blob/cc13876a86ae8cdc477e9dcd8d6ca49d1c74054a/src/test/java/test/PaymentTest.java)
5. Формируем репорт от Allure с помощью команды `./gradlew allureServe`

P.S.: В ветке _main_ все настроено для работы под _MySQL_, в ветке _postgre_ для работы под _PostreSQL_.