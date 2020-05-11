package com.mycompany.app;

import com.mycompany.app.testa.DomainUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class Parameterized2Test {

    //default value = 0
    @Parameter
    public String domainName;


    //Single parameter, use Object[]
    @Parameters(name = "{index}: testDomain - {0}")
    public static Object[] data() {
        return new Object[]{
                "google.com",
                "mkyong.com",
                "twitter.com"
        };
    }

    @Test
    public void test_valid_domain() {
        assertThat(DomainUtils.isValid(domainName), is(true));
    }
}
