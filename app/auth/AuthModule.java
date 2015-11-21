package auth;

import com.google.inject.AbstractModule;
import global.AppConfig;
import org.pac4j.core.authorization.RequireAnyRoleAuthorizer;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator;
import org.pac4j.oauth.client.FacebookClient;
import org.pac4j.oauth.client.TwitterClient;
import org.pac4j.play.ApplicationLogoutController;
import org.pac4j.play.http.DefaultHttpActionAdapter;
import org.pac4j.play.http.HttpActionAdapter;
import org.pac4j.play.store.DataStore;
import org.pac4j.play.store.PlayCacheStore;
import play.Configuration;
import play.Environment;

/**
 * Created by sasinda on 10/26/15.
 */
public class AuthModule extends AbstractModule {
    private final Environment environment;
    private final Configuration configuration;

    public AuthModule(
            Environment environment,
            Configuration configuration) {
        this.environment = environment;
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        //Bindings
        bind(HttpActionAdapter.class).to(DefaultHttpActionAdapter.class);

        //Cache
        final PlayCacheStore playCacheStore = new PlayCacheStore();
        // for test purposes: profile timeout = 60 seconds
        //cacheStore.setProfileTimeout(60);
        bind(DataStore.class).toInstance(playCacheStore);



        //Clients
        String baseUrl=AppConfig.getString("baseUrl");
        com.typesafe.config.Config twitter = AppConfig.getConfig("twitter");
        com.typesafe.config.Config facebook = AppConfig.getConfig("facebook");
        // OAuth
        final TwitterClient twitterClient = new TwitterClient(twitter.getString("consumerKey"), twitter.getString("consumerSecret"));
        final FacebookClient facebookClient = new FacebookClient(facebook.getString("consumerKey"), facebook.getString("consumerSecret"));
        // HTTP
        final FormClient formClient = new FormClient("/", new SimpleTestUsernamePasswordAuthenticator());

        final Clients clients = new Clients(baseUrl + "/auth/callback", twitterClient, formClient,facebookClient); // , casProxyReceptor);

        final Config config = new Config(clients);
        config.addAuthorizer("admin", new RequireAnyRoleAuthorizer("ROLE_ADMIN"));
        bind(Config.class).toInstance(config);


        // logout
        final ApplicationLogoutController logoutController = new ApplicationLogoutController();
        logoutController.setDefaultUrl("/");
         bind(ApplicationLogoutController.class).toInstance(logoutController);
    }
}
