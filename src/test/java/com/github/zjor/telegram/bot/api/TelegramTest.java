package com.github.zjor.telegram.bot.api;

import com.github.zjor.telegram.bot.api.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Slf4j
@Ignore("Token should be specified")
public class TelegramTest {

    private Telegram telegram;

    @Before
    public void setUp() {
        telegram = new Telegram("<YOUR TOKEN>");
    }

    @Test
    public void shouldGetMe() throws TelegramException {
        User user = telegram.getMe();
        log.info("getMe: {}", user);
        Assert.assertNotNull(user.getUsername());
    }

}
