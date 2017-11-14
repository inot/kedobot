# KeDoBot
Бот для Discord, который присваивает и забирает у пользователей Роли сервера, взависимости от выбранных Эмодзи. 

Привет. Данный бот был написан для личных нужд и приведен к виду, для использования его не только на моем сервере. Я не программист, поэтому, просьба, на говнокод не показывать. Я знаю это и сам. Дальше будет не большая инструкция. 

# Установка
[dev]: <https://discordapp.com/developers/applications/me>
- На [сайте][dev] создается бот. От него понадобится Token(Не Secret) и Client ID.
- Бота нужно пригласить на сервер. По сслыке вида.  https://discordapp.com/oauth2/authorize?client_id=<CLIENT_ID>&scope=bot, где <CLIENT_ID> это Client ID ващего бота из ссылки выше. Его поидее должно быть видно в консоли.
- После приглашения бота, он должен быть ВЫШЕ в списке Ролей, чем те Роли, которыми он будет управлять.
- На сервере создается нужное количество Эмодзи. Сервер - Настройки сервера - Эмодзи.
- В любом канале создается сообщение приветствия и копируется ID этого сообщения. (его видно если включить режим разработчика в Discord)
- заполняем config.properties
```sh
token = Сюда пишется Token, тот что не Secret
owner = Сюда пишется длинная цифра, которую можно взять если скопировать ID кликнув в чате на себе.
messageID = Сюда пишется длинная цифра, которая берется из нужного нам сообщения приветствия.
emoji = Сюда нужно вписать через запятую ПАРЫ, представляющие собой название Эмодзи и название Роли. Выглядит вот так. thedivision:37960707346435#The Division. Это одна Пара до знака # идет название Эмодзи, после знака название Роли. Через запятую их может быть несколько. 
thedivision:37960707346435#The Division,overwatch:379898288528162826#Overwatch,destiny:379607763959021588#Destiny 2,counterstrike:379895715310075914#Counter Strike,pubg:379896330648027136#PUBG
debug = true Это включение и выключение дебаговой информации. При первом запуске надо сделать true, после можно сделать false.
```
- запускаем.
- Добавляем на наше сообщение, созданные Эмодзи. В консоли приложения будут появлятся полные названия Эмодзи. Переписываем их себе.
- Выключаем бота, возвращаемся в конфигурационный файл.
- Заполняем его целиком, полученными парами через запятую. 
```thedivision:37960707346435#The Division,overwatch:379898288528162826#Overwatch,destiny:379607763959021588#Destiny 2,counterstrike:379895715310075914#Counter Strike,pubg:379896330648027136#PUBG ```
- Если надо выключаем debug.
- Все. 

# Библиотеки
[d4j]: <https://github.com/austinv11/Discord4J>
- [Discord4j][d4j]

