# Тестовое задание от Уно-Софт

## Задание
[Задание расположено здесь](https://github.com/PeacockTeam/new-job/blob/faebaf3e40bd8fd45046c426aeab95d07157664a/lng-java.md)

## Требования
- Java 11 или новее
- Gradle 7.0+

## Сборка
```bash
./gradlew jar
```

## Запуск
```bash
java -jar {название проекта}.jar {Полный путь к входному файлу}
```
Результат сохраняется в файл `output.txt`

## Характеристика решения
В результате вычисления по входным данным были получены `1910` групп с более чем одной строкой.
Был проведен замер времени с помощью
```bash
time java -Xmx1G -jar test-task-unosoft-1.0-SNAPSHOT.jar lng.txt
```
который показал
```text
real    0m12.216s
user    0m11.420s
sys     0m0.687s
```
