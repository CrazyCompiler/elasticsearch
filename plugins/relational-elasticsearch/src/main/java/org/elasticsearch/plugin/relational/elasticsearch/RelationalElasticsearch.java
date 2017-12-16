package org.elasticsearch.plugin.relational.elasticsearch;

import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.common.settings.ClusterSettings;
import org.elasticsearch.common.settings.IndexScopedSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.SettingsFilter;
import org.elasticsearch.env.Environment;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RelationalElasticsearch extends Plugin implements ActionPlugin {

    private final RelationalElasticsearchConfig config;

    public RelationalElasticsearch(Settings settings) {
        Environment environment = new Environment(settings);
        this.config = null;
    }

    @Override
    public List<RestHandler> getRestHandlers(Settings settings, RestController restController, ClusterSettings clusterSettings,
                                             IndexScopedSettings indexScopedSettings, SettingsFilter settingsFilter, IndexNameExpressionResolver indexNameExpressionResolver,
                                             Supplier<DiscoveryNodes> nodesInCluster) {
        Handler handler = new Handler(settings, restController, config);
        List<RestHandler> listHandlers = new ArrayList<>();
        listHandlers.add(handler);
        return listHandlers;
    }

}
