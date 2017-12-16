package org.elasticsearch.plugin.relational.elasticsearch;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.Table;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.*;
import org.elasticsearch.rest.action.cat.AbstractCatAction;
import org.elasticsearch.rest.action.cat.RestTable;

import static org.elasticsearch.rest.RestRequest.Method.GET;

public class Handler extends AbstractCatAction {

    private final RelationalElasticsearchConfig config;

    public Handler(Settings settings, RestController controller, RelationalElasticsearchConfig config) {
        super(settings);
        this.config = config;
        controller.registerHandler(GET, "/_cat/viraj", this);
    }

    @Override
    protected BaseRestHandler.RestChannelConsumer doCatRequest(final RestRequest request, final NodeClient client) {
        return channel -> {
            try {
                channel.sendResponse(new BytesRestResponse(RestStatus.OK, "bla"));
            } catch (final Exception e) {
                channel.sendResponse(new BytesRestResponse(channel, e));
            }
        };
    }

    @Override
    protected void documentation(StringBuilder sb) {
        sb.append(documentation());
    }

    @Override
    protected Table getTableWithHeader(RestRequest request) {
        return null;
    }

    public static String documentation() {
        return "/_cat/configured_example\n";
    }
}
