package com.example.sleepingrequest;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetadataController {
    Logger logger = LoggerFactory.getLogger(MetadataController.class);

    @RequestMapping(value="/metadata/phoneNumbers/{userId}", method = RequestMethod.GET)
    public long getPhoneNumber(@PathVariable("userId") String userId) {

        logger.info("Getting phone number of : " + userId);
        sleep();
        return getRandomNumber();
    }

    @RequestMapping(value="/metadata/names/{userId}", method = RequestMethod.GET)
    public String getName(@PathVariable("userId") String userId) {

        logger.info("Getting name: " + userId);
        sleep();
        return getRandomName();
    }

    private String getRandomName() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    private void sleep() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long getRandomNumber() {
        return  (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }
}
