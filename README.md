## Программа, работающая с SQLite БД которая организует работу с оборудованием на скважинах со следующими функциями:

### 1. Создание N кол-ва оборудования на скважине.
   При выборе этого пункта пользователь указывает кол-во оборудования и имя скважины.
   Программа создает указанное кол-во оборудования на скважине с указанным именем. При создании оборудования каждому
   присваивается свой id и свое уникальное имя (должно генерироваться программой с использованием латинских букв и
   цифр).
   Скважина, так же создается программой с указанным именем, если ее еще нет в таблице.
### 2. Вывод общей информации об оборудовании на скважинах.
   При выборе этого пункта пользователь указывает имена скважин, разделяя их пробелами или запятыми.
   Программа подсчитывает кол-во оборудования на каждой скважине и выдает на экран таблицу вида ИМЯ скважины - кол-во
   оборудования.
### 3. Экспорт всех данных в xml файл.
   При выборе этого пункта пользователь указывает имя файла.
   Программа выбирает все скважины из базы и записывает данные по ним и оборудовании в xml формате в виде (одна и та же
   скважина или оборудование не могут повторяться несколько раз):
   <dbinfo>
   <well name="АААА"  id="123">
   <equipment name=”EQ0033" id="12"/>
   <equipment name=”EQ0034" id="13"/>
   </well>
   <well name="BBBB"  id="124">
   <equipment name=”EQ0038" id="11"/>
   <equipment name=”EQ0039" id="14"/>
   </well>
   </dbinfo >
  
   ###   Дополнительная информация:
   Проект оформлен для сборки при помощи maven.
   Перечисленные пункты меню реализованы путем ввода в текстовом режиме в
   самой программе.
   Для доступа к БД использован sqllite-jdbc.
   
   Файл базы данных test.db располагается в каталоге запуска программы.
   Если файла еще нет, программа создает БД и все необходимые таблицы в ней.
   Программа упакована в zip архив с исходными кодами и jar файлом для запуска.
   Таблицы для БД:


Well - скважина
id - Уникальный идентификатор записи (первичный ключ). Должен генерироваться средствами БД
name - Varchar(32) - имя скважины, уникальное, не может повторяться и быть пустым.

Equipment – оборудование на скважине
id - Уникальный идентификатор записи (первичный ключ). Должен генерироваться средствами БД
name - Varchar(32) - имя оборудования, уникальное, не может повторяться и быть пустым.
Well_id - Ссылка на Well.id - id скважины, на которой установлено оборудование.


## Запуск программы
```
java -jar wells-1.0-SNAPSHOT-jar-with-dependencies.jar
```
