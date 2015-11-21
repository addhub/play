package social.clients;

import com.fasterxml.jackson.databind.JsonNode;
import org.pac4j.core.client.BaseClient;
import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.client.BaseOAuth20Client;
import org.pac4j.oauth.client.exception.OAuthCredentialsException;
import org.pac4j.oauth.credentials.OAuthCredentials;
import org.pac4j.oauth.profile.JsonHelper;
import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.oauth.OAuth20ServiceImpl;
import social.clients.api.WeiboApi;
import social.clients.profile.WeiboAttributesDefinition;
import social.clients.profile.WeiboProfile;
import social.clients.profile.WeiboScope;

import java.util.List;

/**
 * Created by sasinda on 10/29/15.
 * Open source code refer from below address:
 * https://github.com/eduosi/oauth-client/blob/master/src/main/java/com/buession/oauth/provider/impl/WeiboClient.java
 */

public class WeiboClient extends BaseOAuth20Client<WeiboProfile> {

    @Override
    //figure out the state for weibo user.
    // Not Implemented for Addhub project.
    protected boolean requiresStateParameter() {
        return false;
    }

    @Override
    //figure out wehteth the user request was cancelled by Weibo official business.
    // Not Implemented for Addhub project.
    protected boolean hasBeenCancelled(WebContext context) {
        final String error = context.getRequestParameter(OAuthCredentialsException.ERROR);
        /* user has denied permissions */
        if ("access_denied".equals(error)) {
            return true;
        }
        return false;
    }

    @Override
    protected String getProfileUrl(Token accessToken) {
        String userurl =  "http://www.weibo.com/" + "welltone617" + "/home";
        return userurl;
    }

    protected String getProfileUrl() {
        return WeiboApi.BASE_URL + "2/statuses/user_timeline.json";
    }

    /**
     * scope是OAuth2.0授权机制中authorize接口的一个参数
     * 通过scope，平台将开放更多的微博核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新OAuth2.0授权页中有权利选择赋予应用的功能。
     */
    protected WeiboScope scope = WeiboScope.ALL;

    /**
     * 设置 WeiboScope
     *
     * @param scope
     *        WeiboScope
     */
    public void setScope(WeiboScope scope) {
        this.scope = scope;
    }

    /**
     * 返回 scope
     *
     * @return
     */
    public WeiboScope getScope() {
        return scope;
    }


    protected WeiboClient newProvider() {
        WeiboClient provider = new WeiboClient();
        provider.setScope(scope);
        logger.debug("request scope: " + scope);

        return provider;
    }

    @Override
    protected void internalInit() {
        service = new OAuth20ServiceImpl(new WeiboApi(), new OAuthConfig(key, secret,
                callbackUrl, SignatureType.Header, scope == null ? null : scope.toString(), null)
                );
    }




    @Override
    protected WeiboProfile extractUserProfile(String body) {
        final WeiboProfile profile = new WeiboProfile();
        JsonNode json = JsonHelper.getFirstNode(body);

        if (json != null) {
            JsonNode statuses = json.get("statuses");

            if (statuses != null) {
                JsonNode status = statuses.get(0);

                if (status != null) {
                    JsonNode user = status.get("user");

                    if (user != null) {
                        profile.setId(JsonHelper.get(user, WeiboAttributesDefinition.ID));

                        List<String> principalAttributes =new WeiboAttributesDefinition().getPrincipalAttributes();

                        for (final String name : principalAttributes) {
                            Object value = JsonHelper.get(user, name);

                            profile.addAttribute(name, value);
                            if (WeiboAttributesDefinition.SCREEN_NAME.equals(name) == true) {
                                profile.addAttribute(WeiboAttributesDefinition.USERNAME, value);
                            } else if (WeiboAttributesDefinition.LOCATION.equals(name) == true) {
                                profile.addAttribute(WeiboAttributesDefinition.ADDRESS, value);
                            }
                        }

                        Object avatarHd = JsonHelper.get(user, WeiboAttributesDefinition.AVATAR_HD);
                        if (avatarHd != null && avatarHd.toString().length() != 0) {
                            profile.addAttribute(WeiboAttributesDefinition.AVATAR, avatarHd);
                        } else {
                            Object avatarLarge = JsonHelper.get(user,
                                    WeiboAttributesDefinition.AVATAR_LARGE);
                            if (avatarLarge != null && avatarLarge.toString().length() != 0) {
                                profile.addAttribute(WeiboAttributesDefinition.AVATAR, avatarLarge);
                            }
                        }
                    }
                }
            }
        }

        return profile;
    }

    @Override
    protected BaseClient<OAuthCredentials, WeiboProfile> newClient() {
        return null;
    }
}
