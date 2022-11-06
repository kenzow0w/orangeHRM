package configs.config;

@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "file:src/test/resources/configs/config.properties"
})
public interface PropertyConfig extends org.aeonbits.owner.Config {

    @Key("sirius.selenium.driver")
    @DefaultValue(value = "chrome")
    String browser();

    @Key("url")
    String url();

    @Key("highLighter")
    @DefaultValue(value = "false")
    boolean highLighter();

    @Key("wait.timeouts.short")
    @DefaultValue(value = "30")
    int timeoutShort();

    @Key("wait.timeouts.long")
    @DefaultValue(value = "90")
    int timeoutLong();

}
