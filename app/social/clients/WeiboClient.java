package social.clients;

import org.pac4j.core.client.BaseClient;
import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.client.BaseOAuth20Client;
import org.pac4j.oauth.credentials.OAuthCredentials;
import org.scribe.model.Token;
import social.clients.profile.WeiboProfile;

/**
 * Created by sasinda on 10/29/15.
 */
public class WeiboClient extends BaseOAuth20Client<WeiboProfile> {

    @Override
    protected boolean requiresStateParameter() {
        return false;
    }

    @Override
    protected boolean hasBeenCancelled(WebContext context) {
        return false;
    }

    @Override
    protected String getProfileUrl(Token accessToken) {
        return null;
    }

    @Override
    protected WeiboProfile extractUserProfile(String body) {
        return null;
    }

    @Override
    protected BaseClient<OAuthCredentials, WeiboProfile> newClient() {
        return null;
    }
}
