package com.example.sleepingrequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@org.springframework.stereotype.Controller
public class ControllerCache {
    Logger logger = LoggerFactory.getLogger(ControllerCache.class);

    AtomicInteger counter = new AtomicInteger(0);
    @RequestMapping(value="/sleepCache/{timeInMilliSeconds}", method = RequestMethod.GET)
    public ResponseEntity<String> sleep(@PathVariable("timeInMilliSeconds") int timeInMilliSeconds) {
        logger.info(String.format("Sleeping: %dms", timeInMilliSeconds));
        try {
            Thread.sleep(timeInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Number of threads: " + Thread.activeCount());
        int requestId = counter.incrementAndGet();

        logger.info(String.format("Done sleeping %d | Request ID %d", timeInMilliSeconds, requestId ));

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(3600, TimeUnit.SECONDS))
                .body(String.format("Done sleeping %d | Request ID %d", timeInMilliSeconds, requestId ));
    }
}
