# Maven dependency #

```
<dependency>
  <groupId>com.github.zjor.telegram</groupId>
  <artifactId>bot-api</artifactId>
  <version>1.2</version>
</dependency>
```

# Usage example #

```
Telegram telegram = new Telegram("YOUR_TOKEN");
User user = telegram.getMe();

```

## Deployment instructions ##

```
mvn clean install && mvn release:prepare -B && mvn release:perform -B
```
