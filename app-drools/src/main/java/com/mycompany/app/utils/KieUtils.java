package com.mycompany.app.utils;
import org.kie.api.runtime.KieContainer;
/**
 * @author john
 */
public class KieUtils {

    private static KieContainer kieContainer;

    public static KieContainer getKieContainer() {
        return kieContainer;
    }

    public static void setKieContainer(KieContainer kieContainer) {
        KieUtils.kieContainer = kieContainer;
    }

}
