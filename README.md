Входные файлы и дамб бд находятся в src\main\resources.

Для запуска программы требуется использовать дамп или бд с следующими параметрами:
host = "127.0.0.1"; // сервер базы данных
port = 5432; // порт СУБД
databaseName = "TEST_database"; // база данных
username = "postgres"; // учетная запись пользователя
password = "123123"; // пароль пользователя

Сборка/запуск:
1) в дириктории проекта или в терминале IDE ввести "mvc install"
2) для запуска "java -jar java_jun_dev_test-1.0-SNAPSHOT-jar-with-dependencies.jar command inputPath outputPath"
где command - команда для поиска(search) или статистики(stat),
inputPath - путь до входного json файла, 
outputPath - путь для вывода результата в json файла.