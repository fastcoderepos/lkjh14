package com.fastcode.lkjh14.restcontrollers.extended;

import com.fastcode.lkjh14.restcontrollers.core.Test4ControllerTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=test")
public class Test4ControllerTestExtended extends Test4ControllerTest {
    //Add your custom code here
}
