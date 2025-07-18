Сейчас Filmorate хранит все данные в своей памяти. Это приводит к тому, что при перезапуске приложения его история и настройки сбрасываются. Вряд ли это обрадует пользователей!
## Итак, нам нужно, чтобы данные:
- были доступны всегда,
- находились в актуальном состоянии.

А ещё важно, чтобы пользователи могли получать их быстро. Для этого вся информация должна храниться в базе данных.

В этом задании вы будете проектировать базу данных для проекта, основываясь на уже существующей функциональности. Вносить какие-либо изменения в код не потребуется.

## Изучение теории
💡 Прочтите следующие статьи, чтобы узнать, как проектировать базы данных:
- [«Нормализация баз данных простыми словами»](https://info-comp.ru/database-normalization)
- [«Ненормализованная форма или нулевая нормальная форма (UNF) базы данных»](https://info-comp.ru/zero-normal-form)
- [«Первая нормальная форма (1NF) базы данных»](https://info-comp.ru/first-normal-form)
- [«Вторая нормальная форма (2NF) базы данных»](https://info-comp.ru/second-normal-form)
- [«Третья нормальная форма (3NF) базы данных»](https://info-comp.ru/third-normal-form)

Это поможет набрать словарь технических терминов, который пригодится вам в будущем для чтения документации.

## Доработка модели

Прежде чем приступить к созданию схемы базы данных, нужно доработать модель приложения. Сейчас сущности, с которыми работает Filmorate, имеют недостаточно полей, чтобы получилось создать полноценную базу. Исправим это!

### Film
Добавьте новое свойство — «жанр». У фильма может быть сразу несколько жанров, а у поля — несколько значений. Например, таких:
- Комедия.
- Драма.
- Мультфильм.
- Триллер.
- Документальный.
- Боевик.

Ещё одно свойство — рейтинг Ассоциации кинокомпаний (англ. Motion Picture Association, сокращённо МРА). Эта оценка определяет возрастное ограничение для фильма. Значения могут быть следующими:
- G — у фильма нет возрастных ограничений,
- PG — детям рекомендуется смотреть фильм с родителями,
- PG-13 — детям до 13 лет просмотр не желателен,
- R — лицам до 17 лет просматривать фильм можно только в присутствии взрослого,
- NC-17 — лицам до 18 лет просмотр запрещён.

### User
Добавьте статус для связи «дружба» между двумя пользователями:
- неподтверждённая — когда один пользователь отправил запрос на добавление другого пользователя в друзья,
- подтверждённая — когда второй пользователь согласился на добавление.

## Создание схемы базы данных
Начните с таблиц для хранения пользователей и фильмов. При проектировании помните о том, что:
- Каждый столбец таблицы должен содержать только одно значение. Хранить массивы значений или вложенные записи в столбцах нельзя.
- Все неключевые атрибуты должны однозначно определяться ключом.
- Все неключевые атрибуты должны зависеть только от первичного ключа, а не от других неключевых атрибутов.
- База данных должна поддерживать бизнес-логику, предусмотренную в приложении. Подумайте о том, как будет происходить получение всех фильмов, пользователей. А как — топ N наиболее популярных фильмов. Или список общих друзей с другим пользователем.

Теперь нарисуйте схему базы данных. Для этого можно использовать любой из следующих инструментов:
- [dbdiagram.io](https://dbdiagram.io/d)
- [QuickDBD](https://app.quickdatabasediagrams.com/#/)
- [Miro](https://miro.com/ru/)
- [Lucidchart](https://www.lucidchart.com/pages/examples/database-design-tool)
- [Diagrams.net](https://app.diagrams.net/)

## Последние штрихи

Прежде чем отправлять получившуюся схему на проверку:

Скачайте диаграмму в виде картинки и добавьте в репозиторий. Убедитесь, что на изображении чётко виден текст.

Добавьте в файл README_ER.md ссылку на файл диаграммы. Если использовать разметку markdown, то схему будет видно непосредственно в README_ER.md.

Там же напишите небольшое пояснение к схеме: приложите примеры запросов для основных операций вашего приложения.
## Подсказка

💡 Документы по разметке, которая поддерживается GitHub, лежат [здесь](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#images).

Теперь можно отправлять схему на проверку