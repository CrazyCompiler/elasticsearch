package org.elasticsearch.plugin.relational.elasticsearch;

import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Example configuration.
 */
public class RelationalElasticsearchConfig {
    private final Settings customSettings;

    public static final Setting<String> TEST_SETTING =
            new Setting<String>("test", "default_value",
                    (value) -> value, Setting.Property.Dynamic);

    public RelationalElasticsearchConfig(Environment env) {
        // The directory part of the location matches the artifactId of this plugin
        Path path = env.configFile().resolve("relational/elasticsearch/config/config.yml");
        try {
            customSettings = Settings.builder().loadFromPath(path).build();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load settings, giving up", e);
        }

        // asserts for tests
        assert customSettings != null;
        assert TEST_SETTING.get(customSettings) != null;
    }

    public String getTestConfig() {
        return TEST_SETTING.get(customSettings);
    }
}
