package com.github.zjor.telegram.bot.framework.dispatch;

import com.github.zjor.telegram.bot.api.Telegram;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.concurrent.ExecutorService;

@Slf4j
public class Dispatcher {

    @Inject
    private Telegram telegram;

    private int updatesOffset;

    @Inject
    private UpdateHandler updateHandler;

    private ExecutorService executor;

    private boolean started;

    public Dispatcher(ExecutorService executor) {
        this.executor = executor;
    }

    public void start() {
        started = true;

        executor.submit(() -> {
            log.info("started");
            while (started) {
                try {
                    telegram.getUpdates(updatesOffset + 1, 5, 30).stream().forEach(u -> {
                        updatesOffset = Math.max(updatesOffset, u.getUpdateId());
                        updateHandler.handle(u);
                    });
                } catch (Throwable e) {
                    log.error("Updates processing failed: " + e.getMessage(), e);
                }
            }
            log.info("stopped");
        });

    }

    public void stop() {
        started = false;
        executor.shutdown();
    }
}
